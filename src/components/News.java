package components;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class News {
	/*
	 * @author Johannes Groﬂ
	 * 
	 * @version 1.0
	 * 
	 */
	static String rssUrl = "http://www.tagesschau.de/xml/rss2"; //https://www.welt.de/feeds/topnews.rss
	boolean consoleoutput = false;

	public News() {

	}

	public News(String rssSource) {
		rssUrl = rssSource;
	}

	public News(boolean isConsoleActive) {
		consoleoutput = isConsoleActive;
	}

	public News(String rssSource, boolean isConsoleActive) {
		consoleoutput = isConsoleActive;
		rssUrl = rssSource;
	}

	public void setConsoleActive(boolean activeConsole) {
		consoleoutput = activeConsole;
	}

	public boolean isConsoleActive() {
		return consoleoutput;
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
				if (consoleoutput)
					System.out.println(line);

			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
				if (consoleoutput)
					System.out.println(line);

			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sourceCode.split("\n");

	}

}
