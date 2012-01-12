/*
 * Copyright (C) 2012 Miguel Angel Garcia <miguelangel.garcia@gmail.com>
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
package org.magmax.practica7.persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.magmax.practica7.exceptions.DatabaseNotDefinedException;
import org.magmax.practica7.exceptions.PersonAlreadyExists;
import org.magmax.practica7.exceptions.PersonNotFoundException;
import org.magmax.practica7.pojo.Person;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class Persistence {

    private final String CONNECTION_STRING_TEMPLATE = "jdbc:derby:%s;create=true";
    private String databaseName = null;

    public Persistence() {
        loadDatabaseDriver();
    }

    public void close() {
    }

    public void useInMemoryDatabase() throws DatabaseNotDefinedException {
        selectInMemoryName();
        buildDatabase();
    }

    public void useDatabase(String path) throws DatabaseNotDefinedException {
        databaseName = path;
        buildDatabase();
    }

    public void create(Person person) throws PersonAlreadyExists, SQLException, DatabaseNotDefinedException {
        if (personAlreadyExists(person)) {
            throw new PersonAlreadyExists(person.getDni());
        }
        Connection connection = getValidConnection();
        PreparedStatement statement = connection.prepareStatement("insert into person(dni, name, phone) values (?,?,?)");
        statement.setString(1, person.getDni());
        statement.setString(2, person.getName());
        statement.setString(3, person.getPhone());
        statement.execute();
        statement.close();
        connection.close();
    }

    public Person retrievePerson(String dni) throws SQLException, DatabaseNotDefinedException, PersonNotFoundException {
        Person result = new Person();
        Connection connection = getValidConnection();
        PreparedStatement statement = connection.prepareStatement("select dni, name, phone from person where dni = ? ");
        statement.setString(1, dni);
        ResultSet resultset = statement.executeQuery();
        if (resultset.next()) {
            result.setDni(resultset.getString("dni"));
            result.setName(resultset.getString("name"));
            result.setPhone(resultset.getString("phone"));
            resultset.close();
        } else {
            throw new PersonNotFoundException(dni);
        }
        return result;
    }

    public void clear() throws SQLException, DatabaseNotDefinedException {
        Connection connection = getValidConnection();
        PreparedStatement statement = connection.prepareStatement("delete from person");
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public void delete(String name) throws SQLException, DatabaseNotDefinedException {
        Connection connection = getValidConnection();
        PreparedStatement statement = connection.prepareStatement("delete from person where name = ?");
        statement.setString(1, name);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public String[] showNames() throws SQLException, DatabaseNotDefinedException {
        Connection connection = getValidConnection();
        PreparedStatement statement = connection.prepareStatement("select name from person");
        ResultSet resultset = statement.executeQuery();
        ArrayList<String> result = new ArrayList<String>();
        while (resultset.next()) {
            result.add(resultset.getString("name"));
        }
        resultset.close();
        connection.close();
        return result.toArray(new String[0]);
    }

    private boolean personAlreadyExists(Person person) {
        try {
            retrievePerson(person.getDni());
            return true;
        } catch (Exception ex) {
            return false;
        }
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
            buildPersonTable(connection);
        } catch (SQLException ex) {
            Logger.getLogger("").log(Level.SEVERE, null, ex);
        }
    }

    private void buildPersonTable(Connection connection) throws SQLException {
        if (existsPersonTable(connection)) {
            return;
        }
        Statement statement = connection.createStatement();
        statement.execute("create table person(dni varchar(10), name varchar(50), phone varchar(20))");
        statement.close();
        connection.close();
    }

    private boolean existsPersonTable(Connection connection) throws SQLException {
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet tables = dbm.getTables(null, null, "person", null);
        boolean result = tables.next();
        tables.close();
        return result;
    }
}
