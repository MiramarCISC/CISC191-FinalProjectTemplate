package edu.sdccd.cisc191.template;

import edu.sdccd.cisc191.template.RequestMessage;
import edu.sdccd.cisc191.template.ResponseMessage;
import javafx.concurrent.Task;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NetworkClient {
    private String serverAddress;
    private int serverPort;

    public NetworkClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public Task<ResponseMessage> asyncSendRequest(RequestMessage request) {
        Task<ResponseMessage> task = new Task<ResponseMessage>() {
            private final RequestMessage innerRequest = request;

            @Override
            protected ResponseMessage call() {
                return sendRequest(innerRequest);
            }
        };
        return task;
    }

    public ResponseMessage sendRequest(RequestMessage request) {
        try (Socket socket = new Socket(serverAddress, serverPort);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

            oos.writeObject(request);
            oos.flush();

            return (ResponseMessage) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
