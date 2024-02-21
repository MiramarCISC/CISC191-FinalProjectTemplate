package edu.sdccd.cisc191;

import edu.sdccd.cisc191.ciphers.Caesar;
import edu.sdccd.cisc191.ciphers.Hill;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.net.*;
import java.io.*;
import java.util.Scanner;


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

public class Client extends Application{
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    ComboBox<String> combobox;
    private TextArea textArea;
    private static Stage window;
    private static Scene scene;
    private static String outputText;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public CustomerResponse sendRequest() throws Exception {
        out.println(CustomerRequest.toJSON(new CustomerRequest(1)));
        return CustomerResponse.fromJSON(in.readLine());
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.startConnection("127.0.0.1", 4444);
            System.out.println(client.sendRequest().toString());
            System.out.println(client.sendRequest().getId());
            client.stopConnection();
        } catch(Exception e) {
            e.printStackTrace();
        }
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Decode");

        //list of Ciphers
        combobox = new ComboBox<>();
        combobox.getItems().addAll(
                "Hill Cipher",
                "Caesar Cipher"
        );
        //listen for selection changes
        combobox.setOnAction(e -> System.out.println(combobox.getValue()));

        //labels
        Label label = new Label("Enter key:");

        //text input
        TextField input = new TextField();

        //Text Area
        textArea = new TextArea();

        //Decode/encode button
        Button button = new Button("Encode");
        button.setOnAction(e -> Client.encode(textArea.getText(), input.getText(), combobox.getValue()));
        Button encode = new Button("Decode");
        encode.setOnAction(e -> Client.decode(textArea.getText(), input.getText(), combobox.getValue()));

        //Import File Button
        Button files = new Button("Select File");
        files.setOnAction(e -> {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT files", "*.TXT"),
                    new FileChooser.ExtensionFilter("txt files", ".txt"));
            File selectedFile = fc.showOpenDialog(null);
            if (selectedFile != null) {
                try{
                    Scanner scanner = new Scanner(selectedFile);
                    while(scanner.hasNextLine()){
                        textArea.appendText(scanner.nextLine());
                    }
                }catch (FileNotFoundException f){
                    f.getMessage();
                }
            } else {
                System.out.println("File is not valid");
            }
        });


        //layout
        HBox layout2 = new HBox(10);
        layout2.getChildren().addAll(input, combobox);
        layout2.setAlignment(Pos.CENTER);
        HBox layout3 = new HBox(10);
        layout3.setAlignment(Pos.CENTER);
        layout3.getChildren().addAll(button, encode);
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label, layout2, layout3, textArea, files);


        scene = new Scene(layout, 800,600);
        window.setScene(scene);
        window.show();
    }

    public static void encode(String plainText, String key, String cipherType) {
        switch(cipherType) {
            case "Hill Cipher":
                outputText = Hill.encode(plainText, key);
                createSecondWindow();
                break;
            case "Caesar Cipher":
                outputText = Caesar.encode(plainText, key);
                createSecondWindow();
                break;
        }
    }

    public static void decode(String plainText, String key, String cipherType){
        switch(cipherType) {
            case "Hill Cipher":
                outputText = Hill.decode(plainText, key);
                createSecondWindow();
                break;
            case "Caesar Cipher":
                outputText = Caesar.decode(plainText,key);
                createSecondWindow();
                break;
        }
    }

    public static void createSecondWindow() {
        Label answer = new Label("Result:");
        VBox layout4 = new VBox(10);
        TextField output = new TextField(outputText);
        Button back = new Button("Back");
        back.setOnAction(e -> window.setScene(scene));
        layout4.setAlignment(Pos.CENTER);
        layout4.getChildren().addAll(answer, output, back);
        Scene scene2 = new Scene(layout4, 500, 300);
        window.setScene(scene2);
    }
} //end class Client