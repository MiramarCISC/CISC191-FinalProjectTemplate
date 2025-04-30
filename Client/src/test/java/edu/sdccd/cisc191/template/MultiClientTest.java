package edu.sdccd.cisc191.template;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests to see if we can safely run multiple clients.
 * Simulates concurrent modifications to a single user object through
 * multiple client connections and validates server-client communication.
 * Need to run Server.java before running this test.
 * Will fail if any threads are dropped.
 */
class MultiClientTest {

    /**
     * Simulates concurrent client requests to modify a single user object, with the server running.
     */
    @Test
    void testConcurrentUserModifications() throws Exception {

        // Initialize the client
        Client client = new Client();
        client.startConnection("localhost", 4444);

        // Fetch initial state of the user
        User initialUser = client.userGetRequest(2);
        assertNotNull(initialUser, "Initial user state should not be null.");
        System.out.println(String.format("Initial User State: Name[%s], Money[%d]", initialUser.getName(), initialUser.getMoney()));
        client.stopConnection();

        // Create a thread pool for concurrent client requests
        ExecutorService executor = Executors.newFixedThreadPool(10); // Adjust thread pool size for testing
        int testRuns = 50; // Reduce test runs for quicker execution in tests
        for (int i = 0; i < testRuns; i++) {
            executor.submit(() -> {
                try {
                    client.startConnection("localhost", 4444);

                    // Modify User ID 2 with a unique modification
                    Map<String, Object> attributes = new HashMap<>();
                    attributes.put("Name", "User" + Thread.currentThread().getId());
                    attributes.put("Increment Money", 1);

                    User modifiedUser = client.userModifyRequest(2, attributes);
                    assertNotNull(modifiedUser, "Modified user state should not be null.");
                    System.out.println("Modified User: " + modifiedUser);

                    client.stopConnection();
                    // Stagger the requests
                    Thread.sleep((long) (Math.random() * 100));
                } catch (Exception e) {
                    e.printStackTrace();
                    fail("Exception occurred during client modification: " + e.getMessage());
                }
            });
        }

        // Shut down the executor after tasks complete
        executor.shutdown();
        while (!executor.isTerminated()) {
            // Wait for all tasks to finish
        }

        // Retrieve the final state of User ID 2
        client.startConnection("localhost", 4444);
        User finalUser = client.userGetRequest(2);
        assertNotNull(finalUser, "Final user state should not be null.");
        System.out.println(String.format("Final User State: Name[%s], Money[%d]", finalUser.getName(), finalUser.getMoney()));

        // Assertions to validate modifications. Subtract 1 because we start from 0.
        assertEquals(initialUser.getMoney() + testRuns, finalUser.getMoney(),
                "Final user's money should increase by the total amount of modifications.");
        assertTrue(finalUser.getName().startsWith("User"), "Final user's name should reflect the last modification.");
        System.out.println("Assertions passed!");
        client.stopConnection();

    }
}
