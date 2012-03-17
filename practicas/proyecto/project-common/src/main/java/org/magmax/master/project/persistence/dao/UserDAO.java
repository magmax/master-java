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

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.magmax.master.project.persistence.pojo.User;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class UserDAO extends GenericDAO<User, Integer> {

    public UserDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public User findByCredentials(String username, String password) {
        /*
        EntityManager em = getEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.where(builder.and(
        builder.equal(root.get(User_.name), username),
        builder.equal(root.get(User_.password), password)));
        List<User> list = em.createQuery(criteria).getResultList();
        if (list == null || list.isEmpty()) {
        return null;
        }
        return list.get(0);
         */

        EntityManager em = getEntityManager();
        Query query = em.createQuery("select user from User as user where user.name = ?1 and user.password = ?2", User.class);
        query.setParameter(1, username);
        query.setParameter(2, password);
        List<User> result = query.getResultList();
        if (result.isEmpty())
            return null;
        return result.get(0);
    }
}
