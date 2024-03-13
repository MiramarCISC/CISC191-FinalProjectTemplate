package edu.sdccd.cisc191.template;

import java.net.*;
import java.io.*;

/**
 * This program opens a connection to a computer specified
 * as the first command-line argument.  If no command-line
 * argument is given, it prompts the user for a computer
 * to connect to.  The connection is made to
 * the port specified by LISTENING_PORT.  The program reads one
 * line of text from the connection and then closes the
 * connection.  It displays the text that it read on
 * standard output.  This program is meant to be used with
 * the server program, DateServer, which sends the current
 * date and time on the computer where the server is running.
 */

public class Client {
    public static final String VEHICLE_URL = "http://webservice-1271959.us-east-1.elb.amazonaws.com/Server/vehicle.jsp";

    public VehicleResponse sendRequest(int year, String make, String model) throws Exception {
        StringBuilder response = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(new URL(VEHICLE_URL + "?year=" + year + "&make=" + make + "&model=" + model).openStream()));
        String line;
        // reading from the urlconnection using the bufferedreader
        while ((line = br.readLine()) != null)
        {
            response.append(line);
            response.append(System.lineSeparator());
        }
        br.close();
        return VehicleResponse.fromJSON(response.toString());
    }

    public static void main(String[] args) {
        Client client = new Client();
        try {
            System.out.println(client.sendRequest(2024, "Subaru", "Impreza"));
        } catch(Exception e) {
            System.err.println("An error has occurred: " + e.getMessage());
            System.exit(1);
        }
    }
} //end class Client

