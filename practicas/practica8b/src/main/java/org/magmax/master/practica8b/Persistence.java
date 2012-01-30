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

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.magmax.master.practica8b.pojo.Issue;
import org.magmax.master.practica8b.pojo.Question;

/**
 *
 * @author miguel
 */
public class Persistence {

    private String url;
    private String pass;
    private String user;

    private Persistence() {
    }

    public static Persistence createInstance(String driver, String url, String user, String pass) throws ClassNotFoundException, DriverNotDefinedException {
        Persistence result = new Persistence();
        result.setDriver(driver);
        result.setUrl(url);
        result.setUser(user);
        result.setPass(pass);
        return result;
    }

    public Issue[] getAllIssues() throws SQLException {
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

    List<Question> retrieveQuestions(int issue_id, int level) throws SQLException {
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
            question.setCorrect(resultset.getInt("correct"));
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

    private Connection getValidConnection() throws SQLException {
        Connection result = getConnection();
        result.setAutoCommit(true);
        return result;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

    public void buildDatabase() throws SQLException {
        Connection connection = getConnection();
        buildIssueTable(connection);
        buildQuestionTable(connection);
        connection.close();
    }

    private void buildIssueTable(Connection connection) throws SQLException {
        if (existsIssueTable(connection)) {
            return;
        }
        Statement statement = connection.createStatement();
        statement.execute("create table issue(id int, title varchar(50))");
        statement.close();
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

    private void setUrl(String url) {
        this.url = url;
    }

    private void setDriver(String driver) throws ClassNotFoundException, DriverNotDefinedException {
        if (driver == null)
            throw new DriverNotDefinedException();
        Class.forName(driver);
    }

    private void setUser(String user) {
        this.user = user;
    }

    private void setPass(String pass) {
        this.pass = pass;
    }
}
