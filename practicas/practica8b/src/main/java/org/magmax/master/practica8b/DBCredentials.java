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
public class DBCredentials {

    public static final String DEFAULT_DRIVER = "org.hsqldb.jdbcDriver";
    public static final String DEFAULT_URL = "jdbc:hsqldb:mem:default";
    public static final String DEFAULT_USER = "sa";
    public static final String DEFAULT_PASSWORD = "";

    private String driver = "";
    private String url = "";
    private String user = "";
    private String pass = "";
    
    public static DBCredentials createWithDefaults() {
        DBCredentials result = new DBCredentials();
        result.setDriver(DEFAULT_DRIVER);
        result.setUrl(DEFAULT_URL);
        result.setUser(DEFAULT_USER);
        result.setPass(DEFAULT_PASSWORD);
        return result;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDriver() {
        return driver;
    }

    public String getPass() {
        return pass;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }
    
    
}
