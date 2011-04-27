package org.magmax.masterjava.tema10.jdbc_example.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DerbyDao {

	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	protected Connection getConnection () throws SQLException
	{
		return DriverManager.getConnection("jdbc:derby:memory:derbyDB;create=true");
	}
}
