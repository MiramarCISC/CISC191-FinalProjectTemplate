package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class LoginUI extends Application {
    public void start(Stage primaryStage) {
        // Create a new GridPane layout
        GridPane grid = new GridPane();

        // Add a label and text field for the username
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);

        // Add a label and password field for the password
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);

        // Add a login button
        Button loginButton = new Button("Login");
        grid.add(loginButton, 1, 2);

        // Create a new scene and add the grid to it
        Scene scene = new Scene(grid);

        // Set the title of the window and add the scene to it
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);

        // Show the window
        primaryStage.show();
    }
}
