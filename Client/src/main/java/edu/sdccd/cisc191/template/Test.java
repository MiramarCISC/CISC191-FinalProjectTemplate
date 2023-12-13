package edu.sdccd.cisc191.template;

import org.junit.jupiter.api.*;
import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class NumberGuessingGameTest {

    private final static String HIGH_SCORE_FILE = "highscores.ser";
    private NumberGuessingGame game;
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        game = new NumberGuessingGame();
        originalOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        cleanup();
    }

    @AfterEach
    void restore() {
        System.setOut(originalOut);
        cleanup();
    }

    void cleanup() {
        File file = new File(HIGH_SCORE_FILE);
        if (file.exists()) {
            assertTrue(file.delete());
        }
    }

    @Test
    void testGenerateSecretNumberList() {
        Node head = game.generateSecretNumberList(50);
        assertNotNull(head);
        assertEquals(50, head.value);
    }

    @Test
    void testLoadHighScoresWhenFileDoesNotExist() {
        ArrayList<Integer> highScores = game.loadHighScores();
        assertTrue(highScores.isEmpty());
    }

    @Test
    void testSaveAndLoadHighScores() {
        ArrayList<Integer> highScoresToSave = new ArrayList<>(Arrays.asList(30, 20, 10));
        game.saveHighScores(highScoresToSave);

        File file = new File(HIGH_SCORE_FILE);
        assertTrue(file.exists());

        ArrayList<Integer> loadedHighScores = game.loadHighScores();
        assertNotNull(loadedHighScores);
        assertEquals(highScoresToSave, loadedHighScores);
    }

    @Test
    void testUserMapInsertionAndRetrieval() {
        User user = new User("TestUser", 3);
        game.userMap.put(user.getName(), user);

        User retrievedUser = game.userMap.get("TestUser");
        assertNotNull(retrievedUser);
        assertEquals("TestUser", retrievedUser.getName());
        assertEquals(3, retrievedUser.getScore());
    }

    @Test
    void testRecursivelySearchLinkedList() {
        Node head = game.generateSecretNumberList(10);

        // Assuming 'containsValue' is the method that searches the linked list.
        boolean found = game.containsValue(head, 10);
        assertTrue(found);

        found = game.containsValue(head, 999);
        assertFalse(found);
    }


    // Simulated network communication test
    @Test
    void testSimulatedNetworking() {
        User user = new User("Alice", 3);
        user.sendMessageToServer();
        String expectedOutput = "Sending user data to server: User{name='Alice', score=3}";
        assertTrue(outContent.toString().contains(expectedOutput));
    }

    // Additional tests could be written for sorting functionality, but this would be straightforward as it uses Collections.sort()
}
