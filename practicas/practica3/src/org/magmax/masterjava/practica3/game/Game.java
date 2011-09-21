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
package org.magmax.masterjava.practica3.game;

import java.io.IOException;
import org.magmax.masterjava.practica3.persistence.Persistence;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class Game {

    private final char[] title;
    private final Character[] guess;
    private int tries = 4;

    Game(Persistence persistence) throws IOException {
        title = persistence.getRandomTitle().toCharArray();
        guess = initializeGuess();
    }

    private Character[] initializeGuess() {
        Character[] result = new Character[title.length];
        for (int i = 0; i < title.length; ++i) {
            if (!Character.isLetter(title[i])) {
                result[i] = title[i];
            }
        }
        return result;
    }

    public String getGuessTitle() {
        StringBuilder result = new StringBuilder();
        for (Character c : guess) {
            result.append(c == null ? '_' : c);
        }
        return result.toString();
    }

    public int tryLetter(char c) throws Exception {
        if (tries == 0)
            throw new Exception("Too much tries");
        
        int result = 0;
        for (int i = 0; i < title.length; ++i) {
            if (matchCharacter(c, title[i]) && guess[i] == null) {
                result++;
                guess[i] = title[i];
            }
        }
        if (result == 0)
            tries--;
        return result;
    }

    public boolean solved() {
        for (Character c : guess) {
            if (c == null) {
                return false;
            }
        }
        return true;
    }

    public int getLeftTries() {
        return tries;
    }
    
    private boolean matchCharacter(char a, char b) {
        return Character.toLowerCase(a) == Character.toLowerCase(b);
    }
}
