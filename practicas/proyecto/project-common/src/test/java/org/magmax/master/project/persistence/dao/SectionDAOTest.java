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
package org.magmax.master.project.persistence.dao;

import org.junit.*;
import static org.junit.Assert.*;
import org.magmax.master.project.persistence.pojo.Section;

/**
 *
 * @author miguel
 */
public class SectionDAOTest {
    private SectionDAO sut;
    private Section section;

    @Before
    public void setUp() throws Exception {
        section = new Section();
        section.setName("Iron Maiden");
        sut = new SectionDAO();
    }

    @Test
    public void testCreation() {
        sut.store(section);
        sut.refresh(section);
        
        assertNotNull(section.getId());
    }
}
