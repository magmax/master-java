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
package org.magmax.master.project.ui.shop;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.magmax.master.project.persistence.pojo.Product;
import org.magmax.master.project.persistence.pojo.Section;
import org.magmax.master.project.ui.Helpers.CommonHelper;
import org.magmax.master.project.ui.persistence.Persistence;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class ProductList extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        System.out.println(request.getParameter("sectionid"));

        if (request.getParameter("sectionid") == null) {
            return null;
        }

        request.setAttribute("product_list", retrievePrices(request.getParameter("sectionid")));

        return mapping.findForward(SUCCESS);
    }

    private List<ProductForm> retrievePrices(String sectionId) {
        return retrievePrices(Integer.valueOf(sectionId));
    }

    private List<ProductForm> retrievePrices(Integer sectionId) {
        List<ProductForm> result = new ArrayList<ProductForm>();
        Section section = Persistence.getInstance().getSectionDAO().findById(sectionId);

        System.out.println(section);

        if (section == null || section.getProducts() == null) {
            return result;
        }
        for (Product each : section.getProducts()) {
            ProductForm product = new ProductForm();
            product.setId(each.getId());
            product.setName(each.getName());
            product.setDescription(each.getDescription());
            product.setPrice(each.getPrize());
            result.add(product);
        }

        System.out.println(result.size());

        return result;
    }
}
