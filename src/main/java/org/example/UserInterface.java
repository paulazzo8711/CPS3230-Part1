package org.example;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class UserInterface {
    private Scanner scanner;

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayMainMenu() {
        System.out.println("\nWeatherWear.com");
        System.out.println("---------------");
        System.out.println("1. Recommend clothing for current location");
        System.out.println("2. Recommend clothing for future location");
        System.out.println("3. Exit");
        System.out.print("Enter choice: ");
    }

    public int getUserChoice() {
        return scanner.nextInt();
    }

    public String getAirportCode() {
        String airportCode;
        boolean validICAO = false;

        do {
            System.out.print("Enter Airport ICAO Code: ");
            airportCode = scanner.next().toUpperCase();

            if (airportCode.matches("^[A-Z]{4}$")) {
                validICAO = true;
            } else {
                System.out.println("Invalid ICAO code. Please enter a valid 4-character code.");
            }
        } while (!validICAO);

        return airportCode;
    }

    public String getDate() {
        String date;
        boolean validDate = false;

        do {
            System.out.print("Enter Date (YYYY-MM-DD): ");
            date = scanner.next();

            try {

                LocalDate inputDate = LocalDate.parse(date);

                LocalDate maxAllowedDate = LocalDate.now().plusDays(10);

                if (inputDate.isBefore(maxAllowedDate)) {
                    validDate = true;
                } else {
                    System.out.println("Date cannot be more than 10 days in the future.");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD format.");
            }
        } while (!validDate);

        return date;
    }

    public void displayCurrentLocationRecommendation(String recommendation) {
        System.out.println("Recommendation for Current Location:");
        System.out.println(recommendation);
    }

    public void displayFutureLocationRecommendation(String recommendation) {
        System.out.println("Recommendation for Future Location:");
        System.out.println(recommendation);
    }

    public void displayInvalidChoiceMessage() {
        System.out.println("\nInvalid choice, please try again.");
    }
}
