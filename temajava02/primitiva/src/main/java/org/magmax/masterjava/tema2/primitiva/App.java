package org.magmax.masterjava.tema2.primitiva;

import java.util.HashSet;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Double value;
    	HashSet<Integer> values = new HashSet<Integer>();
    	
    	while (values.size()<6)
    	{
    		value = Math.floor(Math.random()*50);
    		if (values.contains(value.intValue()))
    			continue;
    		values.add(value.intValue());
    	}
    	System.out.println( values );
    }
}
