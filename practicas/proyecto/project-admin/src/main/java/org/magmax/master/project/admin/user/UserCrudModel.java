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

/**
 *
 * @author miguel
 */
public class UserCrudModel extends DefaultCrudModel {

    public UserCrudModel() {
        super();
        setColumnIdentifiers(new String[] {"Nombre", "Admin"});
    }

    @Override
    public void add(CrudObject item) {
        super.add(item);
    }

    @Override
    public void remove(List data) {
        super.remove(data);
    }


}
