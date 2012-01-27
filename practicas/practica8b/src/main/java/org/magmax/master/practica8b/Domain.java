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
package org.magmax.master.practica8b;

import javax.servlet.RequestDispatcher;
import org.magmax.master.practica8b.pojo.Issue;

/**
 *
 * @author miguel
 */
class Domain {

    Issue[] getIssueList() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    void redirect(String create_examjsp) {
        throw new UnsupportedOperationException("Not yet implemented");
        /*
         * RequestDispatcher rd = request.getRequestDispatcher("/create_exam.jsp");
        request.setAttribute("issue_list", domain.getIssueList());
        rd.forward(request, response);
         */
    }
    
}
