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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 *
 * @author miguel
 */
public class DatabaseInitializator {

    public static void main(String[] args) throws Exception {
        easyInitialize();
    }

    public static void easyInitialize() throws ClassNotFoundException, DriverNotDefinedException, SQLException {
        Logger logger = Logger.getLogger("MASTER 8B");
        
        logger.info("Initializating database for Master in J2EE (Practice 8B)");
        DatabaseInitializator initializator = new DatabaseInitializator(getDefaultCredentials());
        logger.info("Building database...");
        initializator.createDatabase();
        logger.info("Inserting Example Issues...");
        initializator.insertIssues();
        logger.info("Inserting Example Questions...");
        initializator.insertQuestions();
        logger.info("Done.");
    }
    private final DBCredentials credentials;

    public DatabaseInitializator(DBCredentials defaultCredentials) {
        this.credentials = defaultCredentials;
    }

    public void createDatabase() throws ClassNotFoundException, SQLException, DriverNotDefinedException {
        Persistence persistence = Persistence.createInstance(credentials);
        persistence.buildDatabase();
    }

    public static DBCredentials getDefaultCredentials() {
        DBCredentials result = new DBCredentials();
        result.setDriver("org.hsqldb.jdbcDriver");
        result.setUrl("jdbc:hsqldb:database.dat");
        result.setUser("sa");
        result.setPass("");
        return result;
    }

    public void insertIssues() throws SQLException {
        Connection connection = getConnection();
        connection.createStatement().executeUpdate("insert into issue(id, title) values (1, 'Matematicas')");
        connection.createStatement().executeUpdate("insert into issue(id, title) values (2, 'Informática')");
        connection.close();
    }

    public void insertQuestions() throws SQLException {
        Connection connection = getConnection();
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
        connection.close();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(credentials.getUrl(), credentials.getUser(), credentials.getPass());
    }
}
