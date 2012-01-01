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
package org.magmax.master.practica6.server;

import org.magmax.master.practica6.configuration.CityCode;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class Temperature {

    private final File persistence;

    public Temperature(File persistence) {
        this.persistence = persistence;
    }

    public Float[] getTemperatures(CityCode citycode) throws FileNotFoundException {
        ArrayList<Float> result = new ArrayList<Float>();
        PairReader reader = new PairReader(persistence);
        for (Pair pair = reader.next(); pair != null; pair = reader.next()) {
            if (pair.code == citycode.code) {
                result.add(pair.temperature);
            }
        }
        return result.toArray(new Float[0]);
    }
}

class Pair {

    int code;
    float temperature;
}

class PairReader {

    private final Scanner scanner;
    private Pair lastPair = null;
    private boolean finished = false;

    PairReader(File persistence) throws FileNotFoundException {
        scanner = new Scanner(persistence);
        scanner.useDelimiter("[,;]");
        scanner.useLocale(Locale.ENGLISH);
    }

    public Pair next() {
        lastPair = new Pair();
        readCode();
        readTemperature();
        return finished ? null : lastPair;
    }

    private void readCode() {
        if (scanner.hasNextInt()) {
            lastPair.code = scanner.nextInt();
            return;
        }
        finished = true;
    }

    private void readTemperature() {
        if (scanner.hasNextFloat()) {
            lastPair.temperature = scanner.nextFloat();
            return;
        }
        finished = true;
    }
}