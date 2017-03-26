package smartmirror;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.FileInputStream;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import essentials.Graphics;

public class MenuItem extends JPanel {

	private static final long serialVersionUID = 1L;

	GridBagLayout layout = new GridBagLayout();
	JLabel label = new JLabel();
	Font font;
	boolean border = true;

	public MenuItem(String text, int fontsize, boolean shortborder) {

		setBackground(Color.black);
		setLayout(layout);

		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("res\\font.otf")).deriveFont(0, fontsize);
		} catch (Exception e) {
			font = new Font("Verdana", 0, fontsize);
			e.printStackTrace();
		}

		label.setFont(font);
		label.setText(text);
		label.setForeground(Color.white);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setBorder(BorderFactory.createLineBorder(Color.white));

		if (shortborder) {
			Graphics.addComponent(this, layout, label, 1, 0, 1, 1, 1, 1, new Insets(5, 60, 5, 60));
		} else
			Graphics.addComponent(this, layout, label, 0, 0, 1, 1, 1, 1, new Insets(5, 5, 5, 5));
	}

	public void setText(String text) {
		label.setText(text);
	}

	public String getText() {
		return label.getText();
	}

	public void setBorder(boolean b) {

		border = b;

		if (b)
			label.setBorder(BorderFactory.createLineBorder(Color.white));
		else
			label.setBorder(BorderFactory.createEmptyBorder());
	}

	public void show(boolean b) {

		if (b) {
			label.setForeground(Color.white);
			if (border)
				label.setBorder(BorderFactory.createLineBorder(Color.white));
			else
				label.setBorder(BorderFactory.createEmptyBorder());
		} else {
			label.setForeground(Color.black);
			label.setBorder(BorderFactory.createEmptyBorder());
		}
	}
}