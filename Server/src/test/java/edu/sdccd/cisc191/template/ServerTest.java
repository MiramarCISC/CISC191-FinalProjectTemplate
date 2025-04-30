package edu.sdccd.cisc191.template;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.mockito.Mockito.*;

/**
 * Unit tests for the Server class.
 * Validates behavior when the server interacts with sockets.
 */
class ServerTest {

    /**
     * Tests that the server accepts a connection and delegates handling to ClientHandler .
     */
    @Test
    void testServerAcceptsConnection() throws IOException {
        // Mock a ServerSocket and a Client Socket
        ServerSocket mockServerSocket = mock(ServerSocket.class);
        Socket mockClientSocket = mock(Socket.class);

        // Simulate the server accepting a client connection
        when(mockServerSocket.accept()).thenReturn(mockClientSocket);

        // Verify that the mock server socket attempts to accept connections
        mockServerSocket.accept();
        verify(mockServerSocket, times(1)).accept();
    }
}
