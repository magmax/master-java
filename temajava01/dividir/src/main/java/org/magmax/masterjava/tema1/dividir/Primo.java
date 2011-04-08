package org.magmax.masterjava.tema1.dividir;

public class Primo {

	int[] primos = new int[] {2, 3, 5, 7, 11, 13, 17, 23, 29};
	
	public boolean esPrimo(int n) {
		if ( n < 4 )
			return true;
		for ( int i = 0; i < primos.length && n != primos[i]; ++i)
			if (n % primos[i] == 0)
				return false;
		return true;
	}

}
