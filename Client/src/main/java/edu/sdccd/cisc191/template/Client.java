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

public class Client extends Application implements IntegralsOfTrigFunctions, DerivativesOfTrigFunctions {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    private Button[] buttons = new Button[8];
    private Button submit, reset;

    private TextField[] textFields = new TextField[8];

    private Controller controller;
    private Model model;


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
        // Controller object creation, so we can use its methods
        controller = new Controller();

        // Title at the top of the window
        primaryStage.setTitle("Integrals and Derivatives of Trig Functions");

        /*
            The code is a bit repetitive here. I could've stored the
            promptText and Button text inside of arrays too and use some
            for loops. However, I like doing it this way because it
            makes it clear which Buttons and TextFields are which.
         */

        // Integral Buttons and TextFields

        // Integral of sin(x) Button and TextField
        textFields[0] = new TextField();
        buttons[0] = new Button("Integral of sin(x)");
        controller.setTextFieldAndButton(textFields[0], "∫sin(x) dx = ",
                buttons[0], INTEGRAL_OF_SINX);

        // Integral of cos(x) Button and TextField
        textFields[1] = new TextField();
        buttons[1] = new Button("Integral of cos(x)");
        controller.setTextFieldAndButton(textFields[1], "∫cos(x) dx = ",
                buttons[1], INTEGRAL_OF_COSX);

        // Integral of tan(x) Button and TextField
        textFields[2] = new TextField();
        buttons[2] = new Button("Integral of tan(x)");
        controller.setTextFieldAndButton(textFields[2], "∫tan(x) dx = ",
                buttons[2], INTEGRAL_OF_TANX);

        // Integral of sec(x) Button and TextField
        textFields[3] = new TextField();
        buttons[3] = new Button("Integral of sec(x)");
        controller.setTextFieldAndButton(textFields[3], "∫sec(x) dx = ",
                buttons[3], INTEGRAL_OF_SECX);

        // Derivative of sin(x) Button and TextField
        textFields[4] = new TextField();
        buttons[4] = new Button("Derivative of sin(x)");
        controller.setTextFieldAndButton(textFields[4], "(d/dx)(sin(x)) = ",
                buttons[4], DERIVATIVE_OF_SINX);

        // Derivative Buttons and TextFields

        // Derivative of cos(x) Button and TextField
        textFields[5] = new TextField();
        buttons[5] = new Button("Derivative of cos(x)");
        controller.setTextFieldAndButton(textFields[5], "(d/dx)(cos(x)) = ",
                buttons[5], DERIVATIVE_OF_COSX);

        // Derivative of tan(x) Button and TextField
        textFields[6] = new TextField();
        buttons[6] = new Button("Derivative of tan(x)");
        controller.setTextFieldAndButton(textFields[6], "(d/dx)(tan(x)) = ",
                buttons[6], DERIVATIVE_OF_TANX);

        // Derivative of sec(x) Button and TextField
        textFields[7] = new TextField();
        buttons[7] = new Button("Derivative of sec(x)");
        controller.setTextFieldAndButton(textFields[7], "(d/dx)(sec(x)) = ",
                buttons[7], DERIVATIVE_OF_SECX);


        // creating new GridPane that the layout is based on.
        // GridPanes are based on a 2D array
        GridPane root = new GridPane();
        root.setVgap(15);

        // Displays the label at the top with instructions
        Label integralsOfTrigFunctionsLabel = new Label("Click button to compare answers" +
                "\nTrig Integrals and Derivatives Flash Cards: ");
        integralsOfTrigFunctionsLabel.setPadding(new Insets(10));
        root.add(integralsOfTrigFunctionsLabel, 0, 0);

       /* Creation of new Model object to use model's methods
          displayButtons and displayTextFields to show
          the Buttons and TextFields in each column */
        model = new Model();
        model.displayButtons(root, buttons);
        model.displayTextFields(root, textFields);

        // Submit and reset buttons
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

