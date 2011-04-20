package org.magmax.masterjava.tema5.calculadora;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.ItemSelectable;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Calculadora extends JFrame {
	private static final long serialVersionUID = 1434355247225782925L;
	private JSpinner number1;
	private JSpinner number2;
	private JLabel result;
	private int operation;
	private Container mainpane;

	public Calculadora() {
		super();

		setBounds(100, 100, 200, 130);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

		mainpane = getContentPane();
		
		initComponents();
	}

	public Calculadora(Container contentPane) {
		super();

		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		mainpane = contentPane;		
		
		initComponents();
	}

	private void initComponents() {
		addFirstNumber();
		addOperator();
		addSecondNumber();
		addEquals();
		addResult();
	}

	private void addResult() {
		result = new JLabel("0");
		mainpane.add(result);
	}

	private void addEquals() {
		JButton button = new JButton("=");

		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				result.setText(calculate());
			}

			private String calculate() {
				Integer retval = new Integer(0);
				Integer num1 = (Integer) number1.getValue();
				Integer num2 = (Integer) number2.getValue();

				switch (operation) {
				case 1:
					retval = num1 + num2;
					break;
				case 2:
					retval = num1 - num2;
					break;
				case 3:
					retval = num1 * num2;
					break;
				case 4:
					if (num2 == 0)
						return "#inval";
					retval = num1 / num2;
					break;
				}

				return retval.toString();
			}
		});

		mainpane.add(button);
	}

	private void addSecondNumber() {
		number2 = new JSpinner();
		number2.setModel(new SpinnerNumberModel());
		mainpane.add(number2);
	}

	private void addOperator(ButtonGroup group, JPanel panel, String name,
			final int value) {
		JRadioButton button = new JRadioButton(name);
		group.add(button);
		panel.add(button);
		button.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					operation = value;
			}
		});
	}

	private void addOperator() {

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		ButtonGroup group = new ButtonGroup();

		addOperator(group, panel, "+", 1);
		addOperator(group, panel, "-", 2);
		addOperator(group, panel, "*", 3);
		addOperator(group, panel, "/", 4);

		mainpane.add(panel);
	}

	private void addFirstNumber() {
		number1 = new JSpinner();
		number1.setModel(new SpinnerNumberModel());
		mainpane.add(number1);
	}
}
