/*
 * Copyright (C) 2012 Miguel Angel Garcia<miguelangel.garcia@gmail.com>
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
import org.magmax.eswing.crud.DefaultCrudModel;
import org.magmax.master.project.admin.Persistence;
import org.magmax.master.project.persistence.dao.UserDAO;
import org.magmax.master.project.persistence.pojo.User;

/**
 *
 * @author Miguel Angel Garcia<miguelangel.garcia@gmail.com>
 */
public class UserCrudModel extends DefaultCrudModel<UserRow> {

    private static String[] headers = new String[]{"Nombre", "Admin"};

    public UserCrudModel() {
        super();
        setColumnIdentifiers(headers);
    }

    @Override
    public void add(UserRow item) {
        saveItem(item);
        super.add(item);
    }

    @Override
    public void update(UserRow item) {
        saveItem(item);
        super.update(item);
    }

    @Override
    public void remove(List<UserRow> data) {
        UserDAO userdao = getDAO();
        for (UserRow each : data) {
            userdao.delete(each.getEntity());
        }
        super.remove(data);
    }

    @Override
    public void load() {
        super.clear();
        for (User each : getDAO().findAll()) {
            UserRow row = new UserRow();
            row.setEntity(each);
            super.add(row);
        }
        super.load();
    }

    @Override
    public Class<?> getColumnClass(int column) {
        if (column == 1) {
            return Boolean.class;
        }
        return String.class;
    }

    private void saveItem(UserRow userrow) {
        getDAO().store(userrow.getEntity());
    }

    private UserDAO getDAO() {
        return Persistence.getInstance().getUserDAO();
    }
}
