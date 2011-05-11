package mensajes2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {
	private final static String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private final static String CONNECTION = "jdbc:derby:memory:myDB";
	private static Persistencia instance = null;

	private Persistencia() {
		try
		{
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(CONNECTION + ";create=true");

			Statement s = conn.createStatement();
			s.execute("create table mensajes(remite varchar(50), destino varchar(50), "
					+ "mensaje varchar(1024))");
			s.close();
			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Persistencia getInstance() {
		if (instance == null) {
			instance = new Persistencia();
		}
		return instance;
	}

	private Connection getConnection() throws SQLException,
			ClassNotFoundException {
		return DriverManager.getConnection(CONNECTION);
	}

	public void create(Mensaje msg) throws SQLException, ClassNotFoundException {
		Connection conn = getConnection();

		PreparedStatement st = conn
				.prepareStatement("insert into mensajes (remite, destino, mensaje) values (?,?,?)");
		st.setString(1, msg.getRemite());
		st.setString(2, msg.getDestino());
		st.setString(3, msg.getTexto());

		if (st.executeUpdate() == 0)
			System.out.println("No se pudo insertar");
		st.close();
		conn.commit();
	}

	public List<Mensaje> retrieve(String destino) throws SQLException,
			ClassNotFoundException {
		Connection conn = getConnection();

		PreparedStatement st = conn
				.prepareStatement("select remite, mensaje from mensajes where destino = ?");
		st.setString(1, destino);

		ResultSet resultset = st.executeQuery();
		List<Mensaje> result = new ArrayList<Mensaje>();
		while (resultset.next()) {
			Mensaje msg = new Mensaje();
			msg.setDestino(destino);
			msg.setRemite(resultset.getString("remite"));
			msg.setTexto(resultset.getString("mensaje"));

			result.add(msg);
		}

		resultset.close();
		st.close();
		conn.commit();

		return result;
	}
}
