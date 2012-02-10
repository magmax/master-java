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

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ConfigurationTest {
    
    @Before
    public void setUp() {
        Configuration.getInstance().reset();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testThereIsOnlyOneInstance() {
        assertSame(Configuration.getInstance(), Configuration.getInstance());
    }
    
    @Test
    public void testCanSetADomain() {
        Domain domain = mock(Domain.class);
        
        Configuration.getInstance().setDomain(domain);
        
        assertSame(domain, Configuration.getInstance().getDomain(null, null, null));
    }
    
    @Test
    public void testCanBuildANewDomain() {
        Configuration.getInstance().setDomain(null);
        
        assertNotNull(Configuration.getInstance().getDomain(null, null, null));
    }

    @Test
    public void testRetrievingTwoDomainsAreDifferent() {
        Configuration.getInstance().setDomain(null);
        
        assertFalse(Configuration.getInstance().getDomain(null, null, null) == Configuration.getInstance().getDomain(null, null, null));
    }
}
