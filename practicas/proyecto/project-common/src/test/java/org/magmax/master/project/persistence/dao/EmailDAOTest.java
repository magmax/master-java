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

import java.util.Collection;
import org.magmax.master.project.persistence.pojo.User;
import org.magmax.master.project.persistence.pojo.Email;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class EmailDAOTest {
    public static final String DEFAULT_EMAIL = "example@example.com";

    private EmailDAO sut;
    private Email email;

    @Before
    public void setUp() {
        email = new Email();
        email.setAddress(DEFAULT_EMAIL);
        sut = new EmailDAO("development");
    }

    @Test
    public void testCreation() {
        sut.store(email);

        sut.refresh(email);
        assertNotNull(email.getId());
    }

    @Test
    public void testAnEmailIsAssociatedToAnUser() {
        User user = new User();

        email.setUser(user);

        sut.store(email);
        sut.refresh(email);

        assertNotNull(email.getId());
    }
    
    @Test
    public void testCanFindAnEmail() {
        sut.store(email);
        
        Email result = sut.findById(email.getId());
        assertEquals(email.getAddress(), result.getAddress());
    }
    
    @Test
    @Ignore("Revisar cuando se ajuste correctamente la clase GenericDAO")
    public void testCanFindAllEmails() {
        sut.store(email);
        
        Collection<Email> emails = sut.findAll();
        
        assertEquals(1, emails.size());
    }
}
