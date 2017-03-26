package test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import smartmirror.Menu;
import smartmirror.MenuPanel;

public class TestMenu {
	public static void main(String[] args) {

		String[] s = { "Blub", "Licht", "Einstellungen", "Radio" };

		MenuPanel menu = new MenuPanel(s);
		menu.setBounds(10, 10, 980, 780);

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 800);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setUndecorated(true);
		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				if (arg0.getKeyChar() == 's')
					menu.back();
				else
					menu.next();
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		frame.add(menu);

		frame.setVisible(true);
	}
}
