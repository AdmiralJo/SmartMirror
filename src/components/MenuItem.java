package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;

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

	public MenuItem(String text, int fontsize) {

		setBackground(Color.black);
		setLayout(layout);

		try {
			font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/font.tff").openStream());
			font.deriveFont(fontsize);
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
		Graphics.addComponent(this, layout, label, 0, 0, 1, 1, 1, 1, new Insets(5, 5, 5, 5));
	}

	public void setText(String text) {
		label.setText(text);
	}
}
