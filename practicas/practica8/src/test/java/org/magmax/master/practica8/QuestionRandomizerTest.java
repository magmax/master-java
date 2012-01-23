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
import org.magmax.master.practica8.pojo.Question;

/**
 *
 * @author miguel
 */
public class QuestionRandomizerTest {
    private QuestionRandomizer sut;
    private Question question1;
    private Question question2;

    @Before
    public void setUp() {
        question1 = new Question();
        question2 = new Question();
        sut = new QuestionRandomizer();
    }
    
    @Test
    public void testCompareQuestion1isHigher() {
        question1.setLevel(1);
        question2.setLevel(2);
        int result = sut.compare(question1, question2);
        assertEquals(1, result);
    }
    
    @Test
    public void testCompareQuestion2isHigher() {
        question1.setLevel(3);
        question2.setLevel(2);
        int result = sut.compare(question1, question2);
        assertEquals(-1, result);
    }
}
