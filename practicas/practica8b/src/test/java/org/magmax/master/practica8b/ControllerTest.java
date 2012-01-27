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

/**
 *
 * @author miguel
 */
public class ControllerTest {
    private Domain domain;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Controller sut;
    
    @Before
    public void setUp() {
        domain = mock(Domain.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        Configuration.getInstance().setDomain(domain);
        sut = new Controller();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testASimpleCallReturnsIndexUnderGet() throws ServletException, IOException {
        sut.doGet(request, response);
        verify(domain).redirect(request, "/create_exam.jsp");
    }

    
    @Test
    public void testASimpleCallReturnsIndexUnderPost() throws ServletException, IOException {
        sut.doPost(request, response);
        verify(domain).redirect(request, "/create_exam.jsp");
    }
    
    @Test
    public void testASimpleCallSetsCharset() throws ServletException, IOException {
        sut.doPost(request, response);
        verify(response).setContentType("text/html;charset=UTF-8");
    }
}