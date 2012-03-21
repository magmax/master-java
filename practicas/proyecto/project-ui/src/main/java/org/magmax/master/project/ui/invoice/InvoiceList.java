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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        Date from = getFrom(request);
        Date to = getTo(request);

        if (from == null || to == null) {
            return null;
        }

        request.setAttribute("invoice_list", retrieveSoldProducts(from, to));

        return mapping.findForward(SUCCESS);
    }

    private List<InvoiceForm> retrieveSoldProducts(Date from, Date to) {
        List<InvoiceForm> forms = new ArrayList<InvoiceForm>();

        for (Invoice invoice : Persistence.getInstance(servlet.getServletContext()).getInvoiceDAO().findByDates(from, to)) {
            for (SoldProduct each : invoice.getProducts()) {
                InvoiceForm iform = new InvoiceForm();
                iform.setId(invoice.getId());
                iform.setDate(invoice.getDate());
                iform.setUsername(invoice.getUser().getName());
                iform.setProductName(each.getProduct().getName());
                iform.setPrice(each.getPrizePerUnit());
                iform.setUnits(each.getUnits());
                forms.add(iform);
            }
        }
        return forms;
    }

    private Date getFrom(HttpServletRequest request) {
        return getDate(request.getParameter("from"));
    }

    private Date getTo(HttpServletRequest request) {
        return getDate(request.getParameter("to"));
    }

    private Date getDate(String param) {
        if (param == null) {
            return null;
        }

        try {
            DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            return format.parse(param);
        } catch (ParseException ex) {
            return null;
        }
    }
}
