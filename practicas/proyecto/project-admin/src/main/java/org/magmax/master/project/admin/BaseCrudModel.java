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
package org.magmax.master.project.admin;

import org.magmax.master.project.admin.section.*;
import java.util.List;
import org.magmax.eswing.crud.DefaultCrudModel;
import org.magmax.master.project.admin.Persistence;
import org.magmax.master.project.persistence.dao.SectionDAO;
import org.magmax.master.project.persistence.pojo.Section;

/**
 *
 * @author miguel
 */
public class BaseCrudModel extends DefaultCrudModel<SectionRow> {

    private static String[] headers = new String[]{"Nombre"};

    public BaseCrudModel() {
        super();
        setColumnIdentifiers(headers);
    }

    @Override
    public void add(SectionRow item) {
        saveItem(item);
        super.add(item);
    }

    @Override
    public void load() {
        for (Section each : getDAO().findAll()) {
            SectionRow row = new SectionRow();
            row.setEntity(each);
            super.add(row);
        }
        super.load();
    }

    @Override
    public void remove(List<SectionRow> data) {
        for(SectionRow each : data) {
            getDAO().delete(each.getEntity());
        }
        super.remove(data);
    }

    @Override
    public void update(SectionRow item) {
        saveItem(item);
        super.update(item);
    }

    private void saveItem(SectionRow sectionrow) {
        getDAO().store(sectionrow.getEntity());
    }

    private SectionDAO getDAO() {
        return Persistence.getInstance().getSectionDAO();
    }
}
