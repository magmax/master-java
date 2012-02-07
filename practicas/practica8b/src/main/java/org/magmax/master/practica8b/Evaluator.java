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

import org.magmax.master.practica8b.pojo.Question;

/**
 *
 * @author miguel
 */
class Evaluator {
    private final Question[] exam;

    Evaluator(Question[] exam) {
        this.exam = exam;
        
    }

    int evaluate(Integer[] answers) {
        if (answers == null || exam == null)
            return 0;
        
        int result = 0;
        int length = exam.length > answers.length ? answers.length : exam.length;
        for (int i = 0; i < length; ++i) {
            if (exam[i].getCorrect() == answers[i])
                result ++;
        }
        return result;
    }
    
}
