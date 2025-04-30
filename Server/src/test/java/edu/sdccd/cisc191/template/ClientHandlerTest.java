package edu.sdccd.cisc191.template;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the ClientHandler class.
 * Validates that client requests are processed correctly and appropriate
 * responses are sent.
 */
class ClientHandlerTest {

    private Socket mockSocket;
    private PrintWriter writer;
    private BufferedReader reader;
    private ClientHandler clientHandler;

    @BeforeEach
    void setUp() throws IOException {
        // Mock the socket and its streams
        mockSocket = mock(Socket.class);
        writer = new PrintWriter(new StringWriter(), true);
        reader = new BufferedReader(new StringReader(""));

        when(mockSocket.getOutputStream()).thenReturn(new ByteArrayOutputStream());
        when(mockSocket.getInputStream()).thenReturn(new ByteArrayInputStream("".getBytes()));

        clientHandler = new ClientHandler(mockSocket);
    }

    /**
     * Tests that the  ClientHandler  correctly handles a valid request.
     */
    @Test
    void testRunHandlesValidRequest() throws Exception {
        // Prepare a valid request
        String mockRequest = """
            {"requestType": "GetSize", "id": 1, "attributesToModify": {}}
        """;

        // Simulate receiving the request
        when(mockSocket.getInputStream()).thenReturn(new ByteArrayInputStream(mockRequest.getBytes()));

        // Run the handler
        clientHandler.run();

        // Validate behavior
        verify(mockSocket, atLeastOnce()).getOutputStream();
    }

    /**
     * Tests that the  ClientHandler  gracefully handles an invalid request.
     */
    @Test
    void testRunHandlesInvalidRequest() throws IOException {
        // Simulate an invalid request
        when(mockSocket.getInputStream()).thenReturn(new ByteArrayInputStream("INVALID_REQUEST".getBytes()));

        assertDoesNotThrow(() -> clientHandler.run(), "ClientHandler should not throw exceptions for invalid requests.");
    }
}
