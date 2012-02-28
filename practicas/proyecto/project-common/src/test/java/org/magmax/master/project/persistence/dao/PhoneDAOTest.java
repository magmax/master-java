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
import org.magmax.master.project.persistence.pojo.Phone;
import org.magmax.master.project.persistence.pojo.User;

/**
 *
 * @author miguel
 */
public class PhoneDAOTest {
    private PhoneDAO sut;
    private Phone phone;
    
    @Before
    public void setUp() {
        sut = new PhoneDAO();
        phone = new Phone();
        phone.setNumber("666 555 444");
    }
    

    @Test
    public void testCreation() {
        sut.store(phone);
        sut.refresh(phone);
        
        assertNotNull(phone.getId());
    }

    @Test
    public void testHasAnUser() {
        User user = new User();
        user.setName("Jim Morrison");
        phone.setUser(user);
        
        sut.store(phone);
        sut.refresh(phone);
        
        Phone retrieved = sut.findById(phone.getId());
        
        assertEquals(retrieved.getUser().getId(), phone.getUser().getId());
       }
}
