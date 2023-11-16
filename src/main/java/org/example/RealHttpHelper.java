package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RealHttpHelper implements HttpHelper {

    private static final String BACKUP_API_KEY = "cb0b82e57d8d4b";

    @Override
    public String makeHttpRequest(String urlString, boolean useApiKey) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(3000);

        if (useApiKey) {
            connection.setRequestProperty("Authorization", "Bearer " + BACKUP_API_KEY);
        }

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } else {
            throw new Exception("HTTP error response: " + responseCode);
        }
    }
}
