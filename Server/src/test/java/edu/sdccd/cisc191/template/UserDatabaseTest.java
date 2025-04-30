package edu.sdccd.cisc191.template;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the  UserDatabase  class.
 * Validates loading, saving, and accessing user data.
 */
class UserDatabaseTest {

    private static final String TEST_FILE_PATH = "Test/Resources/test_users.json";

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
     * Tests that the user database initializes with default values if no file exists.
     */
    @Test
    void testLoadOrInitializeDatabaseWithDefaults() {
        UserDatabase database = UserDatabase.getInstance();

        // Verify that the database contains default values
        List<User> users = database.getUserDatabase();
        assertNotNull(users, "User database should not be null.");
        assertFalse(users.isEmpty(), "User database should contain default values.");
    }

    /**
     * Tests saving and loading the user database from a JSON file.
     */
    @Test
    void testSaveAndLoadDatabase() {
        UserDatabase database = UserDatabase.getInstance();

        // Save the current database
        database.saveToFile();

        // Load the database again
        database.loadOrInitializeDatabase();

        List<User> users = database.getUserDatabase();
        assertNotNull(users, "User database should not be null after reloading.");
    }
}
