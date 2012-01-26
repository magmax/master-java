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

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.magmax.master.practica8.pojo.Issue;
import org.magmax.master.practica8.pojo.Question;

/**
 *
 * @author miguel
 */
public class Persistence {

    private final String CONNECTION_STRING_TEMPLATE = "jdbc:derby:%s;create=true";
    private String databaseName = "database.dat";

    private Persistence() {
    }

    public static Persistence createPersistenceInMemory() throws DatabaseNotDefinedException {
        Persistence result = new Persistence();
        result.loadDatabaseDriver();
        result.selectInMemoryName();
        result.buildDatabase();
        return result;
    }

    public static Persistence createPersistenceForDatabase(String path) throws DatabaseNotDefinedException {
        Persistence result = new Persistence();
        result.loadDatabaseDriver();
        result.useDatabase(path);
        result.buildDatabase();
        return result;
    }

    private void useDatabase(String path) {
        databaseName = path;
    }

    public Issue[] getAllIssues() throws SQLException, DatabaseNotDefinedException {
        Connection connection = getValidConnection();
        PreparedStatement statement = connection.prepareStatement("select id, title from issue");
        ResultSet resultset = statement.executeQuery();
        ArrayList<Issue> result = new ArrayList<Issue>();
        Issue issue;
        while (resultset.next()) {
            issue = new Issue();
            issue.setId(resultset.getInt("id"));
            issue.setName(resultset.getString("title"));
            result.add(issue);
        }
        resultset.close();
        connection.close();
        return result.toArray(new Issue[0]);

    }
    
    List<Question> retrieveQuestions(int issue_id, int level) throws SQLException, DatabaseNotDefinedException {
        Connection connection = getValidConnection();
        PreparedStatement statement = connection.prepareStatement("select id, description, correct, answer1, answer2, answer3, answer4, difficulty from question where id_issue=? and difficulty<=?");
        statement.setInt(1, issue_id);
        statement.setInt(2, level);
        ResultSet resultset = statement.executeQuery();
        ArrayList<Question> result = new ArrayList<Question>();
        Question question;
        while (resultset.next()) {
            question = new Question();
            question.setId(resultset.getInt("id"));
            question.setDescription(resultset.getString("description"));
            question.setSelected(resultset.getInt("correct"));
            question.setLevel(resultset.getInt("difficulty"));
            String[] answers = new String[4];
            answers[0] = resultset.getString("answer1");
            answers[1] = resultset.getString("answer2");
            answers[2] = resultset.getString("answer3");
            answers[3] = resultset.getString("answer4");
            question.setAnswer(answers);
            result.add(question);
        }
        resultset.close();
        connection.close();
        return result;
    }
    
    private void selectInMemoryName() {
        databaseName = "memory:" + UUID.randomUUID();
    }

    private void loadDatabaseDriver() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Connection getValidConnection() throws SQLException, DatabaseNotDefinedException {
        Connection result = getConnection();
        result.setAutoCommit(true);
        return result;
    }

    private Connection getConnection() throws SQLException, DatabaseNotDefinedException {
        return DriverManager.getConnection(getConnectionString());
    }

    private String getConnectionString() throws DatabaseNotDefinedException {
        if (databaseName == null) {
            throw new DatabaseNotDefinedException();
        }
        return String.format(CONNECTION_STRING_TEMPLATE, databaseName);
    }

    private void buildDatabase() throws DatabaseNotDefinedException {
        try {
            Connection connection = getConnection();
            buildIssueTable(connection);
            buildQuestionTable(connection);
        } catch (SQLException ex) {
            Logger.getLogger("").log(Level.SEVERE, null, ex);
        }
    }

    private void buildIssueTable(Connection connection) throws SQLException {
        if (existsIssueTable(connection)) {
            return;
        }
        Statement statement = connection.createStatement();
        statement.execute("create table issue(id int, title varchar(50))");
        statement.close();
        insertIssues(connection);
        connection.close();
    }

    private void insertIssues(Connection connection) throws SQLException {
        connection.createStatement().executeUpdate("insert into issue(id, title) values (1, 'Matematicas')");
        connection.createStatement().executeUpdate("insert into issue(id, title) values (2, 'Informática')");
    }

    private boolean existsIssueTable(Connection connection) throws SQLException {
        return existsTable(connection, "issue");
    }

    private void buildQuestionTable(Connection connection) throws SQLException {
        if (existsQuestionTable(connection)) {
            return;
        }
        Statement statement = connection.createStatement();
        statement.execute("create table question(id int, description varchar(250), id_issue int, correct int, answer1 varchar(50), answer2 varchar(50), answer3 varchar(50), answer4 varchar(50), difficulty int)");
        statement.close();
        insertQuestions(connection);
        connection.close();
    }

