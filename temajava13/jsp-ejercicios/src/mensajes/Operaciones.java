package mensajes;

import java.sql.*;
import mensajes.Mensaje;
import java.util.*;

public class Operaciones {
	private String driver, cadenacon;

	public Operaciones(String driver, String cadenacon) {
		this.driver = driver;
		this.cadenacon = cadenacon;
	}

	public Connection obtenerConexion() {
		Connection cn = null;
		try {
			Class.forName(driver);
			cn = DriverManager.getConnection(cadenacon);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cn;
	}

	public ArrayList<Mensaje> getMensajes(String destino) {
		Connection cn = null;
		ArrayList<Mensaje> lista = null;
		Statement st;
		ResultSet rs;
		try {
			cn = obtenerConexion();
			st = cn.createStatement();
			String tsql;
			tsql = "select * from mensajes where destinatario='" + destino
					+ "'";
			rs = st.executeQuery(tsql);
			lista = new ArrayList<Mensaje>();
			while (rs.next()) {
				Mensaje m = new Mensaje(rs.getString("remitente"),
						rs.getString("destinatario"), rs.getString("texto"));
				lista.add(m);
			}
			cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (lista);
	}

	public void graba(Mensaje m) {
		Connection cn;
		Statement st;
		ResultSet rs;
		try {
			cn = obtenerConexion();
			st = cn.createStatement();
			String tsql;
			tsql = "Insert into mensajes values('";
			tsql += m.getDestino() + "','" + m.getRemite() + "','"
					+ m.getTexto() + "')";
			st.execute(tsql);
			cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
