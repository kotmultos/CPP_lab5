package test;

import main.manager.DisplayManager;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DisplayManagerTest {

    @Test
    void testPrintOneString() {
        {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            String str = "a string to be printed";

            DisplayManager.print(str);
            assertEquals(">:\t" + str + "\r\n", outContent.toString());
        }

        {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            String str = "";

            DisplayManager.print(str);
            assertEquals(">:\t" + str + "\r\n", outContent.toString());
        }
    }

    @Test
    void testPrintStringWithDescription() {
        {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            String description = "description";
            String text = "here we have a very long text to test output";


            DisplayManager.print(description, text);
            assertEquals("---\t" + description + " ---\r\n" + text + "\r\n", outContent.toString());
        }

        {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            String description = "";
            String text = "";


            DisplayManager.print(description, text);
            assertEquals("---\t" + description + " ---\r\n" + text + "\r\n", outContent.toString());
        }
    }

    @Test
    void testPrintStringListWithDescription() {
        {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            String description = "description";
            List<String> list = new ArrayList<>() {
                {
                    add("sentence number 1.");
                    add("інше речення, чом би й не спробувати.");
                    add("What's the problem, dude?");
                }
            };

            StringBuilder builder = new StringBuilder();
            builder.append("---\t" + description + " ---\r\n");
            for (var item : list) {
                builder.append(item + "\r\n");
            }

            DisplayManager.print(description, list);
            assertEquals(builder.toString(), outContent.toString());
        }
    }
}
