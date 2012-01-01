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
import org.codehaus.plexus.util.StringInputStream;
import org.junit.Before;
import org.junit.Test;
import org.magmax.master.practica6.configuration.CityCode;
import static org.junit.Assert.*;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class MarshallerTest {

    private Marshaller sut;

    @Before
    public void setUp() {
        sut = new Marshaller();
    }

    @Test
    public void testSerializeRequest() {
        Request request = new Request();
        request.setCitycode(CityCode.BURGOS);
        assertArrayEquals("10".getBytes(), sut.serialize(request));
    }

    @Test
    public void testSerializeReturnsCorrectFrameForNoTemperatures(){
        Response response = new Response();
        response.setCitycode(CityCode.BURGOS);
        assertArrayEquals("ini|fin".getBytes(), sut.serialize(response));
    }
    
    @Test
    public void testSerializeResponse() {
        Response response = new Response();
        response.setCitycode(CityCode.TARRAGONA);
        response.addTemperature((float) 34.5);
        assertArrayEquals("ini|44|34.5|fin".getBytes(), sut.serialize(response));
    }
    
    @Test
    public void testSerializeResponseWithTwoTemperatures() {
        Response response = new Response();
        response.setCitycode(CityCode.TARRAGONA);
        response.addTemperature((float) 31.2);
        response.addTemperature((float) 35.8);
        assertArrayEquals("ini|44|31.2|35.8|fin".getBytes(), sut.serialize(response));
    }
    
    @Test
    public void testUnserializeRequest() throws Exception {
        Request result = sut.unserialize_request(new StringInputStream("16"));
        assertEquals(CityCode.CIUDAD_REAL, result.getCitycode());
    }
    
    @Test
    public void testUnserializeEmptyResponse() throws IOException, Exception {
        CityCode city = CityCode.ALAVA;
        Response result = sut.unserialize_response(new StringInputStream("ini|fin"));
        assertEquals("Cities are diferent", null, result.getCitycode());
        assertEquals("Temperatures are diferent", 0, result.getTemperatures().length);
    }
}
