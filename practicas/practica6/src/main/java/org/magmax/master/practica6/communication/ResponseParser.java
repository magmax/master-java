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
package org.magmax.master.practica6.communication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;
import org.magmax.master.practica6.configuration.CityCode;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
class ResponseParser {

    private Scanner scanner;
    private Response response = new Response();

    public void parse(InputStream inputStream) throws IOException, Exception {
        initializeScanner(inputStream);
        processInitializationValue();
        addCity();
        while (hasMoreValues()) {
            addNextTemperature();
        }
        processFinalizationValue();
    }

    private void initializeScanner(InputStream inputStream) {
        scanner = new Scanner(inputStream);
        scanner.useDelimiter("\\|");
        scanner.useLocale(Locale.ENGLISH);
    }

    public Response getResponse() {
        return response;
    }

    private void processInitializationValue() throws IOException {
        String value = scanner.next();
        if ("ini".equals(value))
            return;
        throw new IOException("Invalid data. Expected 'ini', but found '" + value + "'");
    }

    private void processFinalizationValue() throws IOException {
        String value = scanner.next();
        if ("fin".equals(value))
            return;
        throw new IOException("Invalid data. Expected 'fin', but found '" + value + "'");
    }

    private boolean hasMoreValues() {
        return scanner.hasNextFloat();
    }

    private void addNextTemperature() {
        response.addTemperature(scanner.nextFloat());
    }

    private void addCity() throws Exception {
        if (scanner.hasNextInt()) {
            response.setCitycode(CityCode.getFromCode(scanner.nextInt()));
        }
    }
}
