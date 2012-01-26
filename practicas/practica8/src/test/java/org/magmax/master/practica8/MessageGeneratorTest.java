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
package org.magmax.master.practica8;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author miguel
 */
public class MessageGeneratorTest {

    private MessageGenerator sut;

    @Before
    public void setUp() {
        sut = new MessageGenerator();
    }

    private String getMessage(Level level, int punctuation) {
        sut.setLevel(level);
        sut.setPunctuation(punctuation);
        return sut.getMessage();
    }

    @Test
    public void getMessageForLowLevelAnd0() {
        String message = getMessage(Level.Low, 0);

        assertEquals("Nulos conocimientos", message);
    }
    
    
    @Test
    public void getMessageForLowLevelAnd1() {
        String message = getMessage(Level.Low, 1);

        assertEquals("Nulos conocimientos", message);
    }
    
    
    @Test
    public void getMessageForLowLevelAnd2() {
        String message = getMessage(Level.Low, 2);

        assertEquals("Muy bajos conocimientos", message);
    }
    
    
    @Test
    public void getMessageForLowLevelAnd5() {
        String message = getMessage(Level.Low, 5);

        assertEquals("Parece que tiene buen conocimiento, pruebe dificultad media", message);
    }
    
    
    @Test
    public void getMessageForMedLevelAnd0() {
        String message = getMessage(Level.Medium, 0);

        assertEquals("Muy bajos conocimientos", message);
    }
    
    
    @Test
    public void getMessageForMedLevelAnd5() {
        String message = getMessage(Level.Medium, 5);

        assertEquals("Excelente, pruebe nivel alto", message);
    }
    
    
    @Test
    public void getMessageForHighLevelAnd0() {
        String message = getMessage(Level.High, 0);

        assertEquals("Mejor pruebe en un nivel inferior", message);
    }
    
    
    @Test
    public void getMessageForHighLevelAnd5() {
        String message = getMessage(Level.High, 5);

        assertEquals("Es usted un experto", message);
    }
}
