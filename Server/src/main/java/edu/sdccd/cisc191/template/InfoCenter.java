package edu.sdccd.cisc191.template;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

/**
 * This class gives info on how to draw the
 * top section of the GUI with the title of
 * the game as well as a start new game
 * button when it is needed.
 */

public class InfoCenter
{
    private StackPane pane;
    private Label title;
    private Button startGameButton;

    //constructor method for the portion of the GUI that has the status message as well as the start new game button
    public InfoCenter()
    {
        pane = new StackPane();
        pane.setMinSize(UIDimensions.APP_WIDTH, UIDimensions.APP_HEIGHT);
        pane.setTranslateX(UIDimensions.APP_WIDTH/2);
        pane.setTranslateY(UIDimensions.INFO_CENTER_HEIGHT/2);

        title = new Label("Treasure Hunt");
        title.setMinSize(UIDimensions.APP_WIDTH, UIDimensions.APP_HEIGHT);
        title.setFont(Font.font(28));
        title.setAlignment(Pos.CENTER);
        title.setTranslateY(-20);
        pane.getChildren().add(title);

        startGameButton = new Button("Start New Game");
        startGameButton.setMinSize(135, 30);
        startGameButton.setTranslateY(20);
        pane.getChildren().add(startGameButton);
    }

    //getter method for StackPane
    public StackPane getStackPane()
    {
        return pane;
    }

    //method to update the status message
    public void updateTitle(String title)
    {
        this.title.setText(title);
    }

    //method to show start button after a game is complete
    public void showStartButton()
    {
        startGameButton.setVisible(true);
    }

    //method to hide the start button while the game is in progress
    public void hideStartButton()
    {
        startGameButton.setVisible(false);
    }

    //method to start the game on mouse click
    public void setStartButtonOnAction(EventHandler<ActionEvent> onAction)
    {
        startGameButton.setOnAction(onAction);
    }

    //end of class InfoCenter
}
