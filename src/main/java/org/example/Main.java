package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        WeatherService weatherService = new WeatherService();
        HttpHelper realHttpHelper = new RealHttpHelper();

        LocationService locationService = new LocationService(realHttpHelper);
        RecommendationEngine recommendationEngine = new RecommendationEngine();


        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            UserInterface userInterface = new UserInterface(scanner);

            userInterface.displayMainMenu();

            int choice = userInterface.getUserChoice();

            switch (choice) {
                case 1:
                    userInterface.displayCurrentLocationRecommendation(
                            recommendationEngine.getRecommendationForCurrentWeather(
                                    weatherService.getCurrentWeather(
                                            locationService.getCurrentLocation()
                                    )
                            )
                    );
                    break;
                case 2:
                    String airportCode = userInterface.getAirportCode();
                    String date = userInterface.getDate();

                    userInterface.displayFutureLocationRecommendation(
                            recommendationEngine.getRecommendationForFutureWeather(
                                    weatherService.getFutureWeather(
                                            airportCode,
                                            date
                                    )
                            )
                    );
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    userInterface.displayInvalidChoiceMessage();
            }
        }

        scanner.close();
    }

}

