package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrentRecommendationEngineTest {
    private RecommendationEngine recommendationEngine;

    @BeforeEach
    void setUp() {
        recommendationEngine = new RecommendationEngine();
    }

    @Test
    void testGetRecommendationForCurrentWeather_ColdAndRaining() {
        WeatherData weatherData = new WeatherData(10, 5);
        String recommendation = recommendationEngine.getRecommendationForCurrentWeather(weatherData);

        String expectedRecommendation = "It is cold so you should wear warm clothing.\n" +
                "It is currently raining so you do need an umbrella.";
        assertEquals(expectedRecommendation, recommendation);
    }
    @Test
    void testGetRecommendationForCurrentWeather_ColdAndNotRaining() {
        WeatherData weatherData = new WeatherData(10, 0);
        String recommendation = recommendationEngine.getRecommendationForCurrentWeather(weatherData);

        String expectedRecommendation = "It is cold so you should wear warm clothing.\n" +
                "It is not raining so you don't need an umbrella.";
        assertEquals(expectedRecommendation, recommendation);
    }
    @Test
    void testGetRecommendationForCurrentWeather_WarmAndNotRaining() {
        WeatherData weatherData = new WeatherData(20, 0);
        String recommendation = recommendationEngine.getRecommendationForCurrentWeather(weatherData);

        String expectedRecommendation = "It is warm so you should wear light clothing.\n" +
                "It is not raining so you don't need an umbrella.";
        assertEquals(expectedRecommendation, recommendation);
    }
    @Test
    void testGetRecommendationForCurrentWeather_WarmAndRaining() {
        WeatherData weatherData = new WeatherData(20, 5);
        String recommendation = recommendationEngine.getRecommendationForCurrentWeather(weatherData);

        String expectedRecommendation = "It is warm so you should wear light clothing.\n" +
                "It is currently raining so you do need an umbrella.";
        assertEquals(expectedRecommendation, recommendation);
    }
}
