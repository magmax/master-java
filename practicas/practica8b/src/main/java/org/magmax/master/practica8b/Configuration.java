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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author miguel
 */
class Configuration {
    private static Configuration instance = null;
    private Domain domain = null;
    private String dbDriver = "";
    private String dbUri = "";
    private String dbUser = "";
    private String dbPassword = "";
    
    public static Configuration getInstance() {
        if (instance == null)
            instance = new Configuration();
        return instance;
    }

    public static void reset() {
        Configuration config = getInstance();
        config.setDomain(null);
        config.setDbDriver("");
        config.setDbUri("");
        config.setDbUser("");
        config.setDbPassword("");
    }
    
    /**
     * This method is for testing purposes only. 
     * It allows to use a mocked domain.
     * @param domain Mocked domain to use.
     */
    public void setDomain(Domain domain) {
        instance.domain = domain;
    }

    public Domain getDomain(HttpServletResponse response, HttpServletRequest request, ServletContext context) {
        Domain result = domain;
        if (result == null)
            result = new Domain();
        result.setResponse(response);
        result.setRequest(request);
        result.setServletContext(context);
        setDbDriver(getContextParameter(context, "driver"));
        setDbUri(getContextParameter(context, "uri"));
        setDbUser(getContextParameter(context, "user"));
        setDbPassword(getContextParameter(context, "password"));
        return result;
    }

    public String getDbDriver() {
        return dbDriver;
    }

    public void setDbDriver(String db_driver) {
        this.dbDriver = db_driver;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String db_password) {
        this.dbPassword = db_password;
    }

    public String getDbUri() {
        return dbUri;
    }

    public void setDbUri(String db_uri) {
        this.dbUri = db_uri;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String db_user) {
        this.dbUser = db_user;
    }
    
    private String getContextParameter(ServletContext context, String keyword) {
        String result = context.getInitParameter(keyword);
        if (result == null)
            return "";
        return result;
    }
}
