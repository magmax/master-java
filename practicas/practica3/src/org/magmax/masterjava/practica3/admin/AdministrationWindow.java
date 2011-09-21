/*
 * Copyright (C) 2011 Miguel Angel Garcia <miguelangel.garcia@gmail.com>
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
package org.magmax.masterjava.practica3.admin;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.magmax.masterjava.practica3.persistence.Persistence;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class AdministrationWindow extends JDialog {

    private Persistence persistence = new Persistence();
    private JTextField field;
    private JLabel lastaction;

    public AdministrationWindow(Frame frame) {
        super(frame, true);
        initialize();
    }

    private void initialize() {
        setTitle("Modo Administrador");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);


        JLabel label = new JLabel("Introduzca nuevo título");
        label.setBounds(50, 100, 200, 30);
        field = new JTextField();
        field.setBounds(250, 100, 250, 30);
        JButton button = new JButton("Añadir");
        button.setBounds(200, 200, 150, 30);
        lastaction = new JLabel();
        lastaction.setBounds(50, 300, 400, 30);


        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    persistence.save(field.getText());
                    lastaction.setText(MessageFormat.format("Added ''{0}''.", field.getText()));
                    field.setText("");
                    field.requestFocus();
                } catch (IOException ex) {
                    Logger.getLogger(AdministrationWindow.class.getName()).log(Level.SEVERE, null, ex);
                    lastaction.setText(MessageFormat.format("Error found: {0}", ex.getLocalizedMessage()));
                }
            }
        });

        getContentPane().add(label);
        getContentPane().add(field);
        getContentPane().add(button);
        getContentPane().add(lastaction);

        getRootPane().setDefaultButton(button);
    }

    public static void main(String[] args) {
        AdministrationWindow admin = new AdministrationWindow(null);
        admin.setVisible(true);
    }
}
