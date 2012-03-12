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
package org.magmax.master.project.admin.email;

import java.util.List;
import org.magmax.eswing.crud.DefaultCrudModel;
import org.magmax.master.project.admin.Persistence;
import org.magmax.master.project.persistence.dao.EmailDAO;
import org.magmax.master.project.persistence.dao.UserDAO;
import org.magmax.master.project.persistence.pojo.Email;
import org.magmax.master.project.persistence.pojo.User;

/**
 *
 * @author Miguel Angel Garcia<miguelangel.garcia@gmail.com>
 */
public class EmailCrudModel extends DefaultCrudModel<EmailRow> {

    private static String[] headers = new String[]{"Address"};
    private User user;

    public EmailCrudModel() {
        super();
        setColumnIdentifiers(headers);
    }

    @Override
    public void add(EmailRow item) {
        item.getEntity().setUser(user);
        getDAO().store(item.getEntity());
        super.add(item);
    }
    
    @Override
    public void load() {
        if (user != null) {
            UserDAO userdao = Persistence.getInstance().getUserDAO();
            userdao.refresh(user);
            for (Email each : user.getEmails()) {
                EmailRow row = new EmailRow();
                row.setEntity(each);
                super.add(row);
            }
        }
        super.load();
    }

    @Override
    public void remove(List<EmailRow> data) {
        EmailDAO dao = getDAO();
        for (EmailRow each : data) {
            dao.delete(each.getEntity());
        }
        super.remove(data);
    }

    @Override
    public void update(EmailRow item) {
        item.getEntity().setUser(user);
        getDAO().store(item.getEntity());
        super.update(item);
    }

    private EmailDAO getDAO() {
        return Persistence.getInstance().getEmailDAO();
    }

    public void setUser(User user) {
        this.user = user;
    }
}
