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
import java.io.File;
import java.io.FileNotFoundException;
import static org.junit.Assert.*;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class TemperatureTest {

    private Temperature sut;

    @Test
    public void testGetTemperaturesWhenOnlyOnePlaceIsAvailable() throws FileNotFoundException {
        sut = new Temperature(new File("src/test/resources/temperatures001.txt"));
        assertArrayEquals(new Float[]{(float) 22.2}, sut.getTemperatures(CityCode.CIUDAD_REAL));
    }

    @Test
    public void testGetTemperaturesWhenSomePlacesAreAvailable() throws FileNotFoundException {
        sut = new Temperature(new File("src/test/resources/temperatures002.txt"));
        assertArrayEquals(new Float[]{(float) 28.3}, sut.getTemperatures(CityCode.TOLEDO));
    }

    @Test
    public void testGetMoreThanOneTemperature() throws FileNotFoundException {
        sut = new Temperature(new File("src/test/resources/temperatures003.txt"));
        assertArrayEquals(new Float[]{(float) 21.1, (float) 22.2}, sut.getTemperatures(CityCode.TOLEDO));
    }
}
