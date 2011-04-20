package org.magmax.masterjava.tema5.nombres;

import java.awt.Button;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class WindowName extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;

	public WindowName () {
		super();
		init();
	}

	private void init() {
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS) );
		setBounds(100,100,600,400);
		
		initTable();
		initButtons();
		
	}

	private void initButtons() {
		JPanel panel = new JPanel();
		
		Button insertar = new Button("Insertar");
		insertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				InsertionDialog dialog = new InsertionDialog(new JFrame());
				dialog.setVisible(true);
				((NameTableModel) table.getModel()).append( dialog.getPerson() );
				table.updateUI();
			}
		});
		panel.add(insertar);
		Button eliminar = new Button("Eliminar");
		eliminar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int pane = JOptionPane.showConfirmDialog(new JFrame(), "¿Está seguro?");
				if (pane == JOptionPane.YES_OPTION) {
					((NameTableModel) table.getModel()).remove(table.getSelectedRows());
				}
				table.updateUI();
			}
		});
		panel.add(eliminar);
		
		add (panel);
	}

	private void initTable() {
		table = new JTable();
		table.setModel(new NameTableModel());
		add(new JScrollPane(table));
	}
}
