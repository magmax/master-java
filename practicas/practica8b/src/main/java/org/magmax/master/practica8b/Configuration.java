/*
 * Copyright (C) 2012 miguel
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
package org.magmax.master.practica8b;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author miguel
 */
class Configuration {
    private static Configuration instance = null;
    private Domain domain = null;
    
    public static Configuration getInstance() {
        if (instance == null)
            instance = new Configuration();
        return instance;
    }

    public static void reset() {
        Configuration config = getInstance();
        config.setDomain(null);
    }
    
    /**
     * This method is for testing purposes only. 
     * It allows to use a mocked domain.
     * @param domain Mocked domain to use.
     */
    public void setDomain(Domain domain) {
        instance.domain = domain;
    }

    public Domain getDomain(HttpServlet controller, HttpServletRequest request, HttpServletResponse response) {
        Domain result = domain;
        if (result == null)
            result = new Domain();
        result.setController(controller);
        result.setRequest(request);
        result.setResponse(response);
        return result;
    }
}
