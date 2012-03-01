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
package org.magmax.master.project.persistence.dao;

import org.junit.*;
import static org.junit.Assert.*;
import org.magmax.master.project.persistence.pojo.Invoice;
import org.magmax.master.project.persistence.pojo.Product;
import org.magmax.master.project.persistence.pojo.SoldProduct;
import org.magmax.master.project.persistence.pojo.User;

/**
 *
 * @author miguel
 */
public class InvoiceDAOTest {

    private InvoiceDAO sut;
    private Invoice invoice;
    private DAOFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = new DAOFactory("development");
        invoice = new Invoice();
        sut = factory.getInvoiceDAO();
    }

    @Test
    public void testCreation() {
        sut.store(invoice);
        sut.refresh(invoice);

        assertNotNull(invoice.getId());
    }

    @Test
    public void testDateIsFilled() {
        sut.storeAndRefresh(invoice);

        assertNotNull(invoice.getDate());
    }

    @Test
    public void testCanHaveASoldProduct() {
        Product product = new Product();
        product.setName("T-shirt");
        SoldProduct soldproduct = new SoldProduct();
        soldproduct.setProduct(product);
        invoice.addProduct(soldproduct);
        
        sut.storeAndRefresh(invoice);
        
        Invoice current = sut.findById(invoice.getId());

        assertEquals("Invoice must have a product", 1, current.getProducts().size());
        assertEquals(product.getName(), current.getProducts().get(0).getProduct().getName());
    }

    @Test
    public void testCanHaveAnUser() {
        User user = new User();
        user.setName("Max Cavalera");
        invoice.setUser(user);
        
        sut.storeAndRefresh(invoice);
        
        Invoice current = sut.findById(invoice.getId());
        assertEquals(user.getName(), current.getUser().getName());
    }
}
