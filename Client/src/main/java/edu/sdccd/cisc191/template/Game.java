package edu.sdccd.cisc191.template;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game extends javafx.application.Application {
    @Override
    public void start(Stage stage) {
        //sets up new scene for the game and launches it
        Scene scene = new Scene(new Graphics());
        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
