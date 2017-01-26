package test;

import components.News;

public class TestNewsMain {

	public static void main(String[] args) {

		String[] titles;

		News news = new News();
		
		
		titles = news.getNews("http://www.tagesschau.de/xml/rss2");

		for (int i = 0; i < titles.length; i++) {
			System.out.println(titles[i]);
		}

	}

}
