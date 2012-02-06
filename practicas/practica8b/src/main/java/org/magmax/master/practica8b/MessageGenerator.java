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

/**
 *
 * @author miguel
 */
class MessageGenerator {

    private static final String[] messages = {
        "Nulos conocimientos",
        "Nulos conocimientos",
        "Muy bajos conocimientos",
        "Pocos conocimientos",
        "Conocimiento aceptable, pruebe con la dificultad media",
        "Parece que tiene buen conocimiento, pruebe dificultad media",
        "Muy bajos conocimientos",
        "Pocos conocimientos, pruebe nivel bajo",
        "Conocimiento aceptable",
        "Buen conocimiento",
        "Parece que tiene muchos conocimientos, pruebe nivel alto",
        "Excelente, pruebe nivel alto",
        "Mejor pruebe en un nivel inferior",
        "Mejor pruebe en un nivel inferior",
        "Tiene un conocimiento más que aceptable",
        "Muy buen conocimiento",
        "Excelente",
        "Es usted un experto"
    };
    
    private int punctuation = 0;
    private int level = 1;

    void setLevel(int level) {
        if (isValidLevel(level))
        this.level = level;
    }

    private boolean isValidLevel(int level) {
        return 0 < level && level < 4;
    }

    void setPunctuation(int punctuation) {
        this.punctuation = punctuation;
    }

    public String getMessage() {
        return messages[(level-1) * 6 + punctuation];
    }
}
