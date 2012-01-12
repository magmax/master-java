/*
 * Copyright (C) 2012 miguel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.magmax.practica7.gui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import org.magmax.practica7.exceptions.DatabaseNotDefinedException;
import org.magmax.practica7.exceptions.PersonNotFoundException;
import org.magmax.practica7.persistence.Persistence;
import org.magmax.practica7.pojo.Person;

/**
 *
 * @author miguel
 */
public class MainWindow extends JFrame {

    private JTextField nameField;
    private JTextField phoneField;
    private JTextField dniField;
    private JTextField searchField;
    private JList namelist;
    private Persistence persistence;

    public MainWindow() throws HeadlessException, DatabaseNotDefinedException {
        initializePersistence();
        initializeGUI();
    }

    private void initializePersistence() throws DatabaseNotDefinedException {
        persistence = new Persistence();
        persistence.useDatabase("./database.dat");
    }

    private void initializeGUI() {
        setLayout(null);
        setTitle("Práctica 7");
        setSize(600, 400);
        setLocationByPlatform(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton buttonAdd = new JButton("Añadir");
        buttonAdd.setBounds(10, 45, 80, 24);
        add(buttonAdd);

        JLabel label;

        label = new JLabel("Nombre");
        label.setBounds(110, 10, 80, 24);
        add(label);

        label = new JLabel("Teléfono");
        label.setBounds(110, 45, 80, 24);
        add(label);

        label = new JLabel("DNI");
        label.setBounds(110, 80, 80, 24);
        add(label);

        nameField = new JTextField();
        nameField.setBounds(200, 10, 110, 24);
        add(nameField);

        phoneField = new JTextField();
        phoneField.setBounds(200, 45, 110, 24);
        add(phoneField);

        dniField = new JTextField();
        dniField.setBounds(200, 80, 110, 24);
        add(dniField);

        /*
         * Search
         */

        label = new JLabel("Introducir DNI");
        label.setBounds(150, 120, 140, 24);
        add(label);

        JButton buttonSearch = new JButton("Buscar");
        buttonSearch.setBounds(10, 140, 120, 24);
        add(buttonSearch);

        searchField = new JTextField();
        searchField.setBounds(150, 140, 110, 24);
        add(searchField);

        /*
         * Show names
         */

        JButton buttonShowNames = new JButton("Mostrar Nombres");
        buttonShowNames.setBounds(10, 240, 170, 24);
        add(buttonShowNames);

        namelist = new JList();
        namelist.setBounds(190, 180, 200, 140);
        namelist.setBorder(new BevelBorder(BevelBorder.LOWERED));
        add(namelist);

        /*
         * Models
         */
        NameListModel nameModel = new NameListModel();
        namelist.setModel(nameModel);

        /*
         * Events
         */
        buttonAdd.addActionListener(new AddListener(persistence, nameField, phoneField, dniField));
        buttonSearch.addActionListener(new SearchListener(persistence, searchField, nameField, phoneField, dniField));
        buttonShowNames.addActionListener(new ShowNamesListener(persistence, nameModel));
        namelist.addMouseListener(new DeleteListener(persistence));
    }
}

class NameListModel implements ListModel {

    String[] content = new String[0];
    ArrayList<ListDataListener> listeners = new ArrayList<ListDataListener>();

    public int getSize() {
        return content.length;
    }

    public Object getElementAt(int i) {
        return content[i];
    }

    public void addListDataListener(ListDataListener ll) {
        listeners.add(ll);
    }

    public void removeListDataListener(ListDataListener ll) {
        listeners.remove(ll);
    }

    public void setList(String[] names) {
        content = names;
        fireContentChanged();
    }

    private void fireContentChanged() {
        ListDataEvent event = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, content.length);
        for (ListDataListener each : listeners) {
            each.contentsChanged(event);
        }
    }
}

class AddListener implements ActionListener {

    private final Persistence persistence;
    private final JTextField nameField;
    private final JTextField phoneField;
    private final JTextField dniField;

    AddListener(Persistence persistence, JTextField nameField, JTextField phoneField, JTextField dniField) {
        this.persistence = persistence;
        this.nameField = nameField;
        this.phoneField = phoneField;
        this.dniField = dniField;
    }

    public void actionPerformed(ActionEvent ae) {
        Person person = new Person();
        person.setName(nameField.getText());
        person.setPhone(phoneField.getText());
        person.setDni(dniField.getText());
        save(person);
    }

    private void save(Person person) {
        try {
            persistence.create(person);
        } catch (Exception ex) {
            MessageRenderer messages = new MessageRenderer();
            messages.showError("Error saving person", ex.getMessage());
        }
    }
}

class SearchListener implements ActionListener {

    private final Persistence persistence;
    private final JTextField searchField;
    private final JTextField nameField;
    private final JTextField phoneField;
    private final JTextField dniField;

    SearchListener(Persistence persistence, JTextField searchField, JTextField nameField, JTextField phoneField, JTextField dniField) {
        this.persistence = persistence;
        this.searchField = searchField;
        this.nameField = nameField;
        this.phoneField = phoneField;
        this.dniField = dniField;
    }

    public void actionPerformed(ActionEvent ae) {
        Person person;
        person = retrievePerson();
        showPerson(person);
    }

    private Person retrievePerson() {
        try {
            return persistence.retrievePerson(searchField.getText());
        } catch (PersonNotFoundException ex) {
            // do nothing
        } catch (Exception ex) {
            MessageRenderer messages = new MessageRenderer();
            messages.showError("Error retrieving person", ex.getMessage());
        }
        return new Person();
    }

    private void showPerson(Person person) {
        nameField.setText(person.getName());
        phoneField.setText(person.getPhone());
        dniField.setText(person.getDni());
    }
}

class ShowNamesListener implements ActionListener {

    private final Persistence persistence;
    private final NameListModel nameModel;

    ShowNamesListener(Persistence persistence, NameListModel nameModel) {
        this.persistence = persistence;
        this.nameModel = nameModel;

    }

    public void actionPerformed(ActionEvent ae) {
        nameModel.setList(getNames());
    }

    private String[] getNames() {
        try {
            return persistence.showNames();
        } catch (SQLException ex) {
            Logger.getLogger(ShowNamesListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DatabaseNotDefinedException ex) {
            Logger.getLogger(ShowNamesListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new String[0];
    }
}

class DeleteListener implements MouseListener {

    private final Persistence persistence;

    DeleteListener(Persistence persistence) {
        this.persistence = persistence;
    }

    public void mouseClicked(MouseEvent me) {
        if (me.getClickCount() != 2) {
            return;
        }
        JList list = (JList) me.getSource();
        deletePerson((String)list.getSelectedValue());
    }

    private void deletePerson(String personName) {
        try {
            persistence.delete(personName);
        } catch (SQLException ex) {
            Logger.getLogger(DeleteListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DatabaseNotDefinedException ex) {
            Logger.getLogger(DeleteListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mousePressed(MouseEvent me) {
        // do nothing
    }

    public void mouseReleased(MouseEvent me) {
        // do nothing
    }

    public void mouseEntered(MouseEvent me) {
        // do nothing
    }

    public void mouseExited(MouseEvent me) {
        // do nothing
    }
}