package org.example;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LocationService {
    private HttpHelper httpHelper;

    public LocationService(HttpHelper helper) {
        this.httpHelper = helper;
    }
    public static final String PRIMARY_API_URL = "http://ip-api.com/json";
    public static final String BACKUP_API_URL = "https://ipinfo.io/json";
    private static final String BACKUP_API_KEY = "cb0b82e57d8d4b";

    public String getCurrentLocation() {
        try {
            String location = callApiForLocation(PRIMARY_API_URL, false);

            if (location.equals("Unknown Location")) {
                location = getBackupLocation();
            }

            return location;
        } catch (Exception e) {
            e.printStackTrace();
            return "Unknown Location";
        }
    }

    public String getBackupLocation() {
        return callApiForLocation(BACKUP_API_URL, true);
    }

    private String callApiForLocation(String apiUrl, boolean useApiKey) {
        try {
            String response = httpHelper.makeHttpRequest(apiUrl, useApiKey);
            return parseLocationFromResponse(response, useApiKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unknown Location";
    }

    private String parseLocationFromResponse(String response, boolean isBackupApi) {
        JSONObject jsonObject = new JSONObject(response);

        if (!isBackupApi) {
            String status = jsonObject.optString("status");
            if ("success".equals(status)) {
                String city = jsonObject.optString("city");
                String country = jsonObject.optString("countryCode");
                return city + " " + country;
            }
        } else {
            String city = jsonObject.optString("city");
            String country = jsonObject.optString("country");
            if (!city.isEmpty() && !country.isEmpty()) {
                return city + " " + country;
            }
        }

        return "Unknown Location";
    }

    private void handleErrorResponse(int responseCode) {
        if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST) {
            System.out.println("\nBad request: Invalid parameters");
        } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
            System.out.println("\nAPI endpoint not found");
        } else {
            System.out.println("\nServer error: " + responseCode);
        }
    }
}
