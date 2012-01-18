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
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miguel
 */
public class Persistence {

    private final String CONNECTION_STRING_TEMPLATE = "jdbc:derby:%s;create=true";
    private String databaseName = "database.dat";

    public Persistence() {
        loadDatabaseDriver();
    }

    public void useInMemoryDatabase() throws DatabaseNotDefinedException {
        selectInMemoryName();
        buildDatabase();
    }

    public void useDatabase(String path) throws DatabaseNotDefinedException {
        databaseName = path;
        buildDatabase();
    }

    public void initialize() throws DatabaseNotDefinedException {
        buildDatabase();
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
        connection.close();
    }

    private boolean existsIssueTable(Connection connection) throws SQLException {
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet tables = dbm.getTables(null, null, "issue", null);
        boolean result = tables.next();
        tables.close();
        return result;
    }
}
