package smartmirror;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import essentials.Essentials;
import essentials.Graphics;

public class GUI implements KeyListener {

	static JFrame frame = new JFrame();
	GridBagLayout layout = new GridBagLayout();
	JPanel panel = new JPanel(), pnlMenu = new JPanel(), menuPane;
	static NewsPanel news = new NewsPanel();
	static Menu menu;
	InfoPanel info;
	static Properties radios;
	static String[] radioKeys = { "zur�ck", "Radio ausschalten" };
	static Radio radio = new Radio();
	static String[] items = { "Smart Home", "Licht ausschalten", "Radio", "Einstellungen" };
	static String[] settings = { "zur�ck", "Newsbar anzeigen" };
	static PiInterface piInterface = new PiInterface();
	static boolean displayMenu = false;
	static int i = 0;
	static String[] modules = { "zur�ck" };

	public GUI() {

		frame.setBounds(0, 0, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
		frame.setLayout(layout);
		frame.setUndecorated(true);
		// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.addKeyListener(this);
		frame.setResizable(false);
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
		menuPane.add(menu);

		radios = new Properties();
		try {
			radios.load(new FileInputStream("res/radios.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		String[] keys = new String[0];
		for (Enumeration<?> enumeration = radios.keys(); enumeration.hasMoreElements();) {

			String[] keys2 = new String[keys.length + 1];
			System.arraycopy(keys, 0, keys2, 0, keys.length);
			keys2[keys.length] = (String) enumeration.nextElement();
			keys = keys2;
		}

		Arrays.sort(keys);

		for (String s : keys) {

			String[] radioKeys2 = new String[radioKeys.length + 1];
			System.arraycopy(radioKeys, 0, radioKeys2, 0, radioKeys.length);
			radioKeys2[radioKeys.length] = s;
			radioKeys = radioKeys2;
		}

		// String[] radioKeys2 = new String[radioKeys.length + 2];
		// System.arraycopy(radioKeys, 0, radioKeys2, 0, radioKeys.length);
		// radioKeys2[radioKeys.length] = "Radio ausschalten";
		// radioKeys2[radioKeys.length + 1] = "zur�ck";
		// radioKeys = radioKeys2;

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

			menu.setBounds((int) ((frame.getWidth() - frame.getHeight() / 2) / 2), (int) (frame.getHeight() / 5),
					(int) (frame.getHeight() / 2), (int) (frame.getHeight() / 5));

			i = 500;

			if (displayMenu) {

				if (menu.getText().equals("zur�ck"))
					menu.setItems(items);
				else {

					if (menu.getText().equals("Radio"))
						menu.setItems(radioKeys.clone());

					else if (Essentials.isIncluded(radioKeys, menu.getText())) {
						
						System.out.println("Radio: " + menu.getText());
						
						radio.stop();
						radio.play(radios.getProperty(menu.getText()));
					}

					if (GUI.menu.getText().equals("Licht einschalten")) {
						GUI.items[1] = "Licht ausschalten";
						String[] m = GUI.items.clone();
						while (!m[1].startsWith("Licht")) {

							String overfloat = m[0];
							for (int k = 0; k < m.length; k++)
								if (k < m.length - 1)
									m[k] = m[k + 1];
							m[m.length - 1] = overfloat;
						}
						GUI.menu.setItems(m);
						GUI.piInterface.switchLight();
					} else if (GUI.menu.getText().equals("Licht ausschalten")) {
						GUI.items[1] = "Licht einschalten";
						String[] m = GUI.items.clone();
						while (!m[1].startsWith("Licht")) {

							String overfloat = m[0];
							for (int k = 0; k < m.length; k++)
								if (k < m.length - 1)
									m[k] = m[k + 1];
							m[m.length - 1] = overfloat;
						}
						GUI.menu.setItems(m);
						GUI.piInterface.switchLight();
					}

					if (menu.getText().equals("Smart Home"))
						menu.setItems(modules.clone());

					else if (Essentials.isIncluded(modules, menu.getText())) {

						for (ClientThread thread : SmartHomeServer.clients) {
							thread.changeState(Integer.parseInt(String.valueOf(menu.getText().charAt(6))));
						}
					}

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

						menu.setItems(items.clone());
						showMenu(false);
						displayMenu = false;
					}
				}).start();
			}
		}
	}

	static void showMenu(boolean b) {

		if (b) {
			menu.show(true);
		} else {
			menu.show(false);
		}
	}
}
