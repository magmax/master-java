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
package org.magmax.master.practica6.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.magmax.master.practica6.communication.Marshaller;
import org.magmax.master.practica6.communication.Request;
import org.magmax.master.practica6.communication.Response;
import org.magmax.master.practica6.configuration.CityCode;
import org.magmax.master.practica6.configuration.Configuration;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class Client {

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        Float[] temperatures = client.getTemperature(args[1]);
        showTemperatures(temperatures);
    }

    private static void showTemperatures(Float[] temperatures) {
        for (Float each : temperatures) {
            System.out.println(each);
        }
    }
    private final Marshaller serializer;

    public Client() {
        serializer = new Marshaller();
    }

    public Float[] getTemperature(String city) throws UnknownHostException, IOException, Exception {
        CityCode citycode = CityCode.valueOf(city);
        return getTemperature(citycode);
    }

    public Float[] getTemperature(CityCode citycode) throws UnknownHostException, IOException, Exception {
        Request request = new Request();
        request.setCitycode(citycode);
        Float[] result = getTemperature(request).getTemperatures();
        showFinalMessage(result);
        return result;
    }

    private Response getTemperature(Request request) throws IOException, Exception {
        int port = Configuration.getInstance().getPort();
        showConnectionMessage(port);
        Socket socket = new Socket("localhost", port);
        socket.setSoTimeout(2000);
        OutputStream output = socket.getOutputStream();
        InputStream input = socket.getInputStream();
        output.write(serializer.serialize(request));
        return serializer.unserialize_response(input);
    }

    private void showConnectionMessage(int port) {
        Logger.getLogger("Client").log(Level.INFO, "Client connecting to port {0}", port);
    }

    private void showFinalMessage(Float[] result) {
        Logger.getLogger("Client").log(Level.INFO, "Client result length {0}", result.length);
    }
}
