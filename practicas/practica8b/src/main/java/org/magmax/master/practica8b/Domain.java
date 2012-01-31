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
class Domain {

    private HttpServletResponse response = null;
    private HttpServletRequest request;
    private ServletContext context;

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public Redirector getRedirector() {
        return new Redirector(request, response);
    }

    public void setServletContext(ServletContext context) {
        this.context = context;
    }

    public Persistence getPersistence() throws ClassNotFoundException, DriverNotDefinedException {
        return Persistence.createInstance(getDBCredentials());
    }

    public DBCredentials getDBCredentials() {
        DBCredentials result = new DBCredentials();

        result.setDriver(getContextParameter(context, "driver"));
        result.setUrl(getContextParameter(context, "uri"));
        result.setUser(getContextParameter(context, "user"));
        result.setPass(getContextParameter(context, "password"));

        return result;
    }

    private String getContextParameter(ServletContext context, String keyword) {
        if (context == null)
            return "";
        String result = context.getInitParameter(keyword);
        if (result == null) {
            return "";
        }
        return result;
    }
}
