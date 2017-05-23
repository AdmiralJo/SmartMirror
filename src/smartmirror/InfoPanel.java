package smartmirror;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import essentials.Graphics;

public class InfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	GridBagLayout layout = new GridBagLayout();
	JPanel pnlDate = new JPanel(), pnlTemp = new JPanel();
	Font font;
	JLabel lblTime, lblDate, lblTemp, lblMinTemp, lblMaxTemp, lblIcon, lblWeather;

	public InfoPanel() {

		setLayout(layout);
		setBackground(Color.black);

		pnlDate.setBackground(Color.black);
		pnlDate.setLayout(layout);
		Graphics.addComponent(this, layout, pnlDate, 0, 0, 1, 1, 0, 0, new Insets(0, 0, 0, 0));

		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("res/info.ttf")).deriveFont(0, 82);
		} catch (FontFormatException | IOException e) {
			font = new Font("Verdana", 0, 82);
			e.printStackTrace();
		}

		lblTime = new JLabel();
		lblTime.setFont(font.deriveFont(0, 82));
		lblTime.setForeground(Color.white);
		Graphics.addComponent(pnlDate, layout, lblTime, 0, 0, 1, 1, 0, 0, new Insets(10, 50, 0, 10));

		Graphics.addComponent(this, layout, new JLabel(), 1, 0, 1, 1, 1, 0, new Insets(0, 0, 0, 0));

		lblDate = new JLabel();
		lblDate.setFont(font.deriveFont(0, 28));
		lblDate.setForeground(Color.white);
		Graphics.addComponent(pnlDate, layout, lblDate, 0, 1, 1, 1, 0, 0, new Insets(0, 50, 0, 10));

		SimpleDateFormat time = new SimpleDateFormat("HH:mm");
		DateTimeFormatter date = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);

		new Thread(new Runnable() {

			@Override
			public void run() {

				while (true) {

					lblTime.setText(time.format(new Date()));

					String s = date.format(LocalDate.now());
					s = s.replaceAll("Montag", "Mo");
					s = s.replaceAll("Dienstag", "Di");
					s = s.replaceAll("Mittwoch", "Mi");
					s = s.replaceAll("Donnerstag", "Do");
					s = s.replaceAll("Freitag", "Fr");
					s = s.replaceAll("Samstag", "Sa");
					s = s.replaceAll("Sonntag", "So");
					lblDate.setText(s);

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		WeatherFeed weather = new WeatherFeed("cb40d8e6f09191df0bad690aabe8956d");
		weather.getForecastToday();

		pnlTemp.setBackground(Color.black);
		pnlTemp.setLayout(layout);
		Graphics.addComponent(this, layout, pnlTemp, 2, 0, 1, 1, 0, 0, new Insets(0, 0, 0, 0));

		// lblIcon = new JLabel("");
		// try {
		// BufferedImage image = ImageIO
		// .read(new URL("http://openweathermap.org/img/w/" + weather.getIcon()
		// + ".png"));
		// lblIcon = new JLabel(new ImageIcon(image));
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// Graphics.addComponent(pnlTemp, layout, lblIcon, 0, 0, 1, 1, 0, 0, new
		// Insets(0, 0, 0, 0));

		lblTemp = new JLabel("", SwingConstants.RIGHT);
		lblTemp.setFont(font.deriveFont(0, 82));
		lblTemp.setForeground(Color.white);
		Graphics.addComponent(pnlTemp, layout, lblTemp, 0, 0, 1, 1, 0, 0, new Insets(10, 10, 0, 50));

		lblWeather = new JLabel("", SwingConstants.RIGHT);
		lblWeather.setFont(font.deriveFont(0, 28));
		lblWeather.setForeground(Color.white);
		Graphics.addComponent(pnlTemp, layout, lblWeather, 0, 1, 1, 1, 0, 0, new Insets(0, 10, 0, 50));

		// JPanel panel = new JPanel();
		// panel.setBackground(Color.black);
		// panel.setLayout(layout);
		// Graphics.addComponent(pnlTemp, layout, panel, 0, 1, 2, 1, 0, 0, new
		// Insets(0, 0, 0, 0));
		//
		// lblMinTemp = new JLabel("", SwingConstants.RIGHT);
		// lblMinTemp.setFont(font.deriveFont(0, 28));
		// lblMinTemp.setForeground(Color.white);
		// Graphics.addComponent(panel, layout, lblMinTemp, 0, 0, 1, 1, 0, 0,
		// new Insets(0, 0, 0, 10));
		//
		// lblMaxTemp = new JLabel("", SwingConstants.RIGHT);
		// lblMaxTemp.setFont(font.deriveFont(0, 28));
		// lblMaxTemp.setForeground(Color.white);
		// Graphics.addComponent(panel, layout, lblMaxTemp, 1, 0, 1, 1, 0, 0,
		// new Insets(0, 10, 0, 50));

		new Thread(new Runnable() {

			@Override
			public void run() {

				while (true) {

					weather.getForecastToday();
					lblTemp.setText(String.valueOf((int) (Double.parseDouble(weather.getTemp()))) + "°C");
					lblWeather.setText(weather.getWeatherDescribtion());

					// lblMinTemp.setText("min " + weather.getMinTemp() + "°C");
					// lblMaxTemp.setText("max " + weather.getMaxTemp() + "°C");
					//
					// try {
					// BufferedImage image = ImageIO
					// .read(new URL("http://openweathermap.org/img/w/" +
					// weather.getIcon() + ".png"));
					// lblIcon = new JLabel(new ImageIcon(image));
					// } catch (IOException e) {
					// e.printStackTrace();
					// }

					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
