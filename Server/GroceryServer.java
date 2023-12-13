package edu.sdccd.cisc191;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
public class GroceryServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            while (true) {
                Socket clientSocket = serverSocket.accept();
//deserialize contents of client input and read to request
                ObjectInputStream in = new
                        ObjectInputStream(clientSocket.getInputStream());
                GroceryRequest request = (GroceryRequest) in.readObject();
//write response to output stream to send to client
                GroceryResponse response = new GroceryResponse(request, 5.99, 100);

                ObjectOutputStream out = new
                        ObjectOutputStream(clientSocket.getOutputStream());
                out.writeObject(response);
                clientSocket.close();
            }
        } catch (Exception e) {
            System.out.println("Server has shut down. Error: " + e);
        }
    }
}
