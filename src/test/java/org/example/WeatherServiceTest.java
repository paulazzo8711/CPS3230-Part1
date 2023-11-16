package org.example;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherServiceTest {
    @Test
    public void testGetCurrentWeatherWithValidLocation() {
        WeatherService weatherService = new WeatherService();
        String validLocation = "New York";
        WeatherData weatherData = weatherService.getCurrentWeather(validLocation);

        assertNotNull(weatherData);
        assertNotEquals(0, weatherData.getTemperature());
    }

    @Test
    public void testGetFutureWeatherWithValidICAOCodeAndDate() {
        WeatherService weatherService = new WeatherService();
        String validICAOCode = "KSFO";
        String validDate = "2023-11-15";
        WeatherData weatherData = weatherService.getFutureWeather(validICAOCode, validDate);

        assertNotNull(weatherData);
        assertNotEquals(0, weatherData.getTemperature());
    }


}
