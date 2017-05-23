package smartmirror;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Insets;

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

		top = new MenuItem("", 32, true);
		Graphics.addComponent(this, layout, top, 0, 0, 1, 1, 1, 1, new Insets(0, 0, 0, 0));

		center = new MenuItem("", 48, false);
		Graphics.addComponent(this, layout, center, 0, 1, 1, 1, 1, 1, new Insets(0, 0, 0, 0));

		bottom = new MenuItem("", 32, true);
		Graphics.addComponent(this, layout, bottom, 0, 2, 1, 1, 1, 1, new Insets(0, 0, 0, 0));
		display();
	}

	void display() {

		switch (items.length) {
		case 0:
			break;
		case 1:
			top.setText(" ");
			top.setBorder(false);
			center.setText(items[0]);
			bottom.setText(" ");
			bottom.setBorder(false);
			break;
		case 2:
			top.setText(items[0]);
			top.setBorder(true);
			center.setText(items[1]);
			bottom.setText(" ");
			bottom.setBorder(false);
			break;
		default:
			top.setText(items[0]);
			top.setBorder(true);
			center.setText(items[1]);
			bottom.setText(items[2]);
			bottom.setBorder(true);
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

	public void setItems(String[] items) {
		this.items = items;
		display();
	}

	public String[] getItems() {
		return items.clone();
	}

	public String getText() {
		return center.getText();
	}

	public void show(boolean b) {

		if (b) {
			top.show(true);
			center.show(true);
			bottom.show(true);
		} else {
			top.show(false);
			center.show(false);
			bottom.show(false);
		}
	}
}
