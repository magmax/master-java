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
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
        
/**
 *
 * @author miguel
 */
public class RedirectorTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Redirector sut;
    private RequestDispatcher dispatcher;
    
    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        sut = new Redirector(request, response);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testIsCreated() {
        assertNotNull(sut);
    }
    
    @Test
    public void testIsValid(){
        assertTrue(sut.isValid());
    }
    
    @Test
    public void testIsNotValidIfRequestIsNull(){
        sut = new Redirector(null, response);
        
        assertFalse(sut.isValid());
    }
    
    @Test
    public void testIsNotValidIfResponseIsNull(){
        sut = new Redirector(request, null);
        
        assertFalse(sut.isValid());
    }
    
    @Test
    public void testRedirections() throws ServletException, IOException {
        when(request.getRequestDispatcher(JspPage.CREATE.getUri())).thenReturn(dispatcher);
        
        sut.redirect(JspPage.CREATE);
        
        verify(request).getRequestDispatcher(JspPage.CREATE.getUri());
    }
    
    @Test
    public void testRedirectionWithAttributes() throws ServletException, IOException {
        Object object = mock(Object.class);
        String key = "object";
        when(request.getRequestDispatcher(JspPage.CREATE.getUri())).thenReturn(dispatcher);
        
        sut.addAttribute(key, object);
        sut.redirect(JspPage.CREATE);
        
        verify(request).setAttribute(key, object);
        verify(request).getRequestDispatcher(JspPage.CREATE.getUri());
    }
}
