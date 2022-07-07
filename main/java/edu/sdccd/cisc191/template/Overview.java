package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Overview extends Application {

    public static void main(String[] args) {
        // launch the application
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        Text text = new Text();

        // setting the text to be added
        text.setText("A java application to manage and track hold inventory for the " +
                "\nMesa Rim Mira Mesa setting team. This system allows routesetters " +
                "\nto keep inventory of, add, and remove holds for the Mira Mesa " +
                "\nclimbing facility. ");

        //setting the position and font of the text
        text.setX(50);
        text.setY(50);
        text.setFont(Font.font("Times New Roman", 15));

        // Button to return back to main
        Button returnButton = new Button("Return");
        returnButton.setStyle("-fx-background-color: #3f6ab6; -fx-text-fill: white");
        returnButton.setOnAction(e -> stage.close());

        //Creating a Group object
        Group group = new Group(text);

        // Vbox
        VBox vbox = new VBox(10, group, returnButton);
        vbox.setAlignment(Pos.CENTER);

        //Creating a scene object
        Scene scene = new Scene(vbox, 600, 300);

        //Adding scene to the stage
        stage.setScene(scene);

        // set the stage title.
        stage.setTitle("Mira Mesa Routesetting");

        //Displaying the contents of the stage
        stage.show();
    }
}
