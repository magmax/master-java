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
package practica4;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
class PersonaWindow extends Frame {

    private TextField nombre;
    private TextField apellidos;
    private TextField telefono;
    private TextField edad;
    private Persona persona = new Persona();
    private PersonaWindow me;

    public PersonaWindow() {
        initialize();
        setValues();
    }

    private void initialize() {
        me = this;
        setTitle("Datos de la persona");
        setSize(600, 400);
        setLayout(null);

        Label label1 = new Label("Nombre:");
        label1.setBounds(25, 40, 100, 26);

        Label label2 = new Label("Apellidos:");
        label2.setBounds(25, 80, 100, 26);

        Label label3 = new Label("Telefono:");
        label3.setBounds(25, 120, 100, 26);

        Label label4 = new Label("Edad:");
        label4.setBounds(25, 160, 100, 26);

        nombre = new TextField();
        nombre.setBounds(125, 40, 250, 26);

        apellidos = new TextField();
        apellidos.setBounds(125, 80, 250, 26);

        telefono = new TextField();
        telefono.setBounds(125, 120, 250, 26);

        edad = new TextField();
        edad.setBounds(125, 160, 250, 26);

        Button update = new Button("Actualizar");
        update.setBounds(80, 200, 100, 26);

        Button exit = new Button("Salir");
        exit.setBounds(200, 200, 100, 26);

        add(label1);
        add(label2);
        add(label3);
        add(label4);

        add(nombre);
        add(apellidos);
        add(telefono);
        add(edad);

        add(update);
        add(exit);

        update.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                save();
            }
        });

        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                ConfirmDialog dialog = new ConfirmDialog(me, "Salir", "¿Desea guardar antes de salir?");
                dialog.setVisible(true);
                if (dialog.getResult() == ConfirmDialog.RESULT.ACCEPT) {
                    save();
                }
                exit();
            }
        });

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent we) {
                super.windowClosed(we);
                exit();
            }
        });

        setVisible(true);
    }

    private void exit() {
        setVisible(false);
        System.exit(0);
    }

    private void setValues() {
        try {
            persona = PersonaPersitence.load();

            nombre.setText(persona.getNombre());
            apellidos.setText(persona.getApellidos());
            telefono.setText(persona.getTelefono());
            edad.setText(persona.getEdad() == null ? "" : persona.getEdad().toString());
        } catch (Exception ex) {
            Logger.getLogger(PersonaWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void save() {
        try {
            persona.setNombre(nombre.getText());
            persona.setApellidos(apellidos.getText());
            persona.setEdad(edad.getText());
            persona.setTelefono(telefono.getText());
            PersonaPersitence.save(persona);
        } catch (Exception ex) {
            Logger.getLogger(PersonaWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
