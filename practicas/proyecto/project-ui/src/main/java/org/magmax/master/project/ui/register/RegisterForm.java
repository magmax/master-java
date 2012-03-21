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
package org.magmax.master.project.ui.register;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.magmax.master.project.ui.Helpers.CommonHelper;
import org.magmax.master.project.ui.persistence.Persistence;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class RegisterForm extends org.apache.struts.action.ActionForm {

    private static final String EMAIL_PATTERN = "^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*\\.[A-Za-z]{2,}$";
    private String username;
    private String password;
    private String email;
    private String address;
    private String phone;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (CommonHelper.isEmptyString(getUsername())) {
            errors.add("register.username", new ActionMessage("error.name.required"));
        } else if (userExists(getUsername())) {
            errors.add("register.username", new ActionMessage("error.name.repeated"));
        }
        if (CommonHelper.isEmptyString(getPassword())) {
            errors.add("register.password", new ActionMessage("error.password.required"));
        }
        if (CommonHelper.isEmptyString(getEmail())) {
            errors.add("register.email", new ActionMessage("error.email.required"));
        } else if (!validEmail()) {
            errors.add("register.email", new ActionMessage("error.email.invalid"));
        }
        return errors;
    }

    private boolean validEmail() {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(getEmail());
        return matcher.matches();
    }

    private boolean userExists(String username) {
        return Persistence.getInstance(servlet.getServletContext()).getUserDAO().findByName(username) != null;
    }
}
