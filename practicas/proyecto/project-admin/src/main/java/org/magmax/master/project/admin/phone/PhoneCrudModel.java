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
package org.magmax.master.project.admin.phone;

import java.util.List;
import org.magmax.eswing.crud.DefaultCrudModel;
import org.magmax.master.project.admin.Persistence;
import org.magmax.master.project.persistence.dao.PhoneDAO;
import org.magmax.master.project.persistence.dao.UserDAO;
import org.magmax.master.project.persistence.pojo.Phone;
import org.magmax.master.project.persistence.pojo.User;

/**
 *
 * @author Miguel Angel Garcia<miguelangel.garcia@gmail.com>
 */
public class PhoneCrudModel extends DefaultCrudModel<PhoneRow> {

    private static String[] headers = new String[]{"Number"};
    private User user = null;

    public PhoneCrudModel() {
        super();
        setColumnIdentifiers(headers);
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void add(PhoneRow item) {
        item.getEntity().setUser(user);
        getDAO().store(item.getEntity());
        super.add(item);
    }
    
    @Override
    public void load() {
        if (user != null) {
            UserDAO userdao = Persistence.getInstance().getUserDAO();
            userdao.refresh(user);
            for (Phone each : user.getPhones()) {
                PhoneRow row = new PhoneRow();
                row.setEntity(each);
                super.add(row);
            }
        }
        super.load();
    }

    @Override
    public void remove(List<PhoneRow> data) {
        PhoneDAO dao = getDAO();
        for (PhoneRow each : data) {
            dao.delete(each.getEntity());
        }
        super.remove(data);
    }

    @Override
    public void update(PhoneRow item) {
        item.getEntity().setUser(user);
        getDAO().store(item.getEntity());
        super.update(item);
    }

    private PhoneDAO getDAO() {
        return Persistence.getInstance().getPhoneDAO();
    }
}
