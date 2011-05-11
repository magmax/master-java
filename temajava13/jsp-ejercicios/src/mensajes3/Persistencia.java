package mensajes3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {
	private String driver;
	private String connection;
	
	private String destino;

	public Persistencia() {
	}

	public void createTables() {
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(connection + ";create=true");

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

	private Connection getConnection() throws SQLException,
			ClassNotFoundException {
		return DriverManager.getConnection(connection);
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

	public List<Mensaje> getMessages() throws SQLException,
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

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	
}
