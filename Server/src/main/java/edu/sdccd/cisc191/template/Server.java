package edu.sdccd.cisc191.template;

import java.net.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * This program is a server that takes connection requests on
 * the port specified by the constant LISTENING_PORT.  When a
 * connection is opened, the program sends the current time to
 * the connected socket.  The program will continue to receive
 * and process connections until it is killed (by a CONTROL-C,
 * for example).  Note that this server processes each connection
 * as it is received, rather than creating a separate thread
 * to process the connection.
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

    public static void menu(double[][] dataSet) {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        System.out.println("Welcome to Micro-Center \nPlease make a selection by entering a number between 1 and 3:\n\n1: Review data\n2: Add data\n3: Edit data");
        input = scanner.nextLine();

        switch (input) {
            case "1": // Output entire dataset
                outputData(dataSet);
                break;
            case "2": // Add 12 months of sales for a new store
                System.out.println("Add data");
                break;
            case "3": // Edit data for a specific store or specific month
                System.out.println("Edit data");
                break;
            default:
                menu(dataSet);
                break;
        }
    }

    public static void outputData(double[][] dataSet) {
        for(int row = 0; row< dataSet.length; row++) {
            for(int column = 0; column<dataSet[row].length; column++) {
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
                System.out.print(df.format(dataSet[row][column])+ " || ");
            }
            System.out.println();
        }
    }

    public static double[][] createDataSet() {

        double[][] dataSet = new double[5][12];

        for(int row = 0; row< dataSet.length; row++) {
            for(int column = 0; column<dataSet[row].length; column++) {
                dataSet[row][column] = (double)(Math.random() * 100000);
            }
        }

        return dataSet;
    }

    public static void main(String[] args) {
        double dataSet[][] = createDataSet();

        menu(dataSet);
//        Server server = new Server();
//        try {
//            server.start(4444);
//            server.stop();
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
    }
} //end class Server
