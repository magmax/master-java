/*
 * Copyright (C) 2012 Miguel Angel Garcia<miguelangel.garcia@gmail.com>
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
package org.magmax.master.project.admin;

import java.io.*;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.exception.GenericJDBCException;
import org.magmax.master.project.persistence.dao.DAOFactory;

/**
 *
 * @author Miguel Angel Garcia<miguelangel.garcia@gmail.com>
 */
public class Persistence extends DAOFactory {

    public static final String PROFILE = "production";
    public static final String CONFIG_FILE = "configuration.cfg";
    private static Persistence instance = null;

    private Persistence(Map properties) {
        super(PROFILE, properties);
    }

    public static Persistence getInstance() {
        if (instance == null) {
            instance = new Persistence(getConnectionConfiguration());
        }
        return instance;
    }

    public void destroy() {
        if (instance == null) {
            return;
        }
        super.destroy();
    }

    private static Map getConnectionConfiguration() {
        Properties properties = new Properties();
        InputStream reader = null;
        try {
            reader = new FileInputStream(new File(CONFIG_FILE));
            properties.load(reader);
            Logger.getAnonymousLogger().log(Level.INFO, "Using configuration file " + CONFIG_FILE);
        } catch (IOException ex) {
            Logger.getAnonymousLogger().log(Level.INFO, "Configuration file (" + CONFIG_FILE + ")not found");
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
            }
        }
        return properties;
    }
}
