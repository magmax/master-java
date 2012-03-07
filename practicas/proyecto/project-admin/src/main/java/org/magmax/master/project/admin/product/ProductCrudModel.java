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
package org.magmax.master.project.admin.product;

import java.util.List;
import org.magmax.eswing.crud.DefaultCrudModel;
import org.magmax.master.project.admin.Persistence;
import org.magmax.master.project.persistence.dao.ProductDAO;
import org.magmax.master.project.persistence.pojo.Product;

/**
 *
 * @author miguel
 */
public class ProductCrudModel extends DefaultCrudModel<ProductRow> {

    private static String[] headers = new String[]{"Producto", "Descripción", "Precio"};

    public ProductCrudModel() {
        super();
        setColumnIdentifiers(headers);
    }

    @Override
    public void add(ProductRow item) {
        getDAO().store(item.getEntity());
        super.add(item);
    }

    @Override
    public void load() {
        for (Product each : getDAO().findAll()) {
            ProductRow row = new ProductRow();
            row.setEntity(each);
            super.add(row);
        }

        super.load();
    }

    @Override
    public void remove(List<ProductRow> data) {
        for (ProductRow each : data) {
            getDAO().delete(each.getEntity());
        }
        super.remove(data);
    }

    @Override
    public void update(ProductRow item) {
        getDAO().store(item.getEntity());
        super.update(item);
    }

    private ProductDAO getDAO() {
        return Persistence.getInstance().getProductDAO();
    }
}
