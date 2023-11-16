package org.example;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserInterfaceTest {
    private UserInterface userInterface;
    private Scanner mockScanner;

    @BeforeEach
    void setUp() {

        String input = "1\n";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        mockScanner = new Scanner(in);
        userInterface = new UserInterface(mockScanner);
    }

    @Test
    void testGetUserChoice() {
        int choice = userInterface.getUserChoice();
        assertEquals(1, choice);
    }

    @Test
    void testGetAirportCode() {
        String input = "ABCD\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        UserInterface ui = new UserInterface(scanner);

        String airportCode = ui.getAirportCode();
        assertEquals("ABCD", airportCode);
    }

    @Test
    void testGetDate() {

        String input = "2023-11-15\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        UserInterface ui = new UserInterface(scanner);

        String date = ui.getDate();
        assertEquals("2023-11-15", date);
    }

    @Test
    void testGetDateWithInvalidFormat() {

        String input = "11/15/2023\n2023-11-15\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        UserInterface ui = new UserInterface(scanner);

        String date = ui.getDate();
        assertEquals("2023-11-15", date);
    }

    @Test
    void testGetDateWithFutureDate() {

        String input = "2023-11-25\n2023-11-15\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        UserInterface ui = new UserInterface(scanner);

        String date = ui.getDate();
        assertEquals("2023-11-25", date);
    }
}
