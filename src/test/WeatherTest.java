package test;

import smartmirror.WeatherFeed;

public class WeatherTest {

	public static void main(String[] args) {

		WeatherFeed feed = new WeatherFeed("cb40d8e6f09191df0bad690aabe8956d");
		feed.getForecastToday();
		String temp = feed.getTemp();
		System.out.println(temp);

		System.out.println(feed.getIcon());
	}
}
