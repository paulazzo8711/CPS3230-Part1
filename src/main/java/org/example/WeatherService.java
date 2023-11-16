package org.example;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.net.URLEncoder;

public class WeatherService {
    private static final String API_BASE_URL = "https://weatherapi-com.p.rapidapi.com";
    private static final String API_KEY = "3366c1d753mshfeaa18e278520cap100a6djsn541dcc77cf5b";

    public WeatherData getCurrentWeather(String location) {
        try {
            String encodedLocation = URLEncoder.encode(location, StandardCharsets.UTF_8.toString());
            URL url = new URL(API_BASE_URL + "/current.json?q=" + encodedLocation);

            HttpURLConnection connection = createConnection(url);
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                String response = readResponse(connection);
                return parseWeatherData(response);
            } else {
                handleErrorResponse(responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new WeatherData(0, 0);
    }

    public WeatherData getFutureWeather(String icaoCode, String date) {
        try {
            String encodedICAOCode = URLEncoder.encode(icaoCode, StandardCharsets.UTF_8.toString());
            String encodedDate = URLEncoder.encode(date, StandardCharsets.UTF_8.toString());
            URL url = new URL(API_BASE_URL + "/forecast.json?q=metar:" + encodedICAOCode + "&dt=" + encodedDate);

            HttpURLConnection connection = createConnection(url);
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                String response = readResponse(connection);
                return parseWeatherData(response);
            } else {
                handleErrorResponse(responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new WeatherData(0, 0);
    }

    private HttpURLConnection createConnection(URL url) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("x-rapidapi-key", API_KEY);
        connection.setRequestProperty("x-rapidapi-host", "weatherapi-com.p.rapidapi.com");
        return connection;
    }

    private String readResponse(HttpURLConnection connection) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    private WeatherData parseWeatherData(String json) {
        try {

            JSONObject jsonObject = new JSONObject(json);
            JSONObject current = jsonObject.getJSONObject("current");

            double temperature = current.getDouble("temp_c");
            double precipitation = current.getDouble("precip_mm");

            WeatherData weatherData = new WeatherData(temperature, precipitation);

            return weatherData;
        } catch (JSONException e) {
            e.printStackTrace();

        }
        return new WeatherData(0, 0);
    }

    private void handleErrorResponse(int responseCode) {
        if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST) {
            System.out.println("Bad request: Invalid parameters");
        } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
            System.out.println("API endpoint not found");
        } else {
            System.out.println("Server error: " + responseCode);
        }
    }
}
