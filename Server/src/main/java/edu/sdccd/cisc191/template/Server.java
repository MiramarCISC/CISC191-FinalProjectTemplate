package edu.sdccd.cisc191.template;

import java.net.*;
import java.io.*;

/*
Hosts server
 */
public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;

    // Start server socket and accept client connection
    public void start(int port, String[] args) throws Exception {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
    }

    // Close server and close client connection
    public void stop() throws IOException {
        clientSocket.close();
        serverSocket.close();
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.start(4444, args);
            server.stop();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
