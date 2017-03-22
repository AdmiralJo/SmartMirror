package components;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import essentials.Graphics;

public class Menu extends JPanel {

	private static final long serialVersionUID = 1L;

	GridBagLayout layout = new GridBagLayout();
	MenuItem top, center, bottom;
	String[] items;

	public Menu(String[] items) {

		this.items = items;

		setBackground(Color.black);
		setLayout(layout);

		top = new MenuItem("", 42);
		Graphics.addComponent(this, layout, top, 1, 0, 1, 1, 1, 1, new Insets(0, 0, 0, 0));
		Graphics.addComponent(this, layout, new EmptyPane(), 0, 0, 1, 1, 0, 0, new Insets(0, 0, 0, 0));
		Graphics.addComponent(this, layout, new EmptyPane(), 2, 0, 1, 1, 0, 0, new Insets(0, 0, 0, 0));
		
		center = new MenuItem("", 62);
		Graphics.addComponent(this, layout, center, 0, 1, 3, 1, 1, 1, new Insets(0, 0, 0, 0));

		bottom = new MenuItem("", 42);
		Graphics.addComponent(this, layout, bottom, 1, 2, 1, 1, 1, 1, new Insets(0, 0, 0, 0));
		Graphics.addComponent(this, layout, new EmptyPane(), 0, 2, 1, 1, 0, 0, new Insets(0, 0, 0, 0));
		Graphics.addComponent(this, layout, new EmptyPane(), 2, 2, 1, 1, 0, 0, new Insets(0, 0, 0, 0));
		
		display();
	}

	void display() {

		switch (items.length) {
		case 0:
			break;
		case 1:
			top.setText("");
			center.setText(items[0]);
			bottom.setText("");
			break;
		case 2:
			top.setText(items[0]);
			center.setText(items[1]);
			bottom.setText("");
			break;
		default:
			top.setText(items[0]);
			center.setText(items[1]);
			bottom.setText(items[2]);
			break;
		}
	}

	public void next() {

		String overfloat = items[0];
		for (int i = 0; i < items.length; i++)
			if (i < items.length - 1)
				items[i] = items[i + 1];
		items[items.length - 1] = overfloat;
		display();
	}

	public void back() {

		String overfloat = items[items.length - 1];
		for (int i = items.length - 1; i > 0; i--)
			items[i] = items[i - 1];
		items[0] = overfloat;
		display();
	}
}
