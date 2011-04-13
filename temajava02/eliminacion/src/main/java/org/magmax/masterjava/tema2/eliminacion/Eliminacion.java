package org.magmax.masterjava.tema2.eliminacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Hello world!
 *
 */
public class Eliminacion 
{
    public static void main( String[] args ) throws IOException
    {
    	System.out.println("Introduzca una cadena:");
    	String line = readline();
    	System.out.println("Introduzca una palabra de la cadena");
    	String word = readword();
    	System.out.println("Resultado:");
    	System.out.println(replace(line, word));
    }
    
    private static String replace(String line, String word) {
    	StringBuilder result = new StringBuilder();
		for (String item : line.split(" ")) {
			if (!item.equals(word))
			{
				result.append(item);
				result.append(' ');
			}
		}
		return result.toString().trim();
    }

	private static String readword() throws IOException {
    	StringBuilder builder = new StringBuilder();
    	int i;
    	char c;
    	while (true)
    	{
    		i = System.in.read();
    		if ( i == -1)
    			break;
    		c = (char) i;
    		if (c == ' ' || c == '\n')
    			break;
    		builder.append(c);
    	}
    	return builder.toString();
	}

	public static String readline () throws IOException
    {
    	InputStreamReader isr = new InputStreamReader(System.in);
    	BufferedReader reader = new BufferedReader(isr);
    	String result = reader.readLine();
    	return result;
    }
}
