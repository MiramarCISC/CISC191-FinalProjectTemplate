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

        System.out.println("Welcome to Micro-Center \nPlease make a selection by entering a number between 1 and 4:\n\n1: Review data\n2: Add data\n3: Edit data\n4: Close");
        input = scanner.nextLine();

        // Switch that handles user input that leads to outputting the sales data,
        // adding a store with 12 months of data or editing a specific month of sales for a specific store
        // After each method is called we return to the menu method with the sales data
        switch (input) {
            case "1": // Output entire dataset
                outputData(dataSet);
                menu(dataSet);
                break;
            case "2": // Add 12 months of sales for a new store
                dataSet = addData(dataSet);
                menu(dataSet);
                break;
            case "3": // Edit data for a specific store or specific month
                System.out.println("Edit data");
                dataSet = editData(dataSet);
                menu(dataSet);
                break;
            case "4":
                break;
            default:
                menu(dataSet);
                break;
        }
    }

    // Displays all sales data for all stores currently loaded into the app
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

    // Adds a new store with 12 months of data
    public static double[][] addData(double[][] dataSet) {

        Scanner scanner = new Scanner(System.in);
        int prevRowCount = dataSet.length;

        // Creating a new array to extend the old sales data with an extra row to input the new store sales data
        double[][] newDataSet = new double[prevRowCount + 1][12];
        System.arraycopy(dataSet,0,newDataSet,0,prevRowCount);

        for(int col = 0; col < newDataSet[prevRowCount].length; col++) {
            System.out.println("Enter total sales for month "+ (col+1) + ": ");
            newDataSet[prevRowCount][col] = scanner.nextDouble();
        }

        return newDataSet;
    }

    // Edits an existing stores sales data for a specific month
    public static double[][] editData(double[][] dataSet) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Which store would you like to edit: 1 through " + dataSet.length);
        int storeInput = scanner.nextInt();

        // Check if user input is within the correct range for the store input
        if (1 <= storeInput || storeInput <= dataSet.length) {
            System.out.println("Which month would you like to edit: 1 through 12");
            int monthInput = scanner.nextInt();

            // Check if user input is within the correct range for the month input
            if (1<= monthInput || monthInput <= dataSet[storeInput-1].length) {
                System.out.println("Enter the new sales amount for month " + monthInput);
                double salesAmount = scanner.nextDouble();
                dataSet[storeInput-1][monthInput-1] = salesAmount;
            }
        } else editData(dataSet);

        return dataSet;
    }

    // Creates an array with 5 rows and 12 columns replicating a chain of stores with 12 months of sale data
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

        // Calls method that returns an array consisting of 5 rows and 12 columns to
        // replicate random sales data for 5 stores for 12 months for each store
        double dataSet[][] = createDataSet();

        // Calls method that sends the sales data to the menu method which handles user control
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
