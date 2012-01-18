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
package org.magmax.master.practica8.integration;

import com.thoughtworks.selenium.DefaultSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author miguel
 */
@Ignore
public class MainPageTest {

    private DefaultSelenium selenium;

    @Before
    public void setUp() {
        selenium = createSeleniumClient("http://localhost:8080/practica8/");
        selenium.start();
    }

    @After
    public void tearDown() {
        selenium.stop();
    }

    @Test
    public void testIndex() {
//        assertEquals("Master Java Practica 8: QuizMaster", selenium.getTitle());
    }

    private DefaultSelenium createSeleniumClient(String url) {
        return new DefaultSelenium("localhost", 4444, "*firefox", url);
    }
}
