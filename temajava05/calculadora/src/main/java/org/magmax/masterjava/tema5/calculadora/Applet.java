package org.magmax.masterjava.tema5.calculadora;

import javax.swing.JApplet;

public class Applet extends JApplet{
	private static final long serialVersionUID = -4356401463523475751L;

	public void init() {
		Calculadora calculadora = new Calculadora(this.getContentPane());
        calculadora.setVisible(true);
	}
}
