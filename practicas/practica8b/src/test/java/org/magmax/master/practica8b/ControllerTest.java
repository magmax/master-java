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
import org.magmax.master.practica8b.pojo.Issue;

public class ControllerTest {

    private Domain domain;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Controller sut;
    private Redirector redirector;
    private Persistence persistence;

    @Before
    public void setUp() throws ServletException, ClassNotFoundException, DriverNotDefinedException {
        redirector = mock(Redirector.class);
        domain = mock(Domain.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        persistence = mock(Persistence.class);

        Configuration.getInstance().setDomain(domain);
        when(domain.getRedirector()).thenReturn(redirector);
        when(domain.getPersistence()).thenReturn(persistence);

        sut = new Controller();
        sut.init();
        sut.setDomain(domain);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testRetrieveServletInfo() {
        assertEquals("This is a servlet for practice 8 of a Java2EE Master.", sut.getServletInfo());
    }

    @Test(expected=ServletException.class)
    public void testASimpleCallReturnsErrorUnderGet() throws ServletException, IOException {
        sut.doGet(request, response);
    }

    @Test(expected=ServletException.class)
    public void testASimpleCallReturnsErrorUnderPost() throws ServletException, IOException {
        sut.doPost(request, response);
    }

    @Test
    public void testKnowsHowToGenerateIndexWhenNoIssues() throws Exception {
        Issue[] issues = new Issue[0];
        when(persistence.getAllIssues()).thenReturn(issues);

        sut.loadNextPage(request, response);

        verify(redirector, times(1)).redirect(JspPage.CREATE);
        verify(redirector).addAttribute("issue_list", issues);
        verify(persistence).getAllIssues();
    }

    @Test
    public void testASimpleCallSetsCharset() throws Exception {
        Issue[] issues = new Issue[0];
        when(persistence.getAllIssues()).thenReturn(issues);

        sut.doPost(request, response);
        
        verify(response).setContentType("text/html;charset=UTF-8");
    }

    @Test(expected=ServletException.class)
    public void testFailsWhenNoPersistence() throws Exception {
        when (domain.getPersistence()).thenReturn(null);
        
        sut.loadNextPage(request, response);
    }
    
    @Test
    public void testRedirectsToNewExamWhenIssueAndLevelAreSet() throws Exception {
        when(request.getParameter("issue")).thenReturn("1");
        when(request.getParameter("level")).thenReturn("1");
        
        sut.loadNextPage(request, response);
        
        verify(redirector, times(1)).redirect(JspPage.EXAM);
        verify(persistence).retrieveQuestions(1, 1);
    }
    
    @Test
    public void testRedirectsToShowExamWhenIssueAndLevelAreStoredAndEqualToNewOnes() throws Exception {
        when(request.getParameter("issue")).thenReturn("1");
        when(request.getParameter("level")).thenReturn("1");
        when(domain.getContextParameter("issue")).thenReturn("1");
        when(domain.getContextParameter("level")).thenReturn("1");
        
        sut.loadNextPage(request, response);
        
        verify(redirector, times(1)).redirect(JspPage.RESULT);
        verify(persistence, times(0)).retrieveQuestions(anyInt(), anyInt());
    }
}