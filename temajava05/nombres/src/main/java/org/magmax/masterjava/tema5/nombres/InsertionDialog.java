package org.magmax.masterjava.tema5.nombres;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class InsertionDialog extends JDialog {

	private JTextField wname;
	private JSpinner wyears;
	private Person person = null;

	public InsertionDialog(JFrame owner) {
		super(owner, "Insertion", true);

		init();
	}

	private void init() {
		setBounds(150, 150, 300, 200);
		setLayout(new GridLayout(3, 2));

		add(new JLabel("Name:"));
		wname = new JTextField();
		add(wname);

		add(new JLabel("Edad"));
		wyears = new JSpinner();
		wyears.setMaximumSize(new Dimension (0,120));
		wyears.setValue(18);
		add(wyears);

		JButton accept = new JButton("Aceptar");
		accept.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				person = new Person();
				person.setName(wname.getText());
				person.setYears(Integer.parseInt(wyears.getValue().toString()));
				dispose();
			}
		});
		add(accept);
		JButton cancel = new JButton("Cancelar");
		add(cancel);
	}

	public Person getPerson() {
		return person;
	}
}
