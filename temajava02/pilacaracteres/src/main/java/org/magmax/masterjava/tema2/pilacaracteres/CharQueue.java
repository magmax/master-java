package org.magmax.masterjava.tema2.pilacaracteres;

import java.util.ArrayList;

public class CharQueue {
	ArrayList<String> queue = new ArrayList<String>();
	private int maximo;
	
	public CharQueue(int maximo) {
		this.maximo = maximo;
	}

	public CharQueue() {
		maximo = Integer.MAX_VALUE;
	}

	public void añadir(String string) {
		if (string.length() < maximo)
			queue.add(string);
		else
			queue.add(string.substring(0,maximo));
	}

	@Override
	public String toString() {
		return queue.toString();
	}

	public void eliminar() {
		if (queue.size()==0)
			return;
		queue.remove(queue.size()-1);
	}

	public Object total() {
		return queue.size();
	}

	
	
}
