package org.magmax.masterjava.tema10.jdbc_example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class DerbyTest {
	private Connection conn;

	@Before
	public void setUp () throws ClassNotFoundException, SQLException {
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		conn = DriverManager.getConnection("jdbc:derby:memory:derbyDB;create=true");
		
		Statement s = conn.createStatement();
		s.execute("create table cuentas(codigo varchar(50), cliente varchar(50), " +
			"email varchar(50), saldo double)");
		s.close();
		
	}
	
	@After
	public void tearDown () throws SQLException {
		conn.close();
	}
	
	@Test
	public void test_isEmpty() throws SQLException {
		Statement s = conn.createStatement();
		ResultSet res = s.executeQuery("select * from cuentas");
		assertFalse (res.next());
	}
}
