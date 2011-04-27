package org.magmax.masterjava.tema10.jdbc_example;

import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.magmax.masterjava.tema10.jdbc_example.persistencia.Cuenta;

public class WindowEditCuenta extends JDialog {
	private static final long serialVersionUID = -6690322734201335807L;
	protected static final int SELECTED_CANCEL = 0;
	protected static final int SELECTED_OK = 1;

	private JTextField codigo;
	private JTextField cliente;
	private JTextField email;
	private JSpinner saldo;
	private WindowEditCuenta window;
	private int selected = SELECTED_CANCEL;

	public WindowEditCuenta(Dialog owner) throws HeadlessException {
		super(owner, "Añadir Cuenta", true);
		initComponents();
	}

	public WindowEditCuenta(Dialog owner, Cuenta cuenta) {
		super(owner, "Editar Cuenta", true);
		initComponents();
		setInitialValues(cuenta);
	}

	private void setInitialValues(Cuenta cuenta) {
		if (cuenta.getCodigo() != null)
			codigo.setText(cuenta.getCodigo());
		if (cuenta.getCliente() != null)
			cliente.setText(cuenta.getCliente());
		if (cuenta.getEmail() != null)
			email.setText(cuenta.getEmail());
		saldo.setValue(cuenta.getSaldo());

		codigo.setEditable(false);
	}

	private void initComponents() {
		window = this;
		setBounds(200, 200, 300, 200);
		getContentPane().setLayout(
				new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		initMainComponents();

		initButtons();
	}

	private void initButtons() {
		JPanel panel;
		panel = new JPanel();

		JButton accept = new JButton("Aceptar");
		JButton cancel = new JButton("Cancelar");

		accept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				window.setSelected(SELECTED_OK);
				window.setVisible(false);
			}
		});

		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				window.setVisible(false);
			}
		});

		panel.add(accept);
		panel.add(cancel);

		add(panel);
	}

	private void initMainComponents() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 2));

		codigo = new JTextField();
		cliente = new JTextField();
		email = new JTextField();
		saldo = new JSpinner();
		saldo.setModel(new SpinnerNumberModel(0, -99999, 99999, 1.0));

		panel.add(new JLabel("Código:"));
		panel.add(codigo);
		panel.add(new JLabel("Cliente:"));
		panel.add(cliente);
		panel.add(new JLabel("E-mail:"));
		panel.add(email);
		panel.add(new JLabel("Saldo:"));
		panel.add(saldo);

		add(panel);
	}

	public int getSelected() {
		return selected;
	}

	private void setSelected(int selected) {
		this.selected = selected;
	}

	public String getCodigo() {
		return codigo.getText();
	}

	public String getCliente() {
		return cliente.getText();
	}

	public String getEmail() {
		return email.getText();
	}

	public Double getSaldo() {
		return (Double) saldo.getValue();
	}

	public Cuenta getCuenta() {
		Cuenta result = new Cuenta();
		result.setCliente(getCliente());
		result.setCodigo(getCodigo());
		result.setEmail(getEmail());
		result.setSaldo(getSaldo());
		return result;
	}
}
