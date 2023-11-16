package org.example;

public class RecommendationEngine {
    public String getRecommendationForCurrentWeather(WeatherData weatherData) {
        boolean isCold = weatherData.getTemperature() <= 15;
        boolean isRaining = weatherData.getPrecipitation() > 0;

        StringBuilder recommendation = new StringBuilder("It is ");
        recommendation.append(isCold ? "cold" : "warm");
        recommendation.append(" so you should wear ");
        recommendation.append(isCold ? "warm" : "light");
        recommendation.append(" clothing.\n");
        recommendation.append("It is ");
        recommendation.append(isRaining ? "currently" : "not");
        recommendation.append(" raining so you ");
        recommendation.append(isRaining ? "do" : "don't");
        recommendation.append(" need an umbrella.");

        return recommendation.toString();
    }

    public String getRecommendationForFutureWeather(WeatherData weatherData) {
        boolean isCold = weatherData.getTemperature() <= 15;
        boolean isRaining = weatherData.getPrecipitation() > 0;

        StringBuilder recommendation = new StringBuilder("It will be ");
        recommendation.append(isCold ? "cold" : "warm");
        recommendation.append(" so you should wear ");
        recommendation.append(isCold ? "warm" : "light");
        recommendation.append(" clothing.\n");
        recommendation.append("It will");
        recommendation.append(isRaining ? "" : " not");
        recommendation.append(" be raining so you ");
        recommendation.append(isRaining ? "will" : "won't");
        recommendation.append(" need an umbrella.");

        return recommendation.toString();
    }
}