    private void insertQuestions(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("insert into question(id, description, id_issue, difficulty, correct, answer1, answer2, answer3, answer4) values (1, '1 + 2 =', 1, 1, 1, '1', '2', '3', '4')");
        statement.executeUpdate("insert into question(id, description, id_issue, difficulty, correct, answer1, answer2, answer3, answer4) values (2, '2 + 3 =', 1, 1, 4, '2', '3', '4', '5')");
        statement.executeUpdate("insert into question(id, description, id_issue, difficulty, correct, answer1, answer2, answer3, answer4) values (3, '3 + 2 =', 1, 1, 4, '2', '3', '4', '5')");
        statement.executeUpdate("insert into question(id, description, id_issue, difficulty, correct, answer1, answer2, answer3, answer4) values (4, '4 + 1 =', 1, 1, 4, '2', '3', '4', '5')");
        statement.executeUpdate("insert into question(id, description, id_issue, difficulty, correct, answer1, answer2, answer3, answer4) values (5, '5 - 1 =', 1, 1, 3, '2', '3', '4', '5')");
        statement.executeUpdate("insert into question(id, description, id_issue, difficulty, correct, answer1, answer2, answer3, answer4) values (6, '6 - 1 =', 1, 1, 2, '4', '5', '6', '7')");
        statement.executeUpdate("insert into question(id, description, id_issue, difficulty, correct, answer1, answer2, answer3, answer4) values (7, '7 * 1 =', 1, 2, 4, '4', '5', '6', '7')");
        statement.executeUpdate("insert into question(id, description, id_issue, difficulty, correct, answer1, answer2, answer3, answer4) values (8, '8 * 2 =', 1, 2, 3, '14', '15', '16', '17')");
        statement.executeUpdate("insert into question(id, description, id_issue, difficulty, correct, answer1, answer2, answer3, answer4) values (9, '9 + 1 =', 1, 2, 4, '9', '10', '11', '12')");
        statement.executeUpdate("insert into question(id, description, id_issue, difficulty, correct, answer1, answer2, answer3, answer4) values (10, '10^2 =', 1, 3, 3, '80', '90', '100', '42')");
        statement.executeUpdate("insert into question(id, description, id_issue, difficulty, correct, answer1, answer2, answer3, answer4) values (11, '¿Qué es la GPL?', 2, 3, 1, 'Una licencia libre', 'Un sistema de transmisión', 'Protocolo de móviles', 'Un avión')");
        statement.executeUpdate("insert into question(id, description, id_issue, difficulty, correct, answer1, answer2, answer3, answer4) values (12, 'La primera familia de computadoras fue...', 2, 3, 3, 'Multivac', 'Univac', 'PDP', 'Eniac')");
        statement.executeUpdate("insert into question(id, description, id_issue, difficulty, correct, answer1, answer2, answer3, answer4) values (13, 'Java es...', 2, 1, 2, 'Una computadora', 'Una red', 'Un lenguaje', 'Un compilador')");
        statement.executeUpdate("insert into question(id, description, id_issue, difficulty, correct, answer1, answer2, answer3, answer4) values (14, 'Java es...', 2, 3, 3, 'Compilado', 'Estructurado', 'Interpretado', 'Ninguna de las anteriores')");
        statement.executeUpdate("insert into question(id, description, id_issue, difficulty, correct, answer1, answer2, answer3, answer4) values (15, 'Sólo se puede hacer una aplicación web en...', 2, 2, 4, 'Python', 'C', 'Java', 'Todas las anteriores son correctas')");
        statement.executeUpdate("insert into question(id, description, id_issue, difficulty, correct, answer1, answer2, answer3, answer4) values (16, 'Groovy es...', 2, 2, 4, 'Un servidor web', 'Una arquitectura de red', 'Una palabra inventada', 'Un lenguaje de programación')");
        statement.close();
    }

    private boolean existsQuestionTable(Connection connection) throws SQLException {
        return existsTable(connection, "question");
    }

    private boolean existsTable(Connection connection, String tablename) throws SQLException {
        return existsTableCase(connection, tablename) || existsTableCase(connection, tablename.toUpperCase());
    }

    private boolean existsTableCase(Connection connection, String tablename) throws SQLException {
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet tables = dbm.getTables(null, null, tablename, null);
        boolean result = tables.next();
        tables.close();
        return result;
    }
}
