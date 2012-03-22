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

import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class DAOFactory {

    private static String DEFAULT_DATA_ORIGIN = "development";
    private final EntityManagerFactory emFactory;
    private final EntityManager entityManager;

    public DAOFactory() {
        this(DEFAULT_DATA_ORIGIN);
    }

    public DAOFactory(String origin) {
        emFactory = Persistence.createEntityManagerFactory(origin);
        entityManager = emFactory.createEntityManager();
    }

    public DAOFactory(String origin, Map properties) {
        emFactory = Persistence.createEntityManagerFactory(origin, properties);
        entityManager = emFactory.createEntityManager();
    }

    protected void destroy() {
        entityManager.close();
        emFactory.close();
    }

    public UserDAO getUserDAO() {
        return new UserDAO(entityManager);
    }

    public EmailDAO getEmailDAO() {
        return new EmailDAO(entityManager);
    }

    public PhoneDAO getPhoneDAO() {
        return new PhoneDAO(entityManager);
    }

    public SectionDAO getSectionDAO() {
        return new SectionDAO(entityManager);
    }

    public ProductDAO getProductDAO() {
        return new ProductDAO(entityManager);
    }

    public SoldProductDAO getSoldProductDAO() {
        return new SoldProductDAO(entityManager);
    }

    public InvoiceDAO getInvoiceDAO() {
        return new InvoiceDAO(entityManager);
    }
}
