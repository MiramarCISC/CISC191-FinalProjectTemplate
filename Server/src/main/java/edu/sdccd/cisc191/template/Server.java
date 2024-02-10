package edu.sdccd.cisc191.template;

import java.net.*;
import java.io.*;
import java.util.*;

/**
 * This program is a server that takes connection requests on
 * the port specified by the constant LISTENING_PORT.  When a
 * connection is opened, the program sends the current time to
 * the connected socket.  The program will continue to receive
 * and process connections until it is killed (by a CONTROL-C,
 * for example).  Note that this server processes each connection
 * as it is received, rather than creating a separate thread
 * to process the connection
 */
public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) throws Exception {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            CustomerRequest request = CustomerRequest.fromJSON(inputLine);
            CustomerResponse response = new CustomerResponse(request.getId(), "Jane", "Doe");
            out.println(CustomerResponse.toJSON(response));
        }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        Player adventurer = new Player();
        RandomEvent randomEvent = new RandomEvent();

        //player testing
        String[] classes = {"Warrior", "Tank", "Assassin"};
        System.out.println("To begin your journey, please select your class. " + Arrays.toString(classes));

        switch(keyboard.nextLine().toUpperCase()) {
            case "WARRIOR":
                adventurer = new Player("Warrior", 100, 90, 60);
                System.out.println("Welcome " + adventurer.getPlayerName() + ". " + adventurer);
                System.out.println("You drank a strength potion! Increasing your strength...");
                adventurer.setStrength(115);
                System.out.println("Done! " + adventurer);
                break;
            case "TANK":
                adventurer = new Player("Tank", 135, 30, 85);
                System.out.println("Welcome " + adventurer.getPlayerName() + ". " + adventurer);
                System.out.println("You drank a shield potion! Increasing current health and defense...");
                adventurer.setHealth(160);
                adventurer.setDefense(110);
                System.out.println("Done! " + adventurer);
                break;
            case "ASSASSIN":
                adventurer = new Player("Assassin", 80, 125,45);
                System.out.println("Welcome " + adventurer.getPlayerName() + ". " + adventurer);
                System.out.println("You drank a strength potion! Increasing strength...");
                adventurer.setStrength(150);
                System.out.println("Done! " + adventurer);
                break;
            default:
                System.out.println("Sorry, as we did not recognize your class, we will be using the default one.");
                System.out.println("Welcome " + adventurer.getPlayerName() + ". " + adventurer);
                break;
        }

        System.out.println("\nIs there a name you go by?\n");
        adventurer.setPlayerName(keyboard.nextLine());

        System.out.println("I see. I wish you the best of luck on your journey, " + adventurer.getPlayerName() + ".\n");

        //Random Event
        randomEvent.askRandomEvent();

        Server server = new Server();
        try {
            server.start(4444);
            server.stop();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
} //end class Server
