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
package org.magmax.master.practica6.communication;

import java.io.IOException;
import java.io.StringBufferInputStream;
import org.junit.Before;
import org.junit.Test;
import org.magmax.master.practica6.configuration.CityCode;
import static org.junit.Assert.*;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class ResponseParserTest {
    private ResponseParser sut;

    @Before
    public void setUp() {
        sut = new ResponseParser();

    }

    @Test
    public void testEmptyValue() throws IOException, Exception {
        sut.parse(new StringBufferInputStream("ini|fin"));
        assertEquals(0, sut.getResponse().getTemperatures().length);
    }
    
    @Test
    public void testOneTemperature() throws IOException, Exception {
        sut.parse(new StringBufferInputStream("ini|23|74.2|fin"));
        assertEquals(CityCode.HUELVA, sut.getResponse().getCitycode());
        assertArrayEquals(new Float[] {(float)74.2}, sut.getResponse().getTemperatures());
    }
}
