/*
 * Copyright (C) 2011 Miguel Angel Garcia <miguelangel.garcia@gmail.com>
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
package org.magmax.master.practica6.server;

import org.magmax.master.practica6.configuration.CityCode;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class CityCodeTest {

    @Test
    public void testToledoHasCode46() {
        assertEquals(46, CityCode.TOLEDO.code);
    }

    @Test
    public void testCanRetrieveToledoFromCode() throws Exception {
        assertEquals(CityCode.TOLEDO, CityCode.getFromCode(46));
    }
}
