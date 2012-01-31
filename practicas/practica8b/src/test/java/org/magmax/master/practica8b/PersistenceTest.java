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

import java.io.InputStream;
import java.sql.*;
import java.util.List;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.*;
import org.magmax.master.practica8b.pojo.Issue;
import static org.junit.Assert.*;
import org.magmax.master.practica8b.pojo.Question;

/**
 *
 * @author miguel
 */
public class PersistenceTest {

    private Persistence sut;

    @BeforeClass
    public static void setUpClass() throws ClassNotFoundException {
        DBCredentials cred = DBCredentials.createWithDefaults();
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, cred.getDriver());
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, cred.getUrl());
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, cred.getUser());
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, cred.getPass());
    }

    @Before
    public void setUp() throws Exception {
        Configuration.reset();
        DBCredentials credentials = DBCredentials.createWithDefaults();
        sut = Persistence.createInstance(credentials);
        sut.buildDatabase();

        DatabaseConnection conn = null;
        try {
            Class.forName(credentials.getDriver());
            conn = new DatabaseConnection(DriverManager.getConnection(credentials.getUrl(), credentials.getUser(), credentials.getPass()));
            DatabaseOperation.CLEAN_INSERT.execute(conn, getDataSet());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @After
    public void tearDown() {
    }

    protected IDataSet getDataSet() throws Exception {
        InputStream data = getClass().getResourceAsStream("/test1.xml");
        IDataSet result = new FlatXmlDataSet(data);
        return result;
    }

    @Test
    public void testRetrieveAllIssues() throws SQLException {
        Issue[] issues = sut.getAllIssues();

        assertEquals("Length do not match", 3, issues.length);
        for (Issue each : issues) {
            if (each.getId() == 1) {
                assertEquals("Matemáticas", each.getName());
            } else if (each.getId() == 2) {
                assertEquals("Informática", each.getName());
            } else if (each.getId() == 3) {
                assertEquals("Literatura", each.getName());
            } else {
                fail("Unexpected issue");
            }
        }
    }

    @Test
    public void testRetrieveQuestionsWhenThereIsNoOne() throws Exception {
        List<Question> questions = sut.retrieveQuestions(3, 3);

        assertNotNull("questions no debe ser null", questions);
        assertEquals("questions length", 0, questions.size());
    }

    @Test
    public void testRetrieveQuestionsWhenThereAreNotEnoughOfThatLevel() throws Exception {
        List<Question> questions = sut.retrieveQuestions(2, 1);

        assertNotNull("questions no debe ser null", questions);
        assertEquals("questions length", 2, questions.size());
    }
    
    @Test
    public void testRetrieveQuestionsWithInferiorLevelQuestions() throws Exception {
        List<Question> questions = sut.retrieveQuestions(2, 2);

        assertNotNull("questions no debe ser null", questions);
        assertEquals("questions length", 3, questions.size());
    }
    
    @Test(expected=DriverNotDefinedException.class)
    public void testErroneousDriver() throws ClassNotFoundException, SQLException, DriverNotDefinedException {
        DBCredentials credentials = new DBCredentials();
        sut = Persistence.createInstance(credentials);
        sut.getAllIssues();
    }
    
    @Test(expected=SQLException.class)
    public void testErroneousDatabase () throws ClassNotFoundException, SQLException, DriverNotDefinedException {
        DBCredentials credentials = new DBCredentials();
        credentials.setDriver(DBCredentials.DEFAULT_DRIVER);
        sut = Persistence.createInstance(credentials);
        sut.getAllIssues();
    }
}
