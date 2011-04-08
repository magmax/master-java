package org.magmax.masterjava.tema1.dividir;

public class Ordenar {

	public int[] ordenar(int[] vector) {
		if (vector.length < 2)
			return vector;

		int lower;
		for (int i = 0; i < vector.length; ++i) {
			lower = get_lower_possition_for(vector, i);

			if (vector[i] > vector[lower]) {
				int aux = vector[lower];
				vector[lower] = vector[i];
				vector[i] = aux;
			}			
		}
		return vector;
	}

	private int get_lower_possition_for(int[] vector, int i) {
		int result = i;
		for (;i < vector.length; ++i) {
			if (vector[i] < vector[result])
				result = i;
		}
		return result;
	}

}
