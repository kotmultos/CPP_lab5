package test;

import main.manager.InputManager;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class InputManagerTest {

    @Test
    void getStringsFromConsoleTest() {
        {
            System.setIn(new ByteArrayInputStream("just normal text from a line\n\n".getBytes()));
            assertEquals("just normal text from a line", InputManager.getStringsFromConsole());
        }

        {
            System.setIn(new ByteArrayInputStream("text\nwritten in multiple\n lines\n\n".getBytes()));
            assertEquals("text written in multiple lines", InputManager.getStringsFromConsole());
        }

        {
            System.setIn(new ByteArrayInputStream("\n\n".getBytes()));
            assertEquals("", InputManager.getStringsFromConsole());
        }

        {
            System.setIn(new ByteArrayInputStream("Якийсь дивний\nтекст       з       купою         пробілів      !\n\n".getBytes()));
            assertEquals("Якийсь дивний текст       з       купою         пробілів      !", InputManager.getStringsFromConsole());
        }

    }
}