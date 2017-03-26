package smartmirror;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class NewsFeed {
	/*
	 * @author Johannes Groß
	 * 
	 * @version 1.0
	 * 
	 */
	static String rssUrl = "http://www.tagesschau.de/xml/rss2"; //https://www.welt.de/feeds/topnews.rss
	boolean console = false;

	public NewsFeed() {

	}

	public NewsFeed(String rssSource) {
		rssUrl = rssSource;
	}

	public NewsFeed(boolean isConsoleActive) {
		console = isConsoleActive;
	}

	public NewsFeed(String rssSource, boolean isConsoleActive) {
		console = isConsoleActive;
		rssUrl = rssSource;
	}

	public void setConsoleActive(boolean activeConsole) {
		console = activeConsole;
	}

	public boolean isConsoleActive() {
		return console;
	}

	public void setNewsSource(String rssSource) {
		rssUrl = rssSource;
	}

	public String getNewsSource() {
		return rssUrl;
	}

	public String[] getNews() {
		String sourceCode = "";
		try {
			URL url = new URL(rssUrl);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

			String line;

			while ((line = in.readLine()) != null) {
				if (line.contains("<item>")) {

					if ((line = in.readLine()) != null && line.contains("<title>")) {
						int firstPos = line.indexOf("<title>");
						String temp = line.substring(firstPos);
						temp = temp.replace("<title>", "");

						int lastPos = temp.indexOf("</title>");
						temp = temp.substring(0, lastPos);

						sourceCode += temp + "\n";
					}
				}
				if (console)
					System.out.println(line);

			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sourceCode = sourceCode.replaceAll("Ã¤", "ä");
		sourceCode = sourceCode.replaceAll("ÃŸ", "ß");
		sourceCode = sourceCode.replaceAll("Ã¼", "ü");
		sourceCode = sourceCode.replaceAll("Ã¶", "ö");
		
		
		return sourceCode.split("\n");

	}
	
	public String[] getNews(String source) {
		rssUrl = source;
		String sourceCode = "";
		try {
			URL url = new URL(rssUrl);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

			String line;

			while ((line = in.readLine()) != null) {
				if (line.contains("<item>")) {

					if ((line = in.readLine()) != null && line.contains("<title>")) {
						int firstPos = line.indexOf("<title>");
						String temp = line.substring(firstPos);
						temp = temp.replace("<title>", "");

						int lastPos = temp.indexOf("</title>");
						temp = temp.substring(0, lastPos);

						sourceCode += temp + "\n";
					}
				}
				if (console)
					System.out.println(line);

			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sourceCode.split("\n");

	}

}
