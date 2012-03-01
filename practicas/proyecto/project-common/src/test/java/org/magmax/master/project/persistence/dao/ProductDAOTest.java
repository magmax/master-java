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
import org.magmax.master.project.persistence.pojo.Product;
import org.magmax.master.project.persistence.pojo.Section;

/**
 *
 * @author miguel
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
        
        sut.store(product);
        sut.refresh(product);
        
        Product current = sut.findById(product.getId());
        assertEquals(section.getName(), current.getSection().getName());
    }
}
