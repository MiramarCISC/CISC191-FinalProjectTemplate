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
import java.util.ArrayList;
import java.awt.Checkbox;
import javax.swing.*;

public class ViewStartScreen extends Application {
    private int screenWidth, screenHeight; //allows buttons to be scaled accordingly
    private BorderPane layout;
    private Stage stage;
    private Scene startScene, sceneClassName;
    private String temp;
    private ArrayList<Subject> subjectArrayList = new ArrayList<>();
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
    public void start(Stage stage) throws Exception{
        //variables???
        this.stage = stage;
        // 720x1200 resolution
        screenWidth = 720;
        screenHeight = 1200;

        //button to direct the user to set up
        OptionButton setupButton = new OptionButton("Run Setup", screenWidth / 5, screenHeight / 3);
        setupButton.setOnAction((ActionEvent e)-> {
            runSetup1();
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

        startScene = new Scene(layout, screenWidth, screenHeight);
        stage.setTitle("Send Help Now");
        stage.setScene(startScene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }

    /**
     * Asks the user for how many classes they have
     * updates global variable subjectArrayList according to the user
     * directs to runSetup2()
      */
    public void runSetup1() {

        TextField answers = new TextField("100");
        answers.setPrefSize(screenWidth/2, screenHeight/8);

        Label promptClasses = new Label("How many classes with homework do you have?");

        OptionButton confirm = new OptionButton("Confirm", screenWidth/4, screenHeight/16);
        confirm.setOnAction((ActionEvent e)-> {
            //TODO make sure user only inputs integers
            subjectArrayList = new ArrayList<>(Integer.parseInt(answers.getText()));
            runSetup2();
        });
    }
    public void runSetup2() {
        int i = 0;
        for (i = 0; i < subjectArrayList.size(), i++) {
            Label promptName = new Label("Enter the name of period " + i);
            TextField name = new TextField();
            name.setPrefSize(screenWidth/2), screenHeight/8);

            Checkbox weighted = new Checkbox("Is the class weighted?", false);

            VBox layout2 = new VBox(20.0, promptName, name, weighted);

            sceneClassName = (layout2, screenWidth, screenHeight);
            stage.setTitle("Set your Classes");
            stage.setScene(sceneClassName);
        }
    }
}
