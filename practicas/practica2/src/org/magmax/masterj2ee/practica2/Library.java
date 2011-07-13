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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class Library {

    private HashMap<String, Book> books = new HashMap<String, Book>();

    void addBook(Book book) throws BookIsDuplicatedException {
        if (books.containsKey(book.getIsbn()))
            throw new BookIsDuplicatedException ();
        books.put(book.getIsbn(), book);
    }
    
    Book searchByIsbn(String isbn) throws BookNotFoundException  {
        Book result = books.get(isbn);
        if (result == null)
            throw new BookNotFoundException();
        return result;
    }
    
    Book[] searchByTitle(String title) {
        List<Book> result = new ArrayList<Book>();
        
        for (Book each : books.values()) {
            if (bookTitleContainsWord(each, title))
                result.add(each);
        }
        
        return result.toArray(new Book[0]);
    }

    private boolean bookTitleContainsWord(Book book, String title) {
        if (book.getTitle().equals(title))
            return true;
        return book.getTitle().matches("(^|.*\\s)" + title + "(\\s.*|$)");
    }

    void removeBook(String isbn) throws BookNotFoundException {
        if (null == books.remove(isbn))
            throw new BookNotFoundException();
    }
    
    Book[] getBooks() {
        return books.values().toArray(new Book[0]);
    }
}
