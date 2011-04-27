package org.magmax.masterjava.tema10.jdbc_example;
	
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import org.magmax.masterjava.tema10.jdbc_example.persistencia.Cuenta;
import org.magmax.masterjava.tema10.jdbc_example.persistencia.CuentaDao;

public class ModelCuentas extends AbstractTableModel {
	private static final long serialVersionUID = -8518507207353115885L;
	private String[] headers = new String[] {"Codigo", "Cliente", "Email", "Saldo"};
	private CuentaDao dao = new CuentaDao();
	
	public int getColumnCount() {
		return headers.length;
	}

	public int getRowCount() {
		try {
			return dao.findAll().size();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Object getValueAt(int row, int col) {
		// Sí, es altamente ineficiente, pero lo estoy haciendo para practicar...
		List<Cuenta> list;
		try {
			list = dao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
			list = new ArrayList<Cuenta>();
		}
		if (row >= list.size() || col >= headers.length)
			return null;
		Cuenta cuenta = list.get(row);
		switch(col) {
		case 0: return cuenta.getCodigo();
		case 1: return cuenta.getCliente();
		case 2: return cuenta.getEmail();
		case 3: return cuenta.getSaldo();
		}
		return null;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == 3)
			return Float.class;
		return String.class;
	}

	@Override
	public String getColumnName(int column) {
		return headers[column];
	}

	public void addRow(Cuenta cuenta) {
		try {
			dao.create(cuenta);
			fireTableDataChanged();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error insertando objeto", JOptionPane.ERROR_MESSAGE);
		}
	}

	public Cuenta getRow(int row) throws SQLException {
		return dao.read((String)this.getValueAt(row, 0));
	}

	public void deleteRow(int row) throws SQLException {
		dao.delete(dao.read((String)this.getValueAt(row, 0)));
		this.fireTableRowsDeleted(row, row);
		
	}

	public void update(Cuenta cuenta) throws SQLException {
		dao.update(cuenta);
		this.fireTableDataChanged();
	}
}
