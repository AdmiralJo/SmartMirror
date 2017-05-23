package smartmirror;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JPanel;

public class NewsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	NewsFeed feed = new NewsFeed();
	String[] newsarray = feed.getNews();
	String news = "";
	int x, y;

	public NewsPanel() {

		setBackground(Color.black);
		setPreferredSize(new Dimension(0, 80));

		for (String s : newsarray) {
			news += s + " +++ ";
		}
		news = news.substring(0, news.length() - 5);
	}

	public void paint(Graphics graphics) {
		super.paint(graphics);

		Graphics2D g = (Graphics2D) graphics;
		Font font;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("res/font.otf")).deriveFont(0, 32);
		} catch (FontFormatException | IOException e) {
			font = new Font("Verdana", 0, 32);
			e.printStackTrace();
		}
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString(news, x, y);

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		x -= 1;
		y = getHeight() - 29;

		if (x < (0 - g.getFontMetrics().stringWidth(news) + getWidth())) {

			news = "";
			newsarray = feed.getNews();
			for (String s : newsarray) {
				news += s + " +++ ";
			}
			news = news.substring(0, news.length() - 5);
			x = 0;
		}

		repaint();
	}
}
