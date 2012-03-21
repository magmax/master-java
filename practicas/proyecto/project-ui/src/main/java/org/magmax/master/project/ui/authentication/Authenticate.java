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
package org.magmax.master.project.ui.authentication;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.magmax.master.project.persistence.pojo.Section;
import org.magmax.master.project.persistence.pojo.User;
import org.magmax.master.project.ui.Helpers.UserHelper;
import org.magmax.master.project.ui.persistence.Persistence;
import org.magmax.master.project.ui.shop.SectionForm;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class Authenticate extends org.apache.struts.action.Action {

    /*
     * forward name="success" path=""
     */
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Persistence persistence = Persistence.getInstance(servlet.getServletContext());
        User user = persistence.getUserDAO().findByCredentials(request.getParameter("username"), request.getParameter("password"));
        if (user == null) {
            ActionMessages messages = new ActionErrors();
            messages.add("usernotfound", new ActionMessage("warning.user.notfound"));
            addMessages(request, messages);
            return mapping.findForward(ERROR);
        }

        UserHelper userhelper = new UserHelper(getServlet());
        userhelper.save(user);

        request.setAttribute("section_list", retrieveSections());

        return mapping.findForward(SUCCESS);
    }

    private List<SectionForm> retrieveSections() {
        List<SectionForm> result = new ArrayList<SectionForm>();
        for (Section each : Persistence.getInstance(servlet.getServletContext()).getSectionDAO().findAll()) {
            SectionForm section = new SectionForm();
            section.setName(each.getName());
            section.setId(each.getId());
            result.add(section);
        }
        return result;
    }
}
