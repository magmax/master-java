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
package org.magmax.masterj2ee.practica2;

import java.util.Scanner;
import java.io.ByteArrayInputStream;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class MenuTest {

    public MenuTest() {
    }
    
    private int selectValue (String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Menu sut = new Menu(new Scanner(System.in));
        return sut.choose_option();
    }

    @Test
    public void testChoose_option() {
        for (int selected = 1; selected < 6; ++selected) {
            assertEquals(selected, selectValue(String.valueOf(selected)));
        }
    }

    @Test(expected=IllegalArgumentException.class)
    public void testChooseOption7() {
        selectValue("7");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testChooseOption0() {
        selectValue("0");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testNotANumber() {
        selectValue("Nothing Else Mathers");
    }
    
    @Test
    public void testMenuList() {
        
    }
}
