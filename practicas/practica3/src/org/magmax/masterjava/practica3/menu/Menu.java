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
package org.magmax.masterjava.practica3.menu;

import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.magmax.masterjava.practica3.admin.AdministrationWindow;
import org.magmax.masterjava.practica3.game.GameWindow;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class Menu extends JFrame {
    private Menu me;

    public Menu() throws HeadlessException {
        initialize();
    }

    public Menu(String string, GraphicsConfiguration gc) {
        super(string, gc);
        initialize();
    }

    public Menu(String string) throws HeadlessException {
        super(string);
        initialize();
    }

    public Menu(GraphicsConfiguration gc) {
        super(gc);
        initialize();
    }

    private void initialize() {
        me = this;
        setTitle("Ventana de inicio");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel label = new JLabel("Juego del Ahorcado");
        label.setBounds(100, 30, 500, 150);
        label.setFont(new Font("Dialog", Font.BOLD, 36));
        
        JButton newgame = new JButton("Nueva partida");
        newgame.setBounds(100, 200, 150, 30);

        JButton admin = new JButton("Administrar");
        admin.setBounds(350, 200, 150, 30);

        JButton exit = new JButton("Salir");
        exit.setBounds(230, 300, 150, 30);

        newgame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                GameWindow dialog = new GameWindow(me);
                dialog.setVisible(true);
            }
        });
        
        admin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                AdministrationWindow dialog = new AdministrationWindow(me);
                dialog.setVisible(true);
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                close_window();
            }
        });
        
        getContentPane().add(label);
        getContentPane().add(newgame);
        getContentPane().add(admin);
        getContentPane().add(exit);
        
        setVisible(true);
    }
    
    public void close_window() {
        this.setVisible(false);
        System.exit(0);
    }
}
