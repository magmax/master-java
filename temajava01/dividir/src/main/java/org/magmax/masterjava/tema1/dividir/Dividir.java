package org.magmax.masterjava.tema1.dividir;

public class Dividir {

	public int divide(String[] args) {
		if (args.length != 2)
			throw new IllegalArgumentException("Número de argumentos inválido");
		
		return divide(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
	}

	private int divide(int a, int b) {
		return a>b ? a/b : b/a;
	}

}
