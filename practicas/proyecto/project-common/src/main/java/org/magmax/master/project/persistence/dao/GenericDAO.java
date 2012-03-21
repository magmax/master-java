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

import org.magmax.master.project.persistence.pojo.GenericEntity;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class GenericDAO<T extends GenericEntity<I>, I extends Serializable> {

    private final EntityManager entityManager;
    private final Class<T> persistentClass;

    public GenericDAO(EntityManager entityManager) {
        persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.entityManager = entityManager;
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public void store(T object) {
        try {
            entityManager.getTransaction().begin();
            if (object.getId() == null) {
                entityManager.persist(object);
            } else {
                entityManager.merge(object);
            }
        } finally {
            entityManager.getTransaction().commit();
        }
    }

    public void storeAndRefresh(T object) {
        store(object);
        refresh(object);
    }

    public void delete(T object) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(object);
        } finally {
            entityManager.getTransaction().commit();
        }
    }

    public T findById(I id) {
        return (T) entityManager.find(persistentClass, id);
    }

    public List<T> findAll() {
        EntityManager em = getEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(persistentClass);
        criteria.from( persistentClass );
        return em.createQuery( criteria ).getResultList();
    }

    public void refresh(T object) {
        entityManager.refresh(object);
    }
    
    public List<T> findByIds(I[] idList) {
        List<T> result = new ArrayList<T>();
        for (I each : idList) {
            result.add(findById(each));
        }
        return result;
    }
    
    public List<T> findByIds(Collection<I> ids) {
        List<T> result = new ArrayList<T>();
        for (I each : ids) {
            result.add(findById(each));
        }
        return result;
    }
}
