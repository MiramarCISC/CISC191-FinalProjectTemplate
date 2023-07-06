package edu.sdccd.cisc191.template;

import edu.sdccd.cisc191.template.RequestMessage;
import edu.sdccd.cisc191.template.ResponseMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private ScoreManager scoreManager;

    public Server() {
        scoreManager = new ScoreManager();
    }

    public void start(int port) throws Exception {
        serverSocket = new ServerSocket(port);

        while (true) {
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                RequestMessage request = RequestMessage.fromJson(inputLine);
                ResponseMessage response = null;

                if ("updatePlayer1Score".equals(request.getRequestType())) {
                    scoreManager.incrementPlayer1Score();
                    response = new ResponseMessage("player1ScoreUpdated", scoreManager.getPlayer1Score());
                } else if ("updatePlayer2Score".equals(request.getRequestType())) {
                    scoreManager.incrementPlayer2Score();
                    response = new ResponseMessage("player2ScoreUpdated", scoreManager.getPlayer2Score());
                }

                out.println(ResponseMessage.toJson(response));
            }
        }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.start(4444);
            server.stop();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
