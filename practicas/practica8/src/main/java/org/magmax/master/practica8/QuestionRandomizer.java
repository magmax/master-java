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
package org.magmax.master.practica8;

import java.util.Comparator;
import java.util.Random;
import org.magmax.master.practica8.pojo.Question;

/**
 *
 * @author miguel
 */
class QuestionRandomizer implements Comparator<Question> {
    private final Random random;

    public QuestionRandomizer() {
        random = new Random();
    }

    @Override
    public int compare(Question q1, Question q2) {
        if (q2.getLevel() == q1.getLevel()) {
            return random.nextInt(2) == 1 ? 1 : -1;
        }
        return q2.getLevel() - q1.getLevel();
    }
}
