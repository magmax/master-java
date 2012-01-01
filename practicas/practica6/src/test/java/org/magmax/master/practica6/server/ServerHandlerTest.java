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
import java.net.ServerSocket;
import java.net.Socket;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.magmax.master.practica6.communication.Request;
import org.magmax.master.practica6.configuration.Configuration;
import static org.junit.Assert.*;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class ServerHandlerTest {

    private ServerHandler sut;
    private ServerSocket serversocket;

    @Before
    public void setup() throws IOException {
        Configuration.getInstance().setPersistentFile("src/test/resources/temperatures004.txt");
        serversocket = new ServerSocket(50000);
        sut = new ServerHandler();
    }

    @After
    public void teardown() throws IOException {
        serversocket.close();
    }

    @Test
    public void testGetTemperaturesForNoCityMeansNoTemperature() throws FileNotFoundException {
        assertArrayEquals(new Float[0], sut.getTemperatures(new Request()));
    }
}
