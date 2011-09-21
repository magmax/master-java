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
package org.magmax.masterjava.practica3.persistence;

import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class PersistenceTest {

    private File fd;
    private Persistence sut;

    @Before
    public void setUp() {
        sut = new Persistence();

        fd = new File("peliculas.dat");
        fd.delete();
    }

    @After
    public void tearDown() {
        fd.delete();
    }

    @Test
    public void testSave() throws Exception {

        assertFalse(fd.exists());
        sut.save("Little Nicky");
        assertTrue(fd.exists());
    }

    @Test
    public void testSaveEmptyStringDoNotSave() throws Exception {

        assertFalse(fd.exists());
        sut.save("");
        assertFalse(fd.exists());
    }

    @Test
    public void testSaveNullStringDoNotSave() throws Exception {

        assertFalse(fd.exists());
        sut.save(null);
        assertFalse(fd.exists());
    }

    @Test
    public void testSaveTwiceIsPossible() throws Exception {

        assertFalse(fd.exists());
        sut.save("Little Nicky");
        sut.save("Top Secret");
        assertTrue(fd.exists());
    }

    @Test
    public void testLoadNonExistentFile() throws Exception {
        String[] films = sut.load();
        assertEquals(0, films.length);
    }

    @Test
    public void testSaveAndThenLoad() throws Exception {
        String film = "El día de la bestia";
        sut.save(film);
        String[] films = sut.load();
        assertEquals(1, films.length);
        assertEquals(film, films[0]);
    }

    @Test
    public void testSave2AndThenLoad() throws Exception {
        String film1 = "Matrix";
        String film2 = "Matrix reloaded";
        sut.save(film1);
        sut.save(film2);
        String[] films = sut.load();
        assertEquals(2, films.length);
        assertEquals(film1, films[0]);
        assertEquals(film2, films[1]);
    }

}
