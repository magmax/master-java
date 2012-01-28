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
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.*;
import org.magmax.master.practica8b.pojo.Issue;
import static org.junit.Assert.*;

/**
 *
 * @author miguel
 */
public class PersistenceTest {

    private static String driver = "org.hsqldb.jdbcDriver";
    private static String url = "jdbc:hsqldb:mem:sample";
    private static String user = "sa";
    private static String pass = "";
    private Persistence sut;

    @BeforeClass
    public static void setUpClass() throws ClassNotFoundException, DatabaseNotDefinedException {
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, driver);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, url);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, user);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, pass);
    }

    @Before
    public void setUp() throws Exception {
        sut = Persistence.createInstance(driver, url, user, pass);
        sut.buildDatabase();

        DatabaseConnection conn = null;
        try {
            Class.forName(driver);
            conn = new DatabaseConnection(DriverManager.getConnection(url, user, pass));
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
        System.out.println("Cargando datos");
        InputStream data = getClass().getResourceAsStream("/test1.xml");

        IDataSet result = new FlatXmlDataSet(data);
        return result;
    }

    @Test
    public void testRetrieveAllIssues() throws SQLException, DatabaseNotDefinedException {
        Issue[] issues = sut.getAllIssues();

        assertEquals("Length do not match", 2, issues.length);
        for (Issue each : issues) {
            if (each.getId() == 1) {
                assertEquals("Matemáticas", each.getName());
            } else if (each.getId() == 2) {
                assertEquals("Informática", each.getName());
            } else {
                fail("Unexpected issue");
            }
        }
    }
}
