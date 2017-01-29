package test;

import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import components.Weather;

public class TestWeatherMain {
	private static final String API_KEY = "your token here";

	public static void main(String[] args) {
		Weather weather = new Weather(API_KEY);
		weather.getForecastToday();

		System.out.println("Min Temp:" + weather.getMinTemp());
		System.out.println("Max Temp:" + weather.getMaxTemp());
		System.out.println("Temp:" + weather.getTemp());
		System.out.println("Weather: " + weather.getWeatherDescribtion());
		System.out.println("IconLabel: " + weather.getIconLabel());

	}

}
