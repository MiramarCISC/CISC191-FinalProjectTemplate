package edu.sdccd.cisc191.template;

import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ViewStartScreen extends Application {
    private int screenWidth, screenHeight; //allows buttons to be scaled accordingly
    private BorderPane layout;
    private Stage stage;

    /**
     *
     * @param stage the primary stage for this application, onto which
     * the application scene can be set. The primary stage will be embedded in
     * the browser if the application was launched as an applet.
     * Applications may create other stages, if needed, but they will not be
     * primary stages and will not be embedded in the browser.
     * Initial screen that the user sees
     * Hooray for javafx
     */
    public void start(Stage stage) {
        //variables???
        this.stage = stage;
        //1080/1920 resolution
        screenWidth = 1080;
        screenHeight = 1920;

        //button to direct the user to set up
        OptionButton setupButton = new OptionButton("Run Setup", screenWidth / 5, screenHeight / 3);
        setupButton.setOnAction((ActionEvent e)-> {
            runSetup();
        });

        //title and credits to the authors
        Label title = new Label("Homework Tracker");
        Label credits = new Label("Credits: Logan, Simon, Theo, Willy");

        //organize title and setup button to be spaced accordingly, set it in center
        VBox buttons = new VBox(screenHeight/120, title, setupButton);
        buttons.setAlignment(Pos.CENTER);
        layout = new BorderPane(buttons);

        buttons = new VBox(credits);
        buttons.setAlignment(Pos.BOTTOM_RIGHT);
        layout.setBottom(buttons);

        Scene scene = new Scene(layout, screenWidth, screenHeight);
        stage.setTitle("Send Help Now");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
    public void runSetup() {
        TextField answers = new TextField();
        answers.setPrefSize(screenWidth/2, screenHeight/8);
    }
}
