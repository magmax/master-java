package org.magmax.masterjava.tema5.nombres;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class NameTableModel extends DefaultTableModel {
	private static final long serialVersionUID = 1L;
	private List<Person> list;

	public NameTableModel (){
		list = new ArrayList<Person>();
	}
	
	public Class<?> getColumnClass(int colnum) {
		switch (colnum) {
		case 0: return String.class;
		default: return Integer.class;
		}
	}

	public int getColumnCount() {
		return 2;
	}

	public String getColumnName(int colnum) {
		switch(colnum) {
		case 0: return "Nombre";
		case 1: return "Edad";
		default: return "Unknown";
		}
	}

	public int getRowCount() {
		return list == null ? 0 : list.size();
	}

	public Object getValueAt(int row, int col) {
		Person person = list.get(row);
		switch (col) {
		case 0: return person.getName();
		case 1: return person.getYears();
		default: throw new IllegalArgumentException("No such column");
		}
	}

	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}
	
	public void append (Person person) {
		list.add(person);
	}

	public void remove(int[] selectedRows) {
		Arrays.sort(selectedRows);
		for ( int i = selectedRows.length; i > 0; --i)
			list.remove(i-1);
	}
}
