package smartmirror;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import json.JSONException;
import json.JSONObject;

public class WeatherFeed {

	String API_KEY = "";
	String location = "Marbach,DE";
	String units = "metric";
	String lang = "de";
	String owmurl = "";

	JSONObject weatherJSONObject;

	String describtion;
	String maxTemp;
	String minTemp;
	String temp;
	String iconLabel;
	String id;
	String icon;
	String rainprob;

	public WeatherFeed(String openweathermapclientkey) {
		API_KEY = openweathermapclientkey;
		weatherJSONObject = new JSONObject();
	}

	public String getWeatherDescribtion() {
		return describtion;
	}

	public String getMaxTemp() {
		return maxTemp;
	}

	public String getMinTemp() {
		return minTemp;
	}

	public String getTemp() {
		return temp;
	}

	public String getIconLabel() {
		return iconLabel;
	}

	public String getId() {
		return id;
	}

	public String getIcon() {
		return icon;
	}

	public String getRainProbability() {
		return rainprob;
	}

	public void getForecastToday() {
		owmurl = "http://api.openweathermap.org/data/2.5/weather?" + "q=" + location + "&units=" + units + "&lang="
				+ lang + "&appid=" + API_KEY;

		try {
			weatherJSONObject = readJsonFromUrl(owmurl);
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}

		for (Object key : weatherJSONObject.keySet()) {

			String keyStr = (String) key;
			String keyvalue = weatherJSONObject.get(keyStr).toString();

			if (keyvalue.contains("\"main\"")) {
				iconLabel = keyvalue.substring(keyvalue.indexOf("\"main\""), keyvalue.indexOf("id") - 3);
				iconLabel = iconLabel.replaceAll("\"main\":\"", "");
			}
			if (keyvalue.contains("\"description\"")) {
				describtion = keyvalue.substring(keyvalue.indexOf("\"description\""), keyvalue.indexOf("main") - 3);
				describtion = describtion.replaceAll("\"description\":\"", "");
			}
			if (keyvalue.contains("\"temp\"")) {
				temp = keyvalue.substring(keyvalue.indexOf("\"temp\""), keyvalue.indexOf("temp_min") - 2);
				temp = temp.replaceAll("\"temp\":", "");
			}
			if (keyvalue.contains("\"temp_min\"")) {
				minTemp = keyvalue.substring(keyvalue.indexOf("\"temp_min\""), keyvalue.indexOf("humidity") - 2);
				minTemp = minTemp.replaceAll("\"temp_min\":", "");
			}
			if (keyvalue.contains("\"temp_max\"")) {
				maxTemp = keyvalue.substring(keyvalue.indexOf("\"temp_max\""), keyvalue.indexOf("}"));
				maxTemp = maxTemp.replaceAll("\"temp_max\":", "");
			}
			if (keyvalue.contains("\"icon\"")) {
				icon = keyvalue.substring(keyvalue.indexOf("\"icon\":\""), keyvalue.indexOf("\"icon\":\"") + 11);
				icon = icon.replaceAll("\"icon\":\"", "");
			}

			// System.out.println("key: " + keyStr + " value: " + keyvalue);

		}

	}

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

}
