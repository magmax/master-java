/*
 * Copyright (C) 2012 Miguel Angel Garcia <miguelangel.garcia@gmail.com>
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
package org.magmax.master.practica6.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.magmax.master.practica6.configuration.CityCode;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class ClientGUI extends JFrame {

    public static void main(String[] args) {
        ClientGUI client = new ClientGUI();
        client.setVisible(true);
    }
    private final JComboBox citySelector;

    public ClientGUI() {
        setLayout(null);
        setTitle("Terminal Cliente");
        setSize(600, 400);
        setLocationByPlatform(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Seleccione ciudad:");
        label.setBounds(50, 50, 130, 24);
        add(label);

        citySelector = new JComboBox(CityCode.values());
        citySelector.setBounds(180, 50, 200, 24);
        add(citySelector);

        JButton recover = new JButton("Recuperar");
        recover.setBounds(80, 100, 120, 24);
        add(recover);

        JButton exit = new JButton("Salir");
        exit.setBounds(220, 100, 120, 24);
        add(exit);

        recover.addActionListener(new RecoverListener(this));
        exit.addActionListener(new ExitListener(this));
    }

    void recover() throws Exception {
        CityCode city = (CityCode) this.citySelector.getSelectedItem();
        Float[] temperatures = recover(city);
        draw(temperatures);
    }

    public void exit() {
        this.processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    private Float[] recover(CityCode cityCode) throws Exception {
        Client client = new Client();
        Float[] temperatures = client.getTemperature(cityCode);
        return temperatures;
    }

    private void draw(Float[] temperatures) {
        String message = "No temperatures were found";
        if (temperatures.length > 0) {
            message = "Temperatures: " + Arrays.toString(temperatures);
        }
        MessageRenderer.showInfo("Result", message);
    }
}

class RecoverListener implements ActionListener {

    private final ClientGUI source;

    RecoverListener(ClientGUI source) {
        this.source = source;
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            source.recover();
        } catch (Exception ex) {
            Logger.getLogger(RecoverListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class ExitListener implements ActionListener {

    private final ClientGUI source;

    public ExitListener(ClientGUI source) {
        this.source = source;
    }

    public void actionPerformed(ActionEvent ae) {
        this.source.exit();
    }
}