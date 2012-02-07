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

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.magmax.master.practica8b.pojo.Question;

/**
 *
 * @author miguel
 */
public class EvaluatorTest {

    private static Question[] exam;
    private Evaluator sut;

    @BeforeClass
    public static void setUpClass() {
        exam = new Question[5];
        for (int i = 0; i < exam.length; ++i) {
            exam[i] = new Question();
            exam[i].setCorrect(1);
        }
    }

    @Before
    public void setUp() {
        sut = new Evaluator(exam);
    }

    @Test
    public void testEvalueateWhenNoneIsRight() {
        assertEquals(0, sut.evaluate(new Integer[]{2, 2, 2, 2, 2}));
    }

    @Test
    public void testEvalueateWhenOneIsRight() {
        assertEquals(1, sut.evaluate(new Integer[]{2, 1, 2, 2, 2}));
    }

    @Test
    public void testEvalueateWhenfiveIsRight() {
        assertEquals(5, sut.evaluate(new Integer[]{1, 1, 1, 1, 1}));
    }
    
    @Test
    public void testTooMuchAnswers() {
        assertEquals(4, sut.evaluate(new Integer[]{1, 1, 1, 1, 0, 1, 1, 1, 1}));
    }
    
    @Test
    public void testTooFew() {
        assertEquals(3, sut.evaluate(new Integer[]{1, 1, 1}));
    }
    
    @Test
    public void testNullAnswers() {
        assertEquals (0, sut.evaluate(null));
    }
}
