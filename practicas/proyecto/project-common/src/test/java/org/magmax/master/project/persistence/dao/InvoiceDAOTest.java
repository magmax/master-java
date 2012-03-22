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
package org.magmax.master.project.persistence.dao;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import org.magmax.master.project.persistence.pojo.Invoice;
import org.magmax.master.project.persistence.pojo.Product;
import org.magmax.master.project.persistence.pojo.SoldProduct;
import org.magmax.master.project.persistence.pojo.User;

/**
 *
 * @author Miguel Angel Garcia<miguelangel.garcia@gmail.com>
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

    private User createUser(String name) {
        User user = new User();
        user.setName(name);
        UserDAO userdao = factory.getUserDAO();
        userdao.storeAndRefresh(user);
        return user;
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
        User user = createUser("Max Cavalera");
        invoice.setUser(user);

        sut.storeAndRefresh(invoice);

        Invoice current = sut.findById(invoice.getId());
        assertEquals(user.getName(), current.getUser().getName());
    }

    @Test
    public void testGivenAnUserAndAListOfProductsBuildsTheInvoice() {
        User user = createUser("Max Cavalera");
        invoice.setUser(user);

        Product product = new Product();
        product.setName("product 1");
        product.setPrize(200F);
        factory.getProductDAO().storeAndRefresh(product);

        ArrayList<Product> products = new ArrayList<Product>();
        products.add(product);

        Invoice invoice = sut.createInvoice(user, products);

        assertNotNull(invoice);
        assertNotNull(invoice.getId());
        assertNotNull(invoice.getProducts());
        assertEquals(1, invoice.getProducts().size());
        assertEquals(1, sut.findAll().size());
    }

    @Test
    public void testGivenAnUserAndAListOfProductsBuildsTheInvoice2() {
        User user = createUser("Max Cavalera");
        invoice.setUser(user);

        Product product = new Product();
        product.setName("product 1");
        product.setPrize(200F);
        factory.getProductDAO().storeAndRefresh(product);

        Product product2 = new Product();
        product2.setName("product 2");
        product2.setPrize(200F);
        factory.getProductDAO().storeAndRefresh(product2);


        ArrayList<Product> products = new ArrayList<Product>();
        products.add(product);
        products.add(product2);

        Invoice invoice = sut.createInvoice(user, products);

        assertNotNull(invoice);
        assertNotNull(invoice.getId());
        assertNotNull(invoice.getProducts());
        assertEquals(2, invoice.getProducts().size());
        assertEquals(1, sut.findAll().size());
    }

    @Test
    public void testGivenAnUserAndAListOfProductsBuildsTheInvoice3() {
        User user = createUser("Max Cavalera");
        invoice.setUser(user);

        Product product = new Product();
        product.setName("product 1");
        product.setPrize(200F);
        factory.getProductDAO().storeAndRefresh(product);

        ArrayList<Product> products = new ArrayList<Product>();
        products.add(product);
        products.add(product);

        invoice = sut.createInvoice(user, products);

        assertNotNull(invoice);
        assertNotNull(invoice.getId());
        assertNotNull(invoice.getProducts());
        assertEquals(1, invoice.getProducts().size());
        assertEquals(2, invoice.getProducts().get(0).getUnits().intValue());
        assertEquals(1, sut.findAll().size());
    }

    @Test
    public void testFindByDates() {
        sut.storeAndRefresh(invoice);

        List<Invoice> current = sut.findByDates(getDate(1999, 1, 1), getDate(2150, 1, 1));

        assertEquals(1, current.size());
        assertEquals(invoice.getId(), current.get(0).getId());
    }

    @Test
    public void testFindByDatesLowPeriod() {
        sut.storeAndRefresh(invoice);

        List<Invoice> current = sut.findByDates(getDate(1999, 1, 1), getDate(2000, 1, 1));

        assertEquals(0, current.size());
    }

    @Test
    public void testFindByDatesHighPeriod() {
        sut.storeAndRefresh(invoice);

        List<Invoice> current = sut.findByDates(getDate(2150, 1, 1), getDate(2151, 1, 1));

        assertEquals(0, current.size());
    }

    private Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.clear();

        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DATE, day);
        return cal.getTime();
    }
}
