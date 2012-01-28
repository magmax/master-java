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

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.*;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ControllerTest {

    private Domain domain;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Controller sut;
    private Redirector redirector;

    @Before
    public void setUp() {
        redirector = mock(Redirector.class);
        domain = mock(Domain.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        Configuration.getInstance().setDomain(domain);

        when(domain.getRedirector()).thenReturn(redirector);

        sut = new Controller();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testRetrieveServletInfo() {
        assertEquals("This is a servlet for practice 8 of a Java2EE Master.", sut.getServletInfo());
    }

    @Test
    public void testASimpleCallReturnsIndexUnderGet() throws ServletException, IOException {
        sut.doGet(request, response);
        verify(redirector).redirect(JspPage.CREATE);
    }

    @Test
    public void testASimpleCallReturnsIndexUnderPost() throws ServletException, IOException {
        sut.doPost(request, response);
        verify(redirector).redirect(JspPage.CREATE);
    }

    @Test
    public void testASimpleCallSetsCharset() throws ServletException, IOException {
        sut.doPost(request, response);
        verify(response).setContentType("text/html;charset=UTF-8");
    }
}