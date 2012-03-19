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

import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import org.magmax.master.project.persistence.pojo.Product;
import org.magmax.master.project.persistence.pojo.Section;

/**
 *
 * @author Miguel Angel Garcia<miguelangel.garcia@gmail.com>
 */
public class ProductDAOTest {

    private ProductDAO sut;
    private Product product;
    private DAOFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = new DAOFactory("development");
        product = new Product();
        product.setName("Vinilo");
        sut = factory.getProductDAO();
    }

    @Test
    public void testCreation() {
        sut.store(product);
        sut.refresh(product);

        assertNotNull(product.getId());
    }

    @Test
    public void testCanHaveASection() {
        Section section = new Section();
        section.setName("Heavy Metal");
        product.setSection(section);

        sut.storeAndRefresh(product);

        Product current = sut.findById(product.getId());
        assertEquals(section.getName(), current.getSection().getName());
    }

    @Test
    public void testRetrieveByName() {
        sut.storeAndRefresh(product);

        Product current = sut.findByName(product.getName());

        assertEquals(product.getId(), current.getId());
    }

    @Test
    public void testRetrieveByArrayOfIds() {
        sut.storeAndRefresh(product);

        List<Product> current = sut.findByIds(new Integer[]{product.getId()});
    
        assertEquals(1, current.size());
        assertEquals(product, current.get(0));
    }
    
    @Test
    public void testRetrieveByListOfIds() {
        sut.storeAndRefresh(product);
        
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(product.getId());
        
        List<Product> current = sut.findByIds(ids);
        
        assertEquals(1, current.size());
        assertEquals(product, current.get(0));
    }
}
