package org.magmax.masterjava.tema2.canvas;


import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RandomDrawer extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel canvas;
	private Random random;

	public RandomDrawer () {
		random = new Random();
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		JPanel buttonpane = new JPanel();
		buttonpane.setLayout(new BoxLayout(buttonpane, BoxLayout.X_AXIS));
		JButton bcircle = new JButton("Circle");
		JButton bline = new JButton("Line");
		JButton brectangle = new JButton("Rectangle");
		
		buttonpane.add(bcircle);
		buttonpane.add(bline);
		buttonpane.add(brectangle);
		
		canvas = new JPanel();
		
		add(buttonpane);
		add (canvas);

		bcircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rectangle r = generateDimensions();
				canvas.getGraphics().drawOval(r.x, r.y, r.height, r.width);
			}
		});

		bline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rectangle r = generateDimensions();
				canvas.getGraphics().drawLine(r.x, r.y, r.height, r.width);
			}
		});

		brectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rectangle r = generateDimensions();
				canvas.getGraphics().drawRect(r.x, r.y, r.height, r.width);
			}
		});

		
		setBounds(100, 100, 600, 400);
		setVisible(true);
	}
	
	Rectangle generateDimensions()
	{
		Rectangle result = new Rectangle();
		Rectangle bounds = canvas.getBounds();
		System.out.println(bounds);
		result.x = random.nextInt(bounds.width);
		result.y = random.nextInt(bounds.height);
		result.width = random.nextInt(getLower (bounds.x, result.x));
		result.height = random.nextInt(getLower (bounds.y, result.y));
		return result;
	}
	
	int getLower (int a, int b)
	{
		a = Math.abs(a);
		b = Math.abs(b);
		if (a>b && b > 0)
			return b;
		if (a<b && a > 0)
			return a;
		return 5;
	}
}
