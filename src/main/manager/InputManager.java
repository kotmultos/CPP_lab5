package main.manager;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class InputManager {
    public static String getStringsFromConsole() {
        StringBuilder result = new StringBuilder();
        String currentLine;
        Scanner scanner = new Scanner(System.in);

        do {
            currentLine = scanner.nextLine().trim();
            result.append(currentLine + " ");

        } while (!currentLine.equals(""));

        return result.toString().trim();
    }

    public static String getStringsFromFile(String filename) {
        StringBuilder result = new StringBuilder();
        String currentLine;

        try (FileReader reader = new FileReader(filename)) {
            Scanner scan = new Scanner(reader);
            while (scan.hasNextLine()) {
                currentLine = scan.nextLine().trim();
                result.append(currentLine + " ");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from file./// filename: " + filename);
            System.out.println(e);
            return "An error while reading a file ///";
        }
        return result.toString();
    }
}
