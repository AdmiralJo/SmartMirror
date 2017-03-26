package test;

import smartmirror.NewsFeed;

public class TestNewsFeed {

	public static void main(String[] args) {
		
		NewsFeed feed = new NewsFeed();
		String[] news = feed.getNews();
		
		for (String string : news) {
			System.out.println(string);
		}
	}
}
