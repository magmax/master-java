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
package org.magmax.master.project.persistence.pojo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
@Entity
public class Invoice implements GenericEntity<Integer> {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne(targetEntity = User.class)
    private User user;
    @OneToMany(targetEntity=SoldProduct.class, mappedBy="invoice")
    private List<SoldProduct> products;
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<SoldProduct> getProducts() {
        if (products == null)
            products = new ArrayList<SoldProduct>();
        return products;
    }

    public void setProducts(List<SoldProduct> products) {
        this.products = products;
    }
    
    public void addProduct(SoldProduct product) {
        if (products == null)
            products = new ArrayList<SoldProduct>();
        products.add(product);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
