package smartmirror;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import essentials.Essentials;
import essentials.Graphics;

public class GUI implements KeyListener {

	JFrame frame = new JFrame();
	GridBagLayout layout = new GridBagLayout();
	JPanel panel = new JPanel(), pnlMenu = new JPanel(), menuPane;
	NewsPanel news = new NewsPanel();
	Menu menu;
	InfoPanel info;
	Properties radios;
	String[] radioKeys = new String[0];
	Radio radio = new Radio();
	String[] items = { "Smart Home", "Licht", "Radio", "Einstellungen" }, settings = { "Newsbar anzeigen", "zurück" };
	PiInterface piInterface = new PiInterface();
	boolean displayMenu = false;
	int i = 0;

	public GUI() {

		frame.setUndecorated(true);
		frame.setBounds(800, 10, 600, 800);
		frame.setLayout(layout);
		frame.addKeyListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel.setLayout(layout);
		panel.setBackground(Color.black);
		Graphics.addComponent(frame, layout, panel, 0, 0, 1, 1, 1, 1, new Insets(0, 0, 0, 0));

		news.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
		Graphics.addComponent(panel, layout, news, 0, 0, 1, 1, 1, 0, new Insets(10, 10, 10, 10));

		info = new InfoPanel();
		Graphics.addComponent(panel, layout, info, 0, 1, 1, 1, 1, 0, new Insets(10, 10, 10, 10));

		menuPane = new JPanel();
		menuPane.setBackground(Color.black);
		menuPane.setLayout(null);
		Graphics.addComponent(panel, layout, menuPane, 0, 2, 1, 1, 1, 1, new Insets(10, 10, 10, 10));

		menu = new Menu(items);
		menu.setBounds((int) (frame.getWidth() / 6), (int) (frame.getHeight() / 5), (int) (frame.getWidth() / 3 * 2),
				(int) (frame.getHeight() / 4));
		menuPane.add(menu);

		radios = new Properties();
		try {
			radios.load(new FileInputStream("res\\radios.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Enumeration<?> enumeration = radios.keys(); enumeration.hasMoreElements();) {

			String[] radioKeys2 = new String[radioKeys.length + 1];
			System.arraycopy(radioKeys, 0, radioKeys2, 0, radioKeys.length);
			radioKeys2[radioKeys.length] = (String) enumeration.nextElement();
			radioKeys = radioKeys2;
		}

		String[] radioKeys2 = new String[radioKeys.length + 2];
		System.arraycopy(radioKeys, 0, radioKeys2, 0, radioKeys.length);
		radioKeys2[radioKeys.length] = "Radio ausschalten";
		radioKeys2[radioKeys.length + 1] = "zurück";
		radioKeys = radioKeys2;

		showMenu(false);
		frame.setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {

		if (e.getKeyChar() == 's') {

			i = 500;
			if (displayMenu)
				menu.next();
		}

		if (e.getKeyChar() == 'w') {

			i = 500;
			if (displayMenu)
				menu.back();
		}

		if (e.getKeyChar() == 'q') {

			i = 500;

			if (displayMenu) {

				if (menu.getText().equals("zurück"))
					menu.setItems(items);
				else {

					if (menu.getText().equals("Radio"))
						menu.setItems(radioKeys.clone());

					else if (Essentials.isIncluded(radioKeys, menu.getText())) {
						radio.stop();
						radio.play(radios.getProperty(menu.getText()));
					}

					if (menu.getText().equals("Licht"))
						piInterface.switchLight();

					if (menu.getText().equals("Einstellungen")) {
						menu.setItems(settings);
					}

					else if (menu.getText().equals("Newsbar anzeigen")) {
						if (news.isVisible())
							news.setVisible(false);
						else
							news.setVisible(true);
					}
				}

			} else {

				showMenu(true);
				displayMenu = true;

				new Thread(new Runnable() {

					@Override
					public void run() {

						while (i > 0) {
							--i;
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}

						showMenu(false);
						displayMenu = false;
					}
				}).start();
			}
		}
	}

	void showMenu(boolean b) {

		if (b) {
			menu.show(true);
		} else {
			menu.show(false);
		}
	}
}
