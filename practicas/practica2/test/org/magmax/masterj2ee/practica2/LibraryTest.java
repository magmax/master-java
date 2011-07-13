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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class LibraryTest {

    private Library sut;
    private Book book;

    public LibraryTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        sut = new Library();

        book = new Book();
        book.setIsbn("No Sleep 'til Hammersmith");
        book.setTitle("Everything Louder Than Everyone Else");
        book.setAuthor("Motörhead");
        book.setPrice(5000);

        sut.addBook(book);
    }

    @After
    public void tearDown() {
    }

    @Test(expected = BookIsDuplicatedException.class)
    public void testAddBookTwiceGetsAnError() throws Exception {
        sut.addBook(book);
    }

    @Test
    public void testISBNSearch() throws Exception {
        assertEquals(book, sut.searchByIsbn(book.getIsbn()));
    }

    @Test(expected = BookNotFoundException.class)
    public void testSearchFailsWithError() throws Exception {
        sut.searchByIsbn("No remorse");
    }

    @Test
    public void testSearchByCompleteTitle() {
        Book[] expected = new Book[]{book};
        assertArrayEquals(expected, sut.searchByTitle(book.getTitle()));
    }

    @Test
    public void testSearchByCompleteTitleWithAnInvalidTitle() {
        Book[] expected = new Book[]{};
        assertArrayEquals(expected, sut.searchByTitle("No remorse"));
    }

    @Test
    public void testSearchByWordInTheTitle() {
        Book[] expected = new Book[]{book};
        assertArrayEquals(expected, sut.searchByTitle("Than"));
    }

    @Test
    public void testSearchByFirstWordInTheTitle() {
        Book[] expected = new Book[]{book};
        assertArrayEquals(expected, sut.searchByTitle("Everything"));
    }

    @Test
    public void testSearchByLastWordInTheTitle() {
        Book[] expected = new Book[]{book};
        assertArrayEquals(expected, sut.searchByTitle("Else"));
    }

    @Test
    public void testSearchByWordReginInTheTitle() {
        Book[] expected = new Book[]{};
        assertArrayEquals(expected, sut.searchByTitle("thing"));
    }

    @Test
    public void testRemoveBook() throws BookNotFoundException {
        assertEquals(book, sut.searchByIsbn(book.getIsbn()));

        sut.removeBook(book.getIsbn());
        try {
            sut.searchByIsbn(book.getIsbn());
            fail("The book still remains");
        } catch (BookNotFoundException e) {
            // OK
        }
    }
    
    @Test(expected=BookNotFoundException.class)
    public void testRemoveInvalidBookGetsException() throws BookNotFoundException {
        sut.removeBook("Decadence");
    }
    
    @Test
    public void testGetAllBooks() {
        Book[] expected = new Book[]{book};
        assertArrayEquals(expected, sut.getBooks());
    }
}
