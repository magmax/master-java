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
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class ConfirmDialog extends Dialog {

    public enum RESULT {

        ACCEPT,
        CANCEL
    };
    private final String message;
    private RESULT result = RESULT.CANCEL;

    public ConfirmDialog(Frame parent, String title, String message) throws HeadlessException {
        super(parent, title, true);
        this.message = message;
        initialize();
    }

    private void initialize() {
        setBounds(100, 100, 350, 200);
        setLayout(null);

        TextArea label = new TextArea(message);
        label.setBounds(25, 45, 300, 90);
        label.setEditable(false);

        Button ok = new Button("Aceptar");
        ok.setBounds(119, 145, 100, 26);

        Button cancel = new Button("Cancelar");
        cancel.setBounds(225, 145, 100, 26);

        add(label);
        add(ok);
        add(cancel);

        ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                result = RESULT.ACCEPT;
                close();
            }
        });
        
        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                result = RESULT.CANCEL;
                close();
            }
        });

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent we) {
                super.windowClosing(we);
                setVisible(false);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        ConfirmDialog c = new ConfirmDialog(null, "prueba", "probando");
        c.setVisible(true);
    }

    public RESULT getResult() {
        return result;
    }
    
    private void close() {
        setVisible(false);
    }
}
