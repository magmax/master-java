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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.magmax.master.practica8b.pojo.Issue;
import sun.security.krb5.Credentials;

/**
 *
 * @author miguel
 */
public class DomainTest {

    private Domain sut;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Controller controller;

    @Before
    public void setUp() {
        Configuration.reset();

        sut = new Domain();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        controller = mock(Controller.class);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testRequestCanBeRetrieved() {
        sut.setRequest(request);
        assertEquals(request, sut.getRequest());
    }

    @Test
    public void testResponseCanBeRetrieved() {
        sut.setResponse(response);
        assertEquals(response, sut.getResponse());
    }

    @Test
    public void testDoesNotWorkIfThereIsNoRequest() {
        Redirector redirector = sut.getRedirector();
        assertNotNull("Can retreave a Redirector", redirector);
        assertFalse("Redirector is not valid", redirector.isValid());
    }

    @Test
    public void testRedirectionObtain() {
        sut.setRequest(request);
        sut.setResponse(response);
        Redirector redirector = sut.getRedirector();
        assertNotNull("Can retreave a Redirector", redirector);
        assertTrue("Redirector is valid", redirector.isValid());
    }

    @Test(expected = DriverNotDefinedException.class)
    public void testTryingToGetAPersistenceObjectWithoutConfigure() throws ClassNotFoundException, DriverNotDefinedException {
        Configuration.reset();
        sut.getPersistence();
    }

    @Test
    public void testCanRetrieveAPersistenceObject() throws ClassNotFoundException, DriverNotDefinedException {
        DBCredentials credentials = DBCredentials.createWithDefaults();
        Persistence persistence = Persistence.createInstance(credentials);
        assertNotNull(persistence);
        assertTrue(persistence instanceof Persistence);
    }

    @Test
    @Ignore("Estoy viendo como rehacer este test")
    public void testCanRetrieveAGivenContext() {
        String driver = "Killing in the name";
        String url = "You do what you do why they tough ya";
        String user = "I won't do what they told me";
        String password = null;
        ServletContext context = mock(ServletContext.class);
        when(context.getInitParameter("driver")).thenReturn(driver);
        when(context.getInitParameter("uri")).thenReturn(url);
        when(context.getInitParameter("user")).thenReturn(user);
        when(context.getInitParameter("password")).thenReturn(password);
        //sut.setContext(context);

        DBCredentials credentials = sut.getDBCredentials();

        assertEquals(driver, credentials.getDriver());
        assertEquals(url, credentials.getUrl());
        assertEquals(user, credentials.getUser());
        assertEquals("", credentials.getPass());
    }

    @Test
    public void canRetrieveContextParameters() {
        String expected = "Nothing Else Matters";
        String key = "Fuel";

        sut.setController(controller);
        ServletContext servletContext = mock(ServletContext.class);
        when(servletContext.getInitParameter(key)).thenReturn(expected);
        when(controller.getServletContext()).thenReturn(servletContext);

        String actual = sut.getContextParameter(key);
        assertEquals(expected, actual);
        verify(servletContext).getInitParameter(key);
    }

    @Test
    public void canRetrieveContextParametersThatCannotBeNull() {
        String key = "Fuel";

        sut.setController(controller);
        ServletContext servletContext = mock(ServletContext.class);
        when(servletContext.getInitParameter(key)).thenReturn(null);
        when(controller.getServletContext()).thenReturn(servletContext);

        String actual = sut.getContextParameter(key);
        assertEquals("", actual);
        verify(servletContext).getInitParameter(key);
    }
}
