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
package org.magmax.masterjava.practica3.game;

import java.awt.Font;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.magmax.masterjava.practica3.persistence.Persistence;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class GameWindow extends JDialog {

    private JLabel solution;
    private Game game;
    private JLabel tries;
    private JTextField field;

    public GameWindow(Frame frame)  {
        super(frame, true);
        try {
            initializeVariables();
            initialize();
        } catch (IOException ex) {
            Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void endGame(boolean win) throws HeadlessException {
        JOptionPane pane;
        if (win) {
            pane = new JOptionPane("Has ganado!!!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            pane = new JOptionPane("Has perdido el juego!", JOptionPane.ERROR_MESSAGE);
        }
        JDialog dialog = pane.createDialog("End Of Game");
        dialog.setVisible(true);
        setVisible(false);
        dispose();
    }

    private void initializeVariables() throws IOException {
        Persistence persistence = new Persistence();
        game = new Game(persistence);
    }

    private void initialize() {
        setTitle("Modo Jugador");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel label = new JLabel("Introducir letra:");
        label.setBounds(50, 20, 100, 20);

        field = new JTextField();
        field.setBounds(160, 20, 20, 20);

        JButton comprobar = new JButton("Comprobar");
        comprobar.setBounds(200, 20, 150, 20);

        solution = new JLabel();
        solution.setFont(new Font("Monospaced", Font.BOLD, 14));
        solution.setBounds(20, 100, 600, 40);

        tries = new JLabel();
        tries.setBounds(40, 150, 150, 20);

        getContentPane().add(label);
        getContentPane().add(field);
        getContentPane().add(comprobar);
        getContentPane().add(solution);
        getContentPane().add(tries);

        getRootPane().setDefaultButton(comprobar);
        
        setTriesLeft();
        updateTitleToGuess();

        comprobar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    play();
                    field.requestFocus();
                    field.selectAll();
                } catch (Exception ex) {
                    Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    private void play() throws Exception {
        String word = field.getText();
        if (word.length() != 1) {
            return;
        }
        game.tryLetter(word.charAt(0));
        setTriesLeft();
        updateTitleToGuess();

        if (game.getLeftTries() == 0) {
            endGame(false);
        }

        if (game.solved()) {
            endGame(true);
        }
    }

    private String insertSpacesBetweenCharacters(String in) {
        StringBuilder result = new StringBuilder();
        for (char c : in.toCharArray()) {
            result.append(c);
            result.append(' ');
        }
        return result.toString();
    }

    public static void main(String[] args) throws IOException {
        GameWindow game = new GameWindow(null);
        game.setVisible(true);
    }

    private void setTriesLeft() {
        tries.setText("Intentos restantes: " + game.getLeftTries());
    }

    private void updateTitleToGuess() {
        solution.setText(insertSpacesBetweenCharacters(game.getGuessTitle()));
    }
}
