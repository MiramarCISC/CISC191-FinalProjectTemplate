package edu.sdccd.cisc191.template;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the  GameDatabase  class.
 * Validates loading, saving, and accessing game data.
 */
class GameDatabaseTest {

    private static final String TEST_FILE_PATH = "Test/Resources/test_games.json";

    @BeforeEach
    void setUp() {
        // Ensure the test file is clean
        File testFile = new File(TEST_FILE_PATH);
        if (testFile.exists()) {
            assertTrue(testFile.delete(), "Test JSON file should be deleted before testing.");
        }
    }

    @AfterEach
    void tearDown() {
        // Clean up the test file
        File testFile = new File(TEST_FILE_PATH);
        if (testFile.exists()) {
            assertTrue(testFile.delete(), "Test JSON file should be deleted after testing.");
        }
    }

    /**
     * Tests that the database initializes with default values if no file exists.
     */
    @Test
    void testLoadOrInitializeDatabaseWithDefaults() {
        GameDatabase database = GameDatabase.getInstance();

        // Verify that the database contains default values
        List<Game> games = database.getGameDatabase();
        assertNotNull(games, "Game database should not be null.");
        assertFalse(games.isEmpty(), "Game database should contain default values.");
    }

    /**
     * Tests saving and loading the database from a JSON file.
     */
    @Test
    void testSaveAndLoadDatabase() {
        GameDatabase database = GameDatabase.getInstance();

        // Save the current database
        database.saveToFile();

        // Load the database again
        database.loadOrInitializeDatabase();

        List<Game> games = database.getGameDatabase();
        assertNotNull(games, "Game database should not be null after reloading.");
    }
}
