/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.magmax.masterj2ee.practica1;

import java.io.IOException;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class Main {
    public static void main (String[] args) throws IOException, NumeroNoValidoExcepcion {
        Main m = new Main();
        System.out.println("Average: " + m.average_stdin());
    }
    
    private float average_stdin() throws IOException, NumeroNoValidoExcepcion {
        Lector l = new Lector();
        int sum = 0;
        float nums = 0;
        
        while (l.ready()) {
            sum += l.readInt();
            nums++;
        }
        
        if (nums == 0)
            return 0;
        return sum/nums;
    }
}
