/*
 * Copyright (C) 2012 Miguel Angel Garcia <miguelangel.garcia@gmail.com>
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
package org.magmax.master.project.ui.persistence;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import org.magmax.master.project.persistence.dao.DAOFactory;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class Persistence extends DAOFactory {

    private static Persistence instance = null;

    private Persistence(Map properties) {
        super("production", properties);
    }

    public static Persistence getInstance(ServletContext servletContext) {
        if (instance == null) {
            instance = new Persistence(readProperties(servletContext));
        }
        return instance;
    }

    @Override
    public void destroy() {
        if (instance == null) {
            return;
        }
        super.destroy();
    }

    private static Map readProperties(ServletContext servletContext) {
        String[] keys = new String[]{
            "hibernate.dialect",
            "hibernate.connection.driver_class",
            "hibernate.connection.url",
            "hibernate.connection.username",
            "hibernate.connection.password",
            "hibernate.show_sql",
            "hibernate.hbm2ddl.auto"};
        HashMap<String, String> result = new HashMap<String, String>();
        String value;
        for (String each : keys) {
            value = servletContext.getInitParameter(each);
            if (value != null) {
                result.put(each, value);
            }
            System.out.println(each + "-->" + value);
        }

        return result;
    }
}
