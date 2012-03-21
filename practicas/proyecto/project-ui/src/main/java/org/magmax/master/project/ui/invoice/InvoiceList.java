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
package org.magmax.master.project.ui.invoice;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.magmax.master.project.persistence.pojo.Invoice;
import org.magmax.master.project.persistence.pojo.SoldProduct;
import org.magmax.master.project.ui.Helpers.UserHelper;
import org.magmax.master.project.ui.persistence.Persistence;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class InvoiceList extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        UserHelper userhelper = new UserHelper(servlet);
        
        request.setAttribute("invoice_list", retrieveSoldProducts());
        
        return mapping.findForward(SUCCESS);
    }

    private List<InvoiceForm> retrieveSoldProducts() {
        List<InvoiceForm> forms = new ArrayList<InvoiceForm>();
        
        for (SoldProduct each : Persistence.getInstance().getSoldProductDAO().findAll()) {
            Invoice invoice = each.getInvoice();
            InvoiceForm iform = new InvoiceForm();
            iform.setDate(invoice.getDate());
            iform.setUsername(invoice.getUser().getName());
            iform.setProductName(each.getProduct().getName());
            iform.setPrice(each.getPrizePerUnit());
            iform.setUnits(each.getUnits());
            forms.add(iform);
        }
        
        return forms;
    }
}
