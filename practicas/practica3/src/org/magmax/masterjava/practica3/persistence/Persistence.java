/*
 * Copyright (C) 2011 Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.magmax.masterjava.practica3.persistence;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class Persistence {

    private static String FILENAME = "peliculas.dat";

    public void save(String text) throws IOException {
        if ("".equals(text) || text == null) {
            return;
        }

        FileWriter output = new FileWriter(FILENAME, true);
        output.append(text);
        output.append('\n');
        output.close();
    }

    String[] load() throws IOException {
        FileReader filereader;
        try {
            filereader = new FileReader(FILENAME);
        } catch (FileNotFoundException ex) {
            return new String[0];
        }
        
        ArrayList<String> result = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(filereader);
        while (reader.ready())
            result.add(reader.readLine());
        return result.toArray(new String[0]);
    }
    
    public String getRandomTitle() throws IOException {
        String[] strings = load();
        Random random = new Random();
        return strings[random.nextInt(strings.length)];
    }
}
