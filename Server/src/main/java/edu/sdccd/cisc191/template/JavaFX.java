package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
public class JavaFX extends Application {

    public void start(Stage stage) {

        //variables???



        Stage scene = new Scene(root)  //do we need to set dimensions of scene????
        Stage sceneSetup = new Scene(root) //what am i doing I'll learn this eventually
        stage.setScene(scene);


        Button setupButton = new Button("Run Setup");
        setUpButon.setonAction( evt -> stage.setScene(sceneSetup));
        // SetupButton.setOnAction( )
    }

}
