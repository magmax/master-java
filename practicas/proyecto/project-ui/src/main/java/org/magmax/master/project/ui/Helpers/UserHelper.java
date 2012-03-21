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
package org.magmax.master.project.ui.Helpers;

import org.apache.struts.action.ActionServlet;
import org.magmax.master.project.persistence.pojo.User;
import org.magmax.master.project.ui.persistence.Persistence;
import org.magmax.master.project.ui.register.UserForm;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class UserHelper {

    private static final String KEYWORD = "user";
    private final ActionServlet servlet;

    public UserHelper(ActionServlet servlet) {
        this.servlet = servlet;
    }

    public void save(User user) {
        UserForm userform = new UserForm();
        userform.setId(user.getId());
        userform.setName(user.getName());
        userform.setAdmin(user.isAdmin());
        servlet.getServletContext().setAttribute(KEYWORD, userform);
    }

    public User retrieve() {
        Object object = servlet.getServletContext().getAttribute(KEYWORD);
        if (object == null) {
            return null;
        }
        UserForm userform = (UserForm) object;
        return Persistence.getInstance(servlet.getServletContext()).getUserDAO().findById(userform.getId());
    }

    public boolean isAuthenticated() {
        return retrieve() != null;
    }

    public boolean isAdmin() {
        User user = retrieve();
        if (user == null) {
            return false;
        }
        return user.isAdmin();
    }
}
