package manager;

import java.util.Scanner;

public class InputManager {
    public static String getStringsFromConsole(String description) {
        System.out.println(description + "\nДля завершення вводу напишіть слово \"stop\"");

        StringBuilder result = new StringBuilder();
        String currentLine;
        Scanner scanner = new Scanner(System.in);
        char confirmation = 'n';
        do {
            System.out.print(">: ");
            currentLine = scanner.nextLine();
            if(currentLine.equals("stop")) {
                System.out.println("Are you sure to stop entering data? (y/n)");
                confirmation = scanner.next().trim().charAt(0);
            } else {
                result.append(currentLine);
            }
        } while (confirmation != 'y');

        return result.toString();
    }
}
