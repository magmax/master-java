package org.magmax.masterj2ee.practica1;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
class Lector extends java.io.BufferedReader {

    public Lector() {
        super(new InputStreamReader(new BufferedInputStream(System.in)));
    }

    public Lector(Reader reader) {
        super(reader);
    }

    private Lector(Reader reader, int i) { // No está en la especificación.
        super(reader, i);
    }

    public int readInt() throws NumeroNoValidoExcepcion, IOException {
        try {
            return Integer.valueOf(readLine());
        } catch (NumberFormatException ex) {
            throw new NumeroNoValidoExcepcion();
        }
    }

    public double readDouble() throws IOException, NumeroNoValidoExcepcion {
        try {
            return Double.valueOf(readLine());
        } catch (NumberFormatException ex) {
            throw new NumeroNoValidoExcepcion();
        }
    }

    public int[] readManyInt() throws IOException, NumeroNoValidoExcepcion {
        try {
            String[] data = readLine().split(",");
            int[] result = new int[data.length];
            for (int i = 0; i < data.length; ++i) {
                result[i] = Integer.valueOf(data[i]);
            }
            return result;
        } catch (NumberFormatException ex) {
            throw new NumeroNoValidoExcepcion();
        }
    }
}
