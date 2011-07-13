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

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class Menu {
    private final Scanner reader;

    public Menu(Scanner scanner) {
        reader = scanner;
    }

    public int choose_option() {
        try {
            return try_choose_option();
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException("Those was not a number (" + e.getMessage() + ')');
        }
    }

    private int try_choose_option() {

        int result = retrieveInt();

        if (isInvalidOption(result)) {
            throw new IllegalArgumentException("Invalid option:" + result);
        }
        return result;
    }

    private int retrieveInt() {
        return reader.nextInt();
    }

    private boolean isInvalidOption(int result) {
        return result < 1 || result > 6;
    }

    void show() {
        System.out.println("1.- Alta de libro");
        System.out.println("2.- Buscar libro por ISBN");
        System.out.println("3.- Buscar libro por título");
        System.out.println("4.- Baja de libro");
        System.out.println("5.- Mostrar todos los libros");
        System.out.println("6.- Salir");
    }
}
