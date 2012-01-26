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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import org.magmax.master.practica8.pojo.Issue;
import org.magmax.master.practica8.pojo.Question;

/**
 *
 * @author miguel
 */
public class Domain {

    public static final int NUMBER_OF_QUESTIONS_PER_EXAM = 5;
    private final String database;

    public Domain(String database) {
        this.database = database;
    }

    public Issue[] getIssues() throws DatabaseNotDefinedException, SQLException {
        Persistence persistence = Persistence.createPersistenceForDatabase(database);
        return persistence.getAllIssues();
    }

    public Question[] generateExam(Integer level, Integer issue_id)  {
        try {
            Persistence persistence = Persistence.createPersistenceForDatabase(database);

            if (level == null)
                level = Level.Low.getValue();
            if (issue_id == null)
                issue_id = 1;
        
               
            List<Question> result = persistence.retrieveQuestions(issue_id, level);
            Collections.sort(result, new QuestionRandomizer());

            return result.subList(0, NUMBER_OF_QUESTIONS_PER_EXAM).toArray(new Question[0]);
        } catch (SQLException ex) {
            Logger.getLogger(Domain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (DatabaseNotDefinedException ex) {
            Logger.getLogger(Domain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return new Question[0];
    }

    public Question[] solve (Question[] exam, Integer[] answers) {
        List<Question> result = new ArrayList<Question>();
        
       
        for (int i = 0; i < exam.length; ++i) {
            if (answers[i] != null && exam[i].getCorrect() == answers[i])
                result.add(exam[i]);
        }
        return result.toArray(new Question[0]);
    }
    
    public String getResultMessage(Level level, int punctuation) {
        MessageGenerator result = new MessageGenerator();
        result.setLevel(level);
        result.setPunctuation(punctuation);
        return result.getMessage();
    }
}
