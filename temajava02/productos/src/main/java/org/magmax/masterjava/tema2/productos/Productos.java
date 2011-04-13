package org.magmax.masterjava.tema2.productos;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Hello world!
 * 
 */
public class Productos {
	private static final int INSERT = 1;
	private static final int UPDATE = 2;
	private static final int AVERAGE = 3;
	private static final int DELETE = 4;
	private static final int EXIT = 5;

	public static void main(String[] args) {
		Productos productos = new Productos();
		productos.run();
	}

	private Scanner scan;
	private HashMap<String, Float> products;

	private void run() {
		int action = 0;
		while (action != EXIT) {
			showMenu();
			action = getAction();
			switch (action) {
			case INSERT:
				processInsertion();
				break;
			case UPDATE:
				processUpdate();
				break;
			case AVERAGE:
				processAverage();
				break;
			case DELETE:
				processDeletion();
				break;
			case EXIT:
				break;
			default:
				System.out.println("Opción desconocida");
			}
		}
		System.out.println("Bye!");
	}

	private void processDeletion() {
		flushBuffer();
		System.out.print("Código:");
		String code = scan.nextLine();
		if (!products.containsKey(code)) {
			System.out.println("El código no existe.");
			return;
		}

		products.remove(code);		
	}

	private void processAverage() {
		float sum = 0;
		float total = 0;
		if (!products.isEmpty()) {
			for (Float item : products.values()) {
				sum += item;
			}
			total = sum / products.size();
		}

		System.out.println("Media: " + total);
	}

	private void processUpdate() {
		flushBuffer();
		System.out.print("Código:");
		String code = scan.nextLine();
		if (!products.containsKey(code)) {
			System.out.println("El código no existe.");
			return;
		}

		System.out.print("Precio:");
		float prize = scan.nextFloat();
		products.put(code, prize);
	}

	private void processInsertion() {
		flushBuffer();
		System.out.print("Código:");
		String code = scan.nextLine();
		System.out.print("Precio:");
		float prize = scan.nextFloat();
		if (products.containsKey(code)) {
			System.out.println("El código ya existe.");
			return;
		}
		products.put(code, prize);
	}

	private void flushBuffer() {
		if (scan.hasNextLine())
			scan.nextLine();
	}

	public Productos() {
		super();
		scan = new Scanner(System.in);
		products = new HashMap<String, Float>();
	}

	private int getAction() {
		return scan.nextInt();
	}

	private void showMenu() {
		System.out.println("1-Introducir producto");
		System.out.println("2-Modificar precio");
		System.out.println("3-Mostrar precio medio");
		System.out.println("4-Eliminar producto");
		System.out.println("5-Salir");
		System.out.println();
	}
}
