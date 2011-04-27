package org.magmax.masterjava.tema10.jdbc_example.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CuentaDao extends DerbyDao implements GenericDao<Cuenta, String> {

	private final String query_delete = "delete from cuentas where codigo=?";
	private final String query_insert = "insert into cuentas (codigo,cliente,email,saldo) values (?,?,?,?)";
	private final String query_update = "update cuentas set cliente=?, email=?, saldo=? where codigo=?";
	private final String query_select_all = "select codigo,cliente,email,saldo from cuentas";
	private final String query_select = "select codigo,cliente,email,saldo from cuentas where codigo=?";

	public String create(Cuenta newInstance) throws SQLException {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = getConnection();
			st = conn.prepareStatement(query_insert);
			st.setString(1, newInstance.getCodigo());
			st.setString(2, newInstance.getCliente());
			st.setString(3, newInstance.getEmail());
			st.setDouble(4, newInstance.getSaldo());
			if (st.executeUpdate() != 1)
				throw new SQLException("No se pudo crear el objeto");
		} finally {
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		}
		return newInstance.getCodigo();
	}

	public Cuenta read(String id) throws SQLException {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			st = conn.prepareStatement(query_select);
			st.setString(1, id);
			rs = st.executeQuery();
			rs.next();
			Cuenta result = new Cuenta();
			result.setCodigo(rs.getString(1));
			result.setCliente(rs.getString(2));
			result.setEmail(rs.getString(3));
			result.setSaldo(rs.getDouble(4));
			return result;
		} finally {
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		}
	}

	public void update(Cuenta transientObject) throws SQLException {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = getConnection();
			st = conn.prepareStatement(query_update);
			st.setString(1, transientObject.getCliente());
			st.setString(2, transientObject.getEmail());
			st.setDouble(3, transientObject.getSaldo());
			st.setString(4, transientObject.getCodigo());
			if (st.executeUpdate() != 1)
				throw new SQLException("No se pudo borrar el objeto");
		} finally {
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		}
	}

	public void delete(Cuenta persistentObject) throws SQLException {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = getConnection();
			st = conn.prepareStatement(query_delete);
			st.setString(1, persistentObject.getCodigo());
			if (st.executeUpdate() != 1)
				throw new SQLException("No se pudo borrar el objeto");
		} finally {
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		}
	}

	public List<Cuenta> findAll() throws SQLException {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Cuenta> result = new ArrayList<Cuenta>();
		try {
			conn = getConnection();
			st = conn.prepareStatement(query_select_all);
			rs = st.executeQuery();
			while (rs.next()) {
				Cuenta cuenta = new Cuenta();
				cuenta.setCodigo(rs.getString(1));
				cuenta.setCliente(rs.getString(2));
				cuenta.setEmail(rs.getString(3));
				cuenta.setSaldo(rs.getDouble(4));
				result.add(cuenta);
			}
			return result;
		} finally {
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		}
	}
}
