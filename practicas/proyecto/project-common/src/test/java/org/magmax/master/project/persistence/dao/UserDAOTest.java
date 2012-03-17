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

import org.magmax.master.project.persistence.pojo.User;
import org.junit.Before;
import org.junit.Test;
import org.magmax.master.project.persistence.pojo.Email;
import org.magmax.master.project.persistence.pojo.Phone;
import static org.junit.Assert.*;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class UserDAOTest {

    private UserDAO sut;
    private User user;
    private DAOFactory factory;

    @Before
    public void setUp() {
        factory = new DAOFactory("development");
        user = new User();
        user.setName("ACDC");
        sut = factory.getUserDAO();
    }

    @Test
    public void testCreation() {
        sut.store(user);

        sut.refresh(user);
        assertNotNull(user.getId());
    }

    @Test
    public void testCanRetrieveEmails() {
        Email email = new Email();
        email.setAddress("example@example.org");
        email.setUser(user);
        EmailDAO emaildao = factory.getEmailDAO();
        emaildao.store(email);

        User current = sut.findById(user.getId());
        assertEquals(1, current.getEmails().size());
        assertEquals(email.getAddress(), current.getEmails().get(0).getAddress());
    }
    
    @Test
    public void testCanRetrievePhones() {
        Phone phone = new Phone();
        phone.setNumber("666555444");
        phone.setUser(user);
        PhoneDAO phonedao = factory.getPhoneDAO();
        phonedao.store(phone);

        User current = sut.findById(user.getId());
        assertEquals(1, current.getPhones().size());
        assertEquals(phone.getNumber(), current.getPhones().get(0).getNumber());
    }
    
    @Test
    public void testAuthenticatedUser() {
        user.setPassword("valid password");
        sut.storeAndRefresh(user);
        
        assertEquals("Authorized user",user, sut.findByCredentials(user.getName(), user.getPassword()));
        assertNull("Not authorized user", sut.findByCredentials(user.getName(), "invalid"));
    }
    
    @Test
    public void testAuthenticateNoUser() {
        assertNull("Not existing user", sut.findByCredentials("invalid", "invalid"));
    }
    
    @Test
    public void testGetByName() {
        sut.storeAndRefresh(user);
        
        User current = sut.findByName(user.getName());
        
        assertEquals (user, current);
    }
}
