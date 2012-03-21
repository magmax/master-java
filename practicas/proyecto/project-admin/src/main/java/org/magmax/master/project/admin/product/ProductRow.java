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
package org.magmax.master.project.admin.product;

import org.magmax.eswing.crud.DefaultCrudObject;
import org.magmax.master.project.persistence.pojo.Product;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class ProductRow extends DefaultCrudObject<Product> {

    @Override
    public Product getEntity() {
        if (null == super.getEntity()) {
            super.setEntity(new Product());
        }
        return super.getEntity();
    }

    @Override
    public Object getValueByColumn(int column) {
        switch (column) {
            case 0:
                return getEntity().getName();
            case 1:
                return getEntity().getDescription();
            case 2:
                return getEntity().getPrize();
            case 3:
                if (getEntity().getSection() != null) {
                    return getEntity().getSection().getName();
                }
                return "";
            default:
                return "";
        }
    }
    
    
}
