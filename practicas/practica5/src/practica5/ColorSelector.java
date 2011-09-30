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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.RootPaneContainer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
class ColorSelector implements ActionListener {

    private final Color color;
    private final RootPaneContainer panel;

    public ColorSelector(RootPaneContainer panel, Color color) {
        this.color = color;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        panel.getContentPane().getGraphics().setColor(color);
        System.out.println("Pintando de " + color);
    }
}
