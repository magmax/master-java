package org.magmax.masterjava.tema2.inversion;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.ConsoleHandler;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	//Console console = System.console();
    	System.out.println("Introduzca una cadena:");
    	InputStreamReader isr = new InputStreamReader(System.in);
    	BufferedReader reader = new BufferedReader(isr); 
    	char[] list = reader.readLine().toCharArray(); 
    	for (int i=list.length; i > 0; --i)
    		System.out.print(list[i-1]);
    }
}
