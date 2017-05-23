package test;

import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;

import essentials.Graphics;
import smartmirror.NewsPanel;

public class TestNewsPanel {

	public static void main(String[] args) {

		GridBagLayout layout = new GridBagLayout();

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1500, 200);
		frame.setLocationRelativeTo(null);
		frame.setLayout(layout);

		NewsPanel panel = new NewsPanel();
		Graphics.addComponent(frame, layout, panel, 0, 0, 1, 1, 1, 1, new Insets(0, 0, 0, 0));

		frame.setVisible(true);
	}
}
