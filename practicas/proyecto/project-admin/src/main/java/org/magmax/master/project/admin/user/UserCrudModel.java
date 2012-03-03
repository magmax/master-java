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
package org.magmax.master.project.admin.user;

import java.util.List;
import org.magmax.eswing.crud.CrudObject;
import org.magmax.eswing.crud.DefaultCrudModel;
import org.magmax.master.project.admin.Persistence;
import org.magmax.master.project.persistence.dao.UserDAO;
import org.magmax.master.project.persistence.pojo.User;

/**
 *
 * @author miguel
 */
public class UserCrudModel extends DefaultCrudModel {

    private static String[] headers = new String[]{"Nombre", "Admin"};

    public UserCrudModel() {
        super();
        setColumnIdentifiers(headers);
    }

    @Override
    public void add(CrudObject item) {
        UserDAO userdao = getDAO();
        UserRow userrow = (UserRow) item;
        userdao.store((userrow.getEntity()));
        super.add(item);
    }

    @Override
    public void remove(List data) {
        super.remove(data);
    }

    @Override
    public void update(CrudObject item) {
        UserDAO userdao = getDAO();
        UserRow userrow = (UserRow) item;
        userdao.store((userrow.getEntity()));
        super.update(item);
    }

    @Override
    public void load() {
        UserDAO userdao = getDAO();
        for (User each : userdao.findAll()) {
            UserRow row = new UserRow();
            row.setEntity(each);
            super.add(row);
        }
        super.load();
    }

    private UserDAO getDAO() {
        Persistence persistence = Persistence.getInstance();
        UserDAO userdao = persistence.getUserDAO();
        return userdao;
    }
}
