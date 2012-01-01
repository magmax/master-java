/*
 * Copyright (C) 2011,2012 Miguel Angel Garcia <miguelangel.garcia@gmail.com>
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.magmax.master.practica6.communication.Marshaller;
import org.magmax.master.practica6.communication.Request;
import org.magmax.master.practica6.communication.Response;
import org.magmax.master.practica6.configuration.Configuration;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
class ServerHandler implements Runnable {

    public static final String LOGGER = "Server";
    private final Marshaller serializer = new Marshaller();
    private Thread thread;
    private InputStream input;
    private OutputStream output;

    public void setSocket(Socket socket) throws IOException {
        input = socket.getInputStream();
        output = socket.getOutputStream();
    }

    @Override
    public void run() {
        try {
            process();
        } catch (IOException ex) {
            Logger.getLogger(LOGGER).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(LOGGER).log(Level.SEVERE, null, ex);
        }
    }

    private void process() throws IOException, Exception {
        Request request = serializer.unserialize_request(input);
        Logger.getLogger(LOGGER).log(Level.INFO, "City requested: " + request.getCitycode());
        Response response = process(request);
        output.write(serializer.serialize(response));
        Logger.getLogger(LOGGER).log(Level.INFO, "Server response: " + response.getTemperatures().length);
    }

    protected Response process(Request request) throws FileNotFoundException {
        Response result = new Response();
        result.setCitycode(request.getCitycode());
        for (Float each : getTemperatures(request)) {
            result.addTemperature(each);
        }
        return result;
    }

    protected Float[] getTemperatures(Request request) {
        try {
            return getTemperaturesFromFile(request);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Float[0];
    }

    protected Float[] getTemperaturesFromFile(Request request) throws FileNotFoundException {
        if (request.getCitycode() == null) {
            return new Float[0];
        }
        Temperature temperature = new Temperature(Configuration.getInstance().getPersitentFile());
        return temperature.getTemperatures(request.getCitycode());
    }

    void start(Socket socket) throws IOException {
        this.setSocket(socket);
        thread = new Thread(this);
        thread.start();
    }

    void stop() {
        thread.interrupt();
    }
}
