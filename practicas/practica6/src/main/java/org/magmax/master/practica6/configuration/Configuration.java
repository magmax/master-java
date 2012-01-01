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
package org.magmax.master.practica6.configuration;

import java.io.File;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class Configuration {
    private static Configuration instance = null;
    private int port = 6668;
    private String persistentFilePath = ".persistence";

    private Configuration() {
        // singleton
    }
    
    public static Configuration getInstance() {
        if (instance == null)
            instance = new Configuration();
        return instance;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public File getPersitentFile() {
        return new File(persistentFilePath);
    }
    
    public void setPersistentFile(String filename) {
        persistentFilePath = filename;
    }
}
