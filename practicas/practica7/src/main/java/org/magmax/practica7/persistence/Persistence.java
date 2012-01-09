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

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.magmax.practica7.pojo.Person;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class Persistence {

    public Persistence() {
    }

    public Persistence(boolean inMemory) {
        ConnectionRetriever.useInMemory(true);
    }

    public void close() {
        ConnectionRetriever.close();
    }

    public void create(Person person) throws Exception {
        if (personAlreadyExists(person)) {
            throw new Exception(String.format("The DNI %s already exists.", person.getDni()));
        }
        Connection connection = ConnectionRetriever.getValidConnection();
        PreparedStatement statement = connection.prepareStatement("insert into Person(dni, name, phone) values (?,?,?)");
        statement.setString(1, person.getDni());
        statement.setString(2, person.getName());
        statement.setString(3, person.getPhone());
        statement.execute();
        statement.close();
        connection.close();
    }

    public Person retrievePerson(String dni) throws Exception {
        Person result = new Person();
        Connection connection = ConnectionRetriever.getValidConnection();
        PreparedStatement statement = connection.prepareStatement("select dni, name, phone from Person where dni = ? ");
        statement.setString(1, dni);
        ResultSet resultset = statement.executeQuery();
        if (resultset.next()) {
            result.setDni(resultset.getString("dni"));
            result.setName(resultset.getString("name"));
            result.setPhone(resultset.getString("phone"));
            resultset.close();
        } else {
            result.setName("");
            result.setDni("");
            result.setPhone("");
        }
        return result;
    }

    private boolean personAlreadyExists(Person person) {
        try {
            retrievePerson(person.getDni());
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void clear() throws SQLException {
        Connection connection = ConnectionRetriever.getValidConnection();
        PreparedStatement statement = connection.prepareStatement("delete from Person");
        statement.executeUpdate();
        statement.close();
        connection.close();
    }
}

class ConnectionRetriever {

    public static final String DEFAULT_FILENAME = "database.dat";
    public static final String IN_MEMORY = "memory:";
    private static ConnectionRetriever instance = null;
    private static final String CONNECTION_STRING_TEMPLATE = "jdbc:derby:%s;create=true";
    private static Object[] database = new String[]{DEFAULT_FILENAME};

    static void useInMemory(boolean inMemory) {
        database[0] = inMemory ? getInMemoryName() : DEFAULT_FILENAME;
    }

    private static String getInMemoryName() {
        return IN_MEMORY + UUID.randomUUID();
    }

    static void close() {
        instance = null;
    }

    private ConnectionRetriever() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionRetriever.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getValidConnection() throws SQLException {
        if (instance == null) {
            instance = new ConnectionRetriever();
            instance.buildDatabase();
        }
        Connection result = getConnection();
        result.setAutoCommit(true);
        return result;
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(instance.getConnectionString());
    }

    private String getConnectionString() {
        return String.format(CONNECTION_STRING_TEMPLATE, database);
    }

    private void buildDatabase() {
        try {
            Connection connection = getConnection();
            buildPersonTable(connection);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionRetriever.class.getName()).log(Level.SEVERE, null, ex);
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
        ResultSet tables = dbm.getTables(null, null, "Person", null);
        boolean result = tables.next();
        tables.close();
        return result;
    }
}
