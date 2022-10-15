package main.manager;

import java.util.List;

public class DisplayManager {
    public static void print(String description, String text) {
        System.out.println("---\t" + description + " ---");
        System.out.println(text);
    }

    public static void print(String description, List<String> text) {
        System.out.println("---\t" + description + " ---");
        for (String line : text) {
            System.out.println(line);
        }
    }

    public static void print(String s) {
        System.out.println(">:\t" + s);
    }
}
