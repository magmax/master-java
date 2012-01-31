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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author miguel
 */
public class Redirector {

    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private Map<String, Object> attributes = new HashMap<String, Object>();

    Redirector(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    void redirect(JspPage target) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(target.getUri());
        for(Entry<String, Object> each : attributes.entrySet()) {
            request.setAttribute(each.getKey(), each.getValue());
        }
        rd.forward(request, response);
    }

    public boolean isValid() {
        return request != null && response != null;
    }

    void addAttribute(String key, Object object) {
        attributes.put(key, object);
    }
}
