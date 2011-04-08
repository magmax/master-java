package org.magmax.masterjava.tema1.dividir;

public class Satisfaccion {

	public String show(int i) {
		if (i<1 || i>5)
			throw new IllegalArgumentException();
		String[] values = new String[] {"Muy insatisfecho", "Insatisfecho", "Normal", "Satisfecho", "Muy satisfecho" }; 
		return values[i-1];
	}

}
