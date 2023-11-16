package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FutureRecommendationEngineTest {
    private RecommendationEngine recommendationEngine;

    @BeforeEach
    void setUp() {
        recommendationEngine = new RecommendationEngine();
    }

    @Test
    void testGetRecommendationForFutureWeather_ColdAndRaining() {
        WeatherData weatherData = new WeatherData(10, 5);
        String recommendation = recommendationEngine.getRecommendationForFutureWeather(weatherData);

        String expectedRecommendation = "It will be cold so you should wear warm clothing.\n" +
                "It will be raining so you will need an umbrella.";
        assertEquals(expectedRecommendation, recommendation);
    }
    @Test
    void testGetRecommendationForFutureWeather_ColdAndNotRaining() {
        WeatherData weatherData = new WeatherData(10, 0);
        String recommendation = recommendationEngine.getRecommendationForFutureWeather(weatherData);

        String expectedRecommendation = "It will be cold so you should wear warm clothing.\n" +
                "It will not be raining so you won't need an umbrella.";
        assertEquals(expectedRecommendation, recommendation);
    }
    @Test
    void testGetRecommendationForFutureWeather_WarmAndNotRaining() {
        WeatherData weatherData = new WeatherData(20, 0);
        String recommendation = recommendationEngine.getRecommendationForFutureWeather(weatherData);

        String expectedRecommendation = "It will be warm so you should wear light clothing.\n" +
                "It will not be raining so you won't need an umbrella.";
        assertEquals(expectedRecommendation, recommendation);
    }
    @Test
    void testGetRecommendationForFutureWeather_WarmAndRaining() {
        WeatherData weatherData = new WeatherData(20, 5);
        String recommendation = recommendationEngine.getRecommendationForFutureWeather(weatherData);

        String expectedRecommendation = "It will be warm so you should wear light clothing.\n" +
                "It will be raining so you will need an umbrella.";
        assertEquals(expectedRecommendation, recommendation);
    }
}
