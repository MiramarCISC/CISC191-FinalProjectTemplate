package edu.sdccd.cisc191.template;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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
    private Button profileLogin;
    private TextField profileField;
    private Button startGameButton;
    private Button leaderboardButton;
    private GridPane profileGrid;

    //constructor method for the portion of the GUI that has the status message as well as the start new game button
    public InfoCenter()
    {
        pane = new StackPane();
        pane.setMinSize(UIDimensions.APP_WIDTH, UIDimensions.APP_HEIGHT);
        pane.setTranslateX(UIDimensions.APP_WIDTH/2);
        pane.setTranslateY(UIDimensions.INFO_CENTER_HEIGHT/2);

        title = new Label("Treasure Hunt");
        title.setMinSize(UIDimensions.APP_WIDTH/2+20, UIDimensions.APP_HEIGHT);
        title.setFont(Font.font(26));
        title.setAlignment(Pos.CENTER);
        title.setTranslateY(-20);
        pane.getChildren().add(title);

        GridPane profileGrid = new GridPane();
        profileGrid.setAlignment(Pos.CENTER);
        profileGrid.setTranslateY(60);

        profileField = new TextField();
        profileGrid.add(profileField, 0, 1);
        profileField.setMinSize(100, 30);

        profileLogin = new Button("Login");
        profileLogin.setMinSize(85, 30);
        profileGrid.add(profileLogin, 1, 1);

        pane.getChildren().add(profileGrid);


        leaderboardButton = new Button("Leaderboard");
        leaderboardButton.setMinSize(85, 30);
        leaderboardButton.setTranslateX(-240);
        leaderboardButton.setTranslateY(60);
        pane.getChildren().add(leaderboardButton);


        startGameButton = new Button("Start New Game");
        startGameButton.setMinSize(135, 30);
        startGameButton.setTranslateY(60);
        pane.getChildren().add(startGameButton);
        hideStartButton();

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

    //methods to show or hide the buttons
    public void showStartButton()
    {
        startGameButton.setVisible(true);
    }

    public void hideStartButton()
    {
        startGameButton.setVisible(false);
    }

    public void hideLeaderboardButton()
    {
        leaderboardButton.setVisible(false);
    }

    public void showLeaderboardButton()
    {
        leaderboardButton.setVisible(true);
    }

    //method to start a new game on button click once the first one is done

    public void additionalGameButton(EventHandler<ActionEvent> onAction)
    {
        startGameButton.setOnAction(onAction);
    }

    //method to start the game on mouse click
    public void setStartButtonOnAction(EventHandler<ActionEvent> onAction)
    {
        profileLogin.setOnAction(onAction);
    }

    public void openLeaderboardAction(EventHandler<ActionEvent> onAction)
    {
        leaderboardButton.setOnAction(onAction);
    }


    //method to hide the profile grid after a name is received
    public void hideProfileGrid()
    {
        profileLogin.setVisible(false);
        profileField.setVisible(false);
    }

    public String getProfileField() {
        return profileField.getText();
    }



    //end of class InfoCenter
}

