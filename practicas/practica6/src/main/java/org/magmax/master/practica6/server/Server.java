/*
 * Copyright (C) 2011, 2012 Miguel Angel Garcia <miguelangel.garcia@gmail.com>
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

import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.magmax.master.practica6.configuration.Configuration;

public class Server extends Thread {

    public static void main(String[] args) throws UnknownHostException, IOException {
        Server server = new Server();
        server.start();

    }

    @Override
    public void run() {
        try {
            process();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void process() throws IOException {
        int port = Configuration.getInstance().getPort();
        Logger.getLogger("Server").log(Level.INFO, "Server listening on port {0}", port);
        ServerSocket server = new ServerSocket(port);
        while (true) {
            ServerHandler handler = new ServerHandler();
            handler.start(server.accept());
        }
    }
}
