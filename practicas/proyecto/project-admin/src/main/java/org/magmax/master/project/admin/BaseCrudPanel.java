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
package org.magmax.master.project.admin;

import javax.swing.JPanel;
import org.magmax.eswing.crud.CrudObject;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public abstract class BaseCrudPanel<T extends CrudObject> extends JPanel{
    public abstract String getTitleCreation();

    public abstract String getTitleUpdate();

    public abstract String getTitleDetails();

    public abstract T getCrudObject();

    public abstract void setCrudObject(T item);

    public abstract void updateCrudObject(T item);

    public abstract void setWidgetsEnabled(boolean b);
}
