package edu.sdccd.cisc191.template;
/**
 * Java Reflection and Invoking Methods
 * https://www.oracle.com/technical-resources/articles/java/javareflection.html
 * https://docs.oracle.com/javase/tutorial/reflect/member/methodInvocation.html
 * Threads (Platform.runLater())
 * https://math.hws.edu/javanotes/c12/s2.html
 */
import java.lang.reflect.Method; // Interactive Console & GUI
import javafx.application.Platform; // GUI
import javafx.embed.swing.JFXPanel; // GUI
import javafx.scene.control.Alert; // GUI
//import org.junit.Before; // GUI
import javafx.stage.Stage; // GUI
import java.io.*; // ByteArrayInputStream, inputStream
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.HashMap; // OOP
import java.io.ByteArrayInputStream; // IO
import java.io.InputStream; // IO
import java.util.Scanner; // IO
import java.util.ArrayList; // G&C

public class ServerTest {
    @Test // Interactive Console Module
    public void testGetValueAtIndex() throws Exception {
        String input = "1\n0\n0\n"; // @input {1,0,0}
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Server server = new Server();
        // Access private method getValueAtIndex() using reflection.
        Method getValueAtIndexMethod = Server.class.getDeclaredMethod("getValueAtIndex");
        getValueAtIndexMethod.setAccessible(true);
        getValueAtIndexMethod.invoke(server);
        // Assert expected result.
        assertEquals("VALUE AT INDEX (0, 0): 5", "EXPECTED_RESULT");
    }
    @BeforeAll // JavaFX GUI of Interactive Console Module
    public void setup() {
        // Initialize JavaFX toolkit.
        JFXPanel panel = new JFXPanel();
        Platform.runLater(() -> {
        });
    }

    @Test // JavaFX GUI of Interactive Console Module
    public void testGetValueAtIndexGUI() throws Exception {
        Server server = new Server();
        server.start(new Stage());
        /*
         * Simulate clicking of button to test functionality.
         * Access private method getValueAtIndexGUI() using reflection.
         */
        Method getValueAtIndexGUIMethod = Server.class.getDeclaredMethod("getValueAtIndexGUI");
        getValueAtIndexGUIMethod.setAccessible(true);
        getValueAtIndexGUIMethod.invoke(server);
        // Assert expected result.
        assertEquals("VALUE AT INDEX (0, 0): 5", "EXPECTED_RESULT");
    }

    @Test // OOP
    public void testUserGetName() {
        User user = new User("USER1");
        assertEquals("USER1", user.getName());
    }

    @Test // IO
    public void testScannerInput() {
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        int value = scanner.nextInt();
        assertEquals(1, value);
    }

    @Test // G&C
    public void testHashMapPutAndGet() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        assertEquals(Integer.valueOf(1), map.get("A"));
        assertEquals(Integer.valueOf(2), map.get("B"));
    }

    @Test // G&C (Good to know: Major Food Allergens)
    public void testArrayListSize() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Milk");
        list.add("Peanuts");
        list.add("Shellfish");
        list.add("Wheat");
        list.add("Soy");
        assertEquals(5, list.size());
    }
}