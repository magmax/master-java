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
package practica5;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ButtonGroup;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class TableroDibujo extends JApplet {

    private TableroDibujo me;
    private Color currentColor;
    private int currentSize = 1;
    private Point lastpoint = null;

    public void init() {
        me = this;
        getContentPane().setLayout(null);

        JLabel label1 = new JLabel("Color trazo:");
        label1.setBounds(15, 5, 100, 26);

        JLabel label2 = new JLabel("Grosor trazo:");
        label2.setBounds(150, 5, 100, 26);

        JButton limpiar = new JButton("Limpiar");
        limpiar.setBounds(250, 5, 90, 26);

        JRadioButton azul = new JRadioButton("Azúl");
        azul.setBounds(0, 30, 55, 26);
        azul.setName("#0000ff");

        JRadioButton rojo = new JRadioButton("Rojo");
        rojo.setBounds(55, 30, 53, 26);
        rojo.setName("#FF0000");

        JRadioButton amarillo = new JRadioButton("Amarillo");
        amarillo.setBounds(105, 30, 80, 26);
        amarillo.setName("#FFFF00");

        ButtonGroup group = new ButtonGroup();
        group.add(azul);
        group.add(rojo);
        group.add(amarillo);

        JSpinner trazo = new JSpinner();
        trazo.setBounds(190, 30, 50, 26);
        trazo.setModel(new SpinnerNumberModel(currentSize, 1, 50, 1));

        getContentPane().add(label1);
        getContentPane().add(label2);

        getContentPane().add(limpiar);

        getContentPane().add(azul);
        getContentPane().add(rojo);
        getContentPane().add(amarillo);

        getContentPane().add(trazo);

        azul.addActionListener(new ColorSelector(Color.BLUE));
        rojo.addActionListener(new ColorSelector(Color.RED));
        amarillo.addActionListener(new ColorSelector(Color.YELLOW));

        limpiar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                me.repaint();
            }
        });

        getContentPane().addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent event) {
                Point point = event.getPoint();
                Graphics g = me.getGraphics();
                g.setColor(currentColor);
                lastpoint = point;
                g.drawRect(point.x, point.y, currentSize, currentSize);
                g.fillRect(point.x, point.y, currentSize, currentSize);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });

        getContentPane().addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent event) {
                Point point = event.getPoint();
                Graphics g = me.getGraphics();
                g.setColor(currentColor);
                for (int i = 0; i < currentSize; ++i) {
                    g.drawLine(lastpoint.x, lastpoint.y, point.x, point.y);
                }
                lastpoint = point;
            }

            @Override
            public void mouseMoved(MouseEvent me) {
            }
        });

        trazo.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent ce) {
                JSpinner source = (JSpinner) ce.getSource();
                setCurrentSize((Integer) source.getValue());
            }
        });
    }

    class ColorSelector implements ActionListener {

        private final Color color;

        public ColorSelector(Color color) {
            this.color = color;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            setColor(color);
        }
    }

    private void setColor(Color c) {
        currentColor = c;
    }

    protected void setCurrentSize(int size) {
        this.currentSize = size;
    }
}
