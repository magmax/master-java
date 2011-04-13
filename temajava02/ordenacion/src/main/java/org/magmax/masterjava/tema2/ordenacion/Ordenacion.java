package org.magmax.masterjava.tema2.ordenacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class Ordenacion 
{
    public static void main( String[] args )
    {
    	List<Integer> numbers = readNumbers();
    	Collections.sort(numbers);
    	System.out.println(numbers);
    }

	private static List<Integer> readNumbers() {
		List<Integer> result = new ArrayList<Integer>();
		int number = 0;
		Scanner scan = new Scanner(System.in);
		while ((number = scan.nextInt()) > 0) {
			result.add(number);
		}
		return result;
	}
}
