package org.magmax.masterjava.tema10.jdbc_example;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class WindowCuentas extends JFrame {
	private static final long serialVersionUID = -6027805473528877505L;
	private JTable table;
	private ModelCuentas model;
	private JButton button_editar;
	private JButton button_anadir;
	private JButton button_eliminar;

	public WindowCuentas() throws HeadlessException, ClassNotFoundException {
		super("Cuentas");
		setBounds(100, 100, 600, 400);
		getContentPane().setLayout(
				new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		initComponents();
	}

	private void initComponents() throws ClassNotFoundException {

		model = new ModelCuentas();
		table = new JTable(model);
		add(new JScrollPane(table));

		JPanel panel = new JPanel();
		add(panel);

		button_anadir = new JButton("Añadir");
		button_anadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WindowEditCuenta dialog = new WindowEditCuenta(null);
				dialog.setVisible(true);
				if (dialog.getSelected() == WindowEditCuenta.SELECTED_OK) {
					model.addRow(dialog.getCuenta());
				}
			}
		});
		panel.add(button_anadir);

		button_editar = new JButton("Editar");
		button_editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WindowEditCuenta dialog;
				try {
					dialog = new WindowEditCuenta(null, model.getRow(table
							.getSelectedRow()));
					dialog.setVisible(true);
					if (dialog.getSelected() == WindowEditCuenta.SELECTED_OK) {
						model.update(dialog.getCuenta());
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		button_editar.setEnabled(false);
		panel.add(button_editar);
		
		button_eliminar = new JButton("Eliminar");
		button_eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					model.deleteRow(table.getSelectedRow());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		button_eliminar.setEnabled(false);
		panel.add(button_eliminar);

		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					public void valueChanged(ListSelectionEvent e) {
						if (table.getSelectedRows().length == 0) {
							button_editar.setEnabled(false);
							button_eliminar.setEnabled(false);
						} else {
							button_editar.setEnabled(true);
							button_eliminar.setEnabled(true);
						}
					}
				});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
