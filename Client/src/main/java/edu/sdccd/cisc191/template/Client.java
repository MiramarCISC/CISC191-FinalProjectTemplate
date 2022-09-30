package edu.sdccd.cisc191.template;

import java.net.*;
import java.io.*;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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

public class Client extends Application implements IntegralsAndDerivativesOfTrigFunctions {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    private Button[] buttons = new Button[8];
    private Button submit, reset;

    private TextField[] textFields = new TextField[8];

    private Controller controller;
    private View view;



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
        launch();


        Client client = new Client();
        try {
            client.startConnection("127.0.0.1", 4444);
            System.out.println(client.sendRequest().toString());
            client.stopConnection();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void start(Stage primaryStage) throws Exception {

        // Title at the top of the window
        primaryStage.setTitle("Integrals and Derivatives of Trig Functions");

        // Controller object creation, so we can use its methods
        controller = new Controller();

        /*
            for loop here to match all
            8 questions and answers
         */

        for (int i = 0; i < buttons.length; i++) {
            textFields[i] = new TextField();
            buttons[i] = new Button(QUESTIONS[i]);
            controller.setTextFieldAndButton(textFields[i], QUESTIONS[i],
                    buttons[i], ANSWERS[i]);
        }

        // creating new GridPane that the layout is based on.
        // GridPanes are based on a 2D array
        GridPane root = new GridPane();
        root.setVgap(15);

        // Displays the label at the top with instructions
        Label integralsOfTrigFunctionsLabel = new Label("Click button to compare answers" +
                "\nTrig Integrals and Derivatives Flash Cards: ");
        integralsOfTrigFunctionsLabel.setPadding(new Insets(10));
        root.add(integralsOfTrigFunctionsLabel, 0, 0);

       /* Creation of new View object to use view's methods
          displayButtons and displayTextFields to show
          the Buttons and TextFields in each column */
        view = new View();
        view.displayButtons(root, buttons);
        view.displayTextFields(root, textFields);

        // Submit and reset Button creation
        submit = new Button("Submit");
        reset = new Button("Reset");

        // adds reset button to root
        controller.resetButton(reset, buttons, textFields);
        root.add(reset, 0, 9);

        // adds submit Button to root
        controller.countCorrectAnswers(submit);
        root.add(submit, 1, 9);

        // Setting the Scene and showing the Stage
        Scene scene = new Scene(root, 525, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
} //end class Client

