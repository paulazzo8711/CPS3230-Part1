package org.example;

public class WeatherData {
    private double temperature;
    private double precipitation;

    public WeatherData(double temperature, double precipitation) {
        this.temperature = temperature;
        this.precipitation = precipitation;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getPrecipitation() {
        return precipitation;
    }
}
