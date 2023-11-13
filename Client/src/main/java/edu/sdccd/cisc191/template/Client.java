package edu.sdccd.cisc191;

import java.io.*;
import java.net.Socket;
public class GroceryClient {
    public static void main(String[] args) throws IOException,
            ClassNotFoundException {
//create socket to communicate with server
        Socket clientSocket = new Socket("localhost", 9999);
        GroceryRequest request = new GroceryRequest("dairy", "milk");
// write info from request to output stream to send to server
        ObjectOutputStream out = new
                ObjectOutputStream(clientSocket.getOutputStream());
        out.writeObject(request);
//read input stream and assign it to response
        ObjectInputStream in = new
                ObjectInputStream(clientSocket.getInputStream());
        GroceryResponse response = (GroceryResponse) in.readObject();
        printResults(response);
        clientSocket.close();
    }
    //method returning response to console
    public static void printResults(GroceryResponse response) {
        System.out.println("Grocery Info:");
        System.out.println("Request: " + response.getRequest());
        System.out.println("Price: $" + response.getPrice());
        System.out.println("Calories: " + response.getCalories());
    }

}
