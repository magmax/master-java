package org.magmax.masterjava.tema10.jdbc_example;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws HeadlessException, ClassNotFoundException, SQLException
    {
    	Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
    	initializeDatabase();
    	WindowCuentas window = new WindowCuentas();
    	window.setVisible(true);
    }

	private static void initializeDatabase() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:memory:derbyDB;create=true");
		
		Statement s = conn.createStatement();
		s.execute("create table cuentas(codigo varchar(50), cliente varchar(50), " +
			"email varchar(50), saldo double)");
		s.close();
	}
}
