package test;

import java.text.SimpleDateFormat;
import java.util.Date;

import components.Weather;

public class TestWeatherMain {
	private static final String API_KEY = "a38beddf76b714c21ca7a4b18cbce77f";

	public static void main(String[] args) {
		Weather weather = new Weather(API_KEY);
		weather.getForecastToday();

		// System.out.println("Min Temp:" + weather.getMinTemp());
		// System.out.println("Max Temp:" + weather.getMaxTemp());
		// System.out.println("Temp:" + weather.getTemp());
		// System.out.println("Weather: " + weather.getWeatherDescribtion());
		// System.out.println("IconLabel: " + weather.getIconLabel());
		//
		// System.out.println();
		//
		// weather.getForecastInFiveDays();
		//
		// System.out.println("Min Temp:" + weather.getMinTemp());
		// System.out.println("Max Temp:" + weather.getMaxTemp());
		// System.out.println("Temp:" + weather.getTemp());
		// System.out.println("Weather: " + weather.getWeatherDescribtion());
		// System.out.println("IconLabel: " + weather.getIconLabel());

		Date date = new Date();
		date.setTime((long) 1487351515 * 1000);

		SimpleDateFormat df = new SimpleDateFormat("HH:mm dd.MM.yyyy");

		System.out.println(df.format(date));
	}

}
