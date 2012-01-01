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
import java.util.Scanner;
import org.magmax.master.practica6.configuration.CityCode;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class Marshaller {

    public byte[] serialize(Request request) {
        return String.valueOf(request.getCitycode().code).getBytes();
    }

    public byte[] serialize(Response response) {
        if (response.getTemperatures().length == 0) {
            return "ini|fin".getBytes();
        }

        StringBuilder result = new StringBuilder();
        result.append("ini|");
        result.append(response.getCitycode().code);
        for (Float each : response.getTemperatures()) {
            result.append("|");
            result.append(each);
        }
        result.append("|fin");
        return result.toString().getBytes();
    }

    public Request unserialize_request(InputStream inputStream) throws Exception {
        Request result = new Request();
        if (inputStream.available() == 0) {
            return result;
        }
        result.setCitycode(CityCode.TOLEDO);
        return result;
    }

    private CityCode readCity(Scanner scanner) throws Exception {
        if (scanner.hasNextInt()) {
            return CityCode.getFromCode(scanner.nextInt());
        }
        throw new Exception("No city was specified");
    }

    public Response unserialize_response(InputStream inputStream) throws IOException, Exception {
        ResponseParser responseParser = new ResponseParser();
        responseParser.parse(inputStream);
        return responseParser.getResponse();
    }
}
