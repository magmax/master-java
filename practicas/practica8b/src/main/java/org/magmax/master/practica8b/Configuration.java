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

/**
 *
 * @author miguel
 */
class Configuration {
    private static Configuration instance = null;
    private static Domain domain = null;
    
    public static Configuration getInstance() {
        if (instance == null)
            instance = new Configuration();
        return instance;
    }

    public static void reset() {
        domain = null;
    }
    
    public static Domain getDomain() {
        if (domain == null)
            return new Domain();
        return domain;
    }

    /**
     * This method is for testing purposes only. 
     * It allows to use a mocked domain.
     * @param domain Mocked domain to use.
     */
    public static void setDomain(Domain domain) {
        Configuration.domain = domain;
    }
}
