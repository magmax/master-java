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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class GenericDAO<T extends Object> {

    private static final String DEFAULT_DATA_ORIGIN = "development";
    private final EntityManagerFactory emFactory;
    private final EntityManager entityManager;
    private final String dataOrigin;

    public GenericDAO() {
        this(DEFAULT_DATA_ORIGIN);
    }
    
    public GenericDAO(String origin) {
        emFactory = Persistence.createEntityManagerFactory(origin);
        entityManager = emFactory.createEntityManager();
        dataOrigin = origin;
    }

    public void store(T object) {
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
    }

    public void delete(T object) {
        entityManager.getTransaction().begin();
        entityManager.remove(object);
        entityManager.getTransaction().commit();
    }

    public T getById(Object id) {
        return (T) entityManager.find(getMyClass(), id);
    }

    public Collection<T> getAll() {
        throw new NotImplementedException();
    }

    public void refresh(T object) {
        entityManager.refresh(object);
    }

    protected String getDataOrigin() {
        return dataOrigin;
    }
    
    private Class getMyClass() {
        return this.getClass().getGenericSuperclass().getClass();
    }
}
