package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;




/**Connor Bue
 * September 30, 2022,
 * Professor Andrew Huang
 * -
 * This program runs a game called Treasure Hunt
 * The goal is to find all the treasure in the 5x5 plot of spaces.
 * Once all treasure is found a victory message is displayed and
 * the player can start a new game.
 **/



public class Server extends Application{

    private InfoCenter infoCenter;
    private TileBoard tileBoard;

    @Override
    public void start(Stage primaryStage)
    {
        try {
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, UIDimensions.APP_WIDTH, UIDimensions.APP_HEIGHT);
            initLayout(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //initializes both portions of the gui
    private void initLayout(BorderPane root)
    {
        initInfoCenter(root);
        initTileBoard(root);

    }

    //initialize info center portion of panel
    private void initInfoCenter(BorderPane root)
    {
        infoCenter = new InfoCenter();
        infoCenter.setStartButtonOnAction(startNewGame());
        root.getChildren().add(infoCenter.getStackPane());
    }


    //first time the game starts, changes status message and hides start button
    private EventHandler<ActionEvent> startNewGame()
    {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                infoCenter.hideStartButton();
                infoCenter.updateTitle("Find the treasure!");
                tileBoard.startNewGame();
            }
        };
    }

    //initialize the game board portion of the gui
    private void initTileBoard(BorderPane root)
    {
        tileBoard = new TileBoard(infoCenter);
        root.getChildren().add(tileBoard.getStackPane());
    }

    //fallback launch
    public static void main(String[] args) {
            launch(args);
        }
    }
    //end class Server
