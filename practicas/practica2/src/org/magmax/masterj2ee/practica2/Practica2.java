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

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class Practica2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Practica2 p = new Practica2();
        p.run();
    }
    private boolean end;
    private Library library;
    private Scanner scanner;

    private void run() {
        scanner = new Scanner(System.in);
        Menu menu = new Menu(scanner);
        mainLoop(menu);
    }

    private void mainLoop(Menu menu) {
        end = false;
        library = new Library();
        while (!end) {
            System.out.println("Iterando");
            menu.show();
            try {
                manageOptions(menu.choose_option());
            } catch (BookIsDuplicatedException e) {
                System.out.println("El libro ya se encuentra en el sistema");
            } catch (BookNotFoundException e) {
                System.out.println("El libro no se ha encontrado");
            }
        }
    }

    private void manageOptions(int option) throws BookIsDuplicatedException, BookNotFoundException {
        switch (option) {
            case 1:
                addBook();
                break;
            case 2:
                searchBookByISBN();
                break;
            case 6:
                end = true;
                break;
            default:
                System.out.println("Opción desconocida:" + option);
        }
    }

    private void addBook() throws BookIsDuplicatedException {
        library.addBook(readBookArguments());
        System.out.println("Libro añadido");
    }

    private Book readBookArguments() {
        Book result = new Book();
        result.setIsbn(readData("Introduzca el ISBN:"));
        result.setTitle(readData("Introduzca el título del libro"));
        result.setAuthor(readData("Introduzca el autor del libro:"));
        result.setPrice(readData("Introduzca el precio del libro"));
        return result;
    }

    private String readData(String msg) {
        String result = "";
        while ("".equals(result))
        {
            System.out.println(msg);
            result = scanner.nextLine().trim();
        }
        return result;
    }

    private void searchBookByISBN() throws BookNotFoundException {
        Book book = library.searchByIsbn(readData("ISBN del libro a buscar:"));
        System.out.println("Libro encontrado:");
        System.out.println(book);
    }
}
