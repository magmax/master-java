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
package org.magmax.masterjava.practica3.game;

import java.lang.Exception;
import java.io.IOException;
import org.magmax.masterjava.practica3.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class GameTest {

    private Game sut;
    private Persistence persistence;

    public GameTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        persistence = mock(Persistence.class);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreation() throws IOException {
        when(persistence.getRandomTitle()).thenReturn("Little Nicky");
        sut = new Game(persistence);
        assertNotNull(sut);
    }

    @Test
    public void testGetGuessString() throws IOException {
        when(persistence.getRandomTitle()).thenReturn("Little Nicky");
        sut = new Game(persistence);

        assertEquals("______ _____", sut.getGuessTitle());
        verify(persistence).getRandomTitle();
    }

    @Test
    public void testGetGuessString2() throws IOException {
        when(persistence.getRandomTitle()).thenReturn("Matrix");
        sut = new Game(persistence);

        assertEquals("______", sut.getGuessTitle());
        verify(persistence).getRandomTitle();
    }

    @Test
    public void testGetGuessStringWithPoints() throws IOException {
        when(persistence.getRandomTitle()).thenReturn("a.b,c:d;e!f?");
        sut = new Game(persistence);

        assertEquals("_._,_:_;_!_?", sut.getGuessTitle());
        verify(persistence).getRandomTitle();
    }

    @Test
    public void testGuessLetter() throws IOException, Exception {
        when(persistence.getRandomTitle()).thenReturn("Matrix");
        sut = new Game(persistence);

        assertEquals(1, sut.tryLetter('a'));
        assertEquals("_a____", sut.getGuessTitle());
    }

    @Test
    public void testGuessShiftLetter() throws IOException, Exception {
        when(persistence.getRandomTitle()).thenReturn("Matrix");
        sut = new Game(persistence);

        assertEquals(1, sut.tryLetter('m'));
        assertEquals("M_____", sut.getGuessTitle());
    }

    @Test
    public void testFinished() throws IOException, Exception {
        when(persistence.getRandomTitle()).thenReturn("Matrix");
        sut = new Game(persistence);

        assertEquals(1, sut.tryLetter('m'));
        assertFalse(sut.solved());
        assertEquals(1, sut.tryLetter('a'));
        assertFalse(sut.solved());
        assertEquals(1, sut.tryLetter('t'));
        assertFalse(sut.solved());
        assertEquals(1, sut.tryLetter('r'));
        assertFalse(sut.solved());
        assertEquals(1, sut.tryLetter('i'));
        assertFalse(sut.solved());
        assertEquals(1, sut.tryLetter('x'));
        assertTrue(sut.solved());
    }

    @Test
    public void test4TriesByDefault() throws IOException {
        when(persistence.getRandomTitle()).thenReturn("Matrix");
        sut = new Game(persistence);

        assertEquals(4, sut.getLeftTries());
    }

    @Test
    public void test3TriesAfterFail() throws IOException, Exception {
        when(persistence.getRandomTitle()).thenReturn("Matrix");
        sut = new Game(persistence);

        sut.tryLetter('z');
        
        assertEquals(3, sut.getLeftTries());
    }

    @Test(expected = Exception.class)
    public void testTryAfterFourFailsLaunchesAnException() throws IOException, Exception {
        when(persistence.getRandomTitle()).thenReturn("Matrix");
        sut = new Game(persistence);

        sut.tryLetter('z');
        sut.tryLetter('z');
        sut.tryLetter('z');
        sut.tryLetter('z');
        assertEquals(0, sut.getLeftTries());
        sut.tryLetter('z');
    }
}
