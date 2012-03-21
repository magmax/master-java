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
package org.magmax.master.project.persistence.dao;

import java.sql.Date;
import java.util.Calendar;
import java.util.Collection;
import javax.persistence.EntityManager;
import org.magmax.master.project.persistence.pojo.Invoice;
import org.magmax.master.project.persistence.pojo.Product;
import org.magmax.master.project.persistence.pojo.SoldProduct;
import org.magmax.master.project.persistence.pojo.User;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class InvoiceDAO extends GenericDAO<Invoice, Integer> {

    public InvoiceDAO(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void store(Invoice invoice) {
        SoldProductDAO soldproductdao = new SoldProductDAO(getEntityManager());
        invoice.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
        User user = invoice.getUser();
        if (user != null) {
            UserDAO userdao = new UserDAO(getEntityManager());
            userdao.storeAndRefresh(user);
        }
        super.store(invoice);
        for (SoldProduct each : invoice.getProducts()) {
            each.setInvoice(invoice);
            soldproductdao.storeAndRefresh(each);
        }
    }

    public Invoice createInvoice(User user, Collection<Product> products) {
        SoldProductDAO soldproductdao = new SoldProductDAO(getEntityManager());
        ProductDAO productDAO = new ProductDAO(getEntityManager());
        Invoice invoice = new Invoice();

        invoice.setUser(user);
        invoice.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
        for (Product product : products) {
            SoldProduct sold = findProduct(invoice, product);
            if (sold == null) {
                sold = new SoldProduct();
                sold.setProduct(product);
                sold.setPrizePerUnit(product.getPrize());
                sold.setUnits(1);
            } else {
                sold.setUnits(sold.getUnits() + 1);
            }
            soldproductdao.storeAndRefresh(sold);
            invoice.addProduct(sold);
        }

        super.storeAndRefresh(invoice);

        return invoice;
    }

    private SoldProduct findProduct(Invoice invoice, Product product) {
        for (SoldProduct each : invoice.getProducts()) {
            if (each.getProduct().getId() == product.getId()) {
                return each;
            }
        }
        return null;
    }
}
