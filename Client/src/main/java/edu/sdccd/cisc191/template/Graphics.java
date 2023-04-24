package edu.sdccd.cisc191.template;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Arrays;
import java.util.LinkedList;
import java.io.*;
import edu.sdccd.cisc191.template.RequestMessage;
import edu.sdccd.cisc191.template.ResponseMessage;
import edu.sdccd.cisc191.template.NetworkClient;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.Collections;



public class Graphics extends GridPane implements EventHandler<ActionEvent>, ScoreTracker {

    //sets Height & width for the game and creates marks used for tic tac toe
    static final int Width = 540;
    static final int Height = 540;
    static final String markX = "X";
    static final String markO = "O";

    final Button[] tiles = new Button[9];
    boolean FirstPlayerActive;
    GameBoard gameBoard;

    // sets default player scores
    int player1Score;
    int player2Score;
    // Added linked list for player scores module 10
    LinkedList<Integer> player1Scores;
    LinkedList<Integer> player2Scores;
    static final String scoreFile = "player_scores.ser";
    private NetworkClient networkClient;

    // Added linked list for player color options module 10
    LinkedList<String> colorPreferences;
    ComboBox<String> colorPreferenceX;
    ComboBox<String> colorPreferenceO;

    Button removeColorBtn;
    TextField colorToRemove;


    public Graphics() {
        gameBoard = new GameBoard();
        loadScores(); // Added loading of serialized player scores, Module 7
        // Initialize the NetworkClient instance Module 8
        networkClient = new NetworkClient("localhost", 4444);
        if (player1Scores == null) {
            player1Scores = new LinkedList<>();
        }
        if (player2Scores == null) {
            player2Scores = new LinkedList<>();
        }
        this.setPrefSize(Width, Height);
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(10));
        colorPreferences = new LinkedList<>(Arrays.asList("BLACK", "BLUE", "CYAN", "DARKGRAY", "GREEN", "MAGENTA", "ORANGE", "PINK", "RED", "YELLOW"));
        sortColorPreferences();

        // Initialize the ComboBoxes for selecting color preferences
        ObservableList<String> colorOptions = FXCollections.observableArrayList(colorPreferences);
        colorPreferenceX = new ComboBox<>(colorOptions);
        colorPreferenceX.setValue("DARKGRAY");
        colorPreferenceO = new ComboBox<>(colorOptions);
        colorPreferenceO.setValue("CYAN");
        ComboBox<String> colorToRemoveCombo;

        colorToRemoveCombo = new ComboBox<>(colorOptions);
        colorToRemoveCombo.setPromptText("Select color to remove");

        // Initialize the button for removing colors
        removeColorBtn = new Button("Remove color");
        removeColorBtn.setOnAction(e -> {
            String color = colorToRemoveCombo.getValue();
            if (color != null) {
                removeColorPreference(color);
                // Update the colorOptions ObservableList
                ObservableList<String> updatedColorOptions = FXCollections.observableArrayList(colorPreferences);
                // Update the ComboBoxes
                colorPreferenceX.setItems(updatedColorOptions);
                colorPreferenceO.setItems(updatedColorOptions);
                colorToRemoveCombo.setValue(null);
            }
        });


        HBox colorBox = new HBox(30);
        colorBox.setAlignment(Pos.CENTER);
        colorBox.getChildren().addAll(colorPreferenceX, colorPreferenceO, colorToRemoveCombo, removeColorBtn);
        this.add(colorBox, 0, 4, 3, 1);
        player1ScoreLabel = new Label();
        player2ScoreLabel = new Label();
        updateScoreLabels();
        player1ScoreLabel.setFont(new Font("Arial", 18));
        player2ScoreLabel.setFont(new Font("Arial", 18));
        HBox scoreBox = new HBox(30);
        scoreBox.setAlignment(Pos.CENTER);
        scoreBox.getChildren().addAll(player1ScoreLabel, player2ScoreLabel);
        this.add(scoreBox, 0, 3, 3, 1);
        for(int i = 0; i < 9; i++){
            // creates the buttons, sets HxW, size, and font for the buttons
            tiles[i] = new Button();
            tiles[i].setFont(new Font("Arial", 80));
            tiles[i].setTextFill(Color.BLACK);
            tiles[i].setFocusTraversable(false);
            tiles[i].setPrefSize(180, 180);
            tiles[i].setMinSize(180, 180);
            tiles[i].setMaxSize(180, 180);
            tiles[i].setOnAction(this);
            this.add(tiles[i], i % 3, i / 3);
        }

        FirstPlayerActive = true;
    }

    // marks X & O
    public void handle(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == tiles[i]) {
                if (tiles[i].getText().isEmpty()) {
                    if (FirstPlayerActive) {
                        tiles[i].setTextFill(Color.valueOf(colorPreferenceX.getValue()));
                        tiles[i].setText(markX);
                        FirstPlayerActive = false;
                        gameBoard.editBoard(i, 1);
                    } else {
                        tiles[i].setTextFill(Color.valueOf(colorPreferenceO.getValue()));
                        tiles[i].setText(markO);
                        FirstPlayerActive = true;
                        gameBoard.editBoard(i, 2);
                    }

                    checkState();
                    updateScoreLabels();
                }
            }
        }
    }

    // checks to see which players has lined up 3 marks in a row. Declares winner
    protected void checkState() {
        if(checkMark(markX)){
            player1Wins();
            return;
        }

        if(checkMark(markO)){
            player2Wins();
            return;
        }

        if(checkDraw()){
            return;
        }
    }

    private void sortColorPreferences() {
        Collections.sort(colorPreferences);
    }

    // Add a method to add a new color preference
    public void addColorPreference(String color) {
        colorPreferences.add(color.toUpperCase());
        sortColorPreferences();
    }

    // Add a method to remove a color preference
    public void removeColorPreference(String color) {
        colorPreferences.remove(color.toUpperCase());
    }

    // checks to see if the game ended in a draw
    protected boolean checkDraw() {
        int i = 0;
        while(!tiles[i].getText().isEmpty()){
            if(i == tiles.length - 1){
                Arrays.stream(tiles).forEach(t -> t.setDisable(true));
                break;
            }
            i++;
        }

        return i == tiles.length - 1;
    }

    boolean checkMark(String mark) {
        boolean isDone = false;

        // Horizontal
        isDone = checkDirection(0, 1, 2, mark);
        if(!isDone)isDone = checkDirection(3, 4, 5, mark);
        if(!isDone)isDone = checkDirection(6, 7, 8, mark);

        //Vertical
        if(!isDone)isDone = checkDirection(0, 3, 6, mark);
        if(!isDone)isDone = checkDirection(1, 4, 7, mark);
        if(!isDone)isDone = checkDirection(2, 5, 8, mark);

        //Diagonal
        if(!isDone)isDone = checkDirection(0, 4, 8, mark);
        if(!isDone)isDone = checkDirection(2, 4, 6, mark);

        return isDone;
    }

    private void updateScoreLabels() {
        player1ScoreLabel.setText("Player 1 Score: " + player1Score);
        player2ScoreLabel.setText("Player 2 Score: " + player2Score);
    }


    // This code check the direction of the X's & O's placed to see if the player has 3 marks in a row
    protected boolean checkDirection(int a, int b, int c, String mark) {
        boolean isDone = false;
        if(tiles[a].getText().equals(mark) && tiles[b].getText().equals(mark) && tiles[c].getText().equals(mark)){
            Arrays.stream(new int[]{a, b, c}).forEach(i -> tiles[i].setTextFill(Color.RED));
            Arrays.stream(tiles).forEach(t -> t.setDisable(true));
            isDone = true;
        }
        return isDone;
    }

    // This code notifies the player of the winner and sets the background of the marks to green


    public void player1Wins() {
        // Send a request to update the score for player 1
        RequestMessage request = new RequestMessage("updatePlayer1Score");
        Task<ResponseMessage> task = networkClient.asyncSendRequest(request);
        task.setOnSucceeded(e -> {
            ResponseMessage response = task.getValue();
            player1Score++;
            updateScoreLabels();
            System.out.println("Player 1 wins!\n" + response.getData());
            saveScores(); // Save scores after updating
        });
        task.setOnFailed(e -> {
            System.out.println("Error updating Player 1 score");
        });
        new Thread(task).start();
    }

    public void player2Wins() {
        // Send a request to update the score for player 2
        RequestMessage request = new RequestMessage("updatePlayer2Score");
        Task<ResponseMessage> task = networkClient.asyncSendRequest(request);
        task.setOnSucceeded(e -> {
            ResponseMessage response = task.getValue();
            player2Score++;
            updateScoreLabels();
            System.out.println("Player 2 wins!\n" + response.getData());
            saveScores(); // Save scores after updating
        });
        task.setOnFailed(e -> {
            System.out.println("Error updating Player 2 score");
        });
        new Thread(task).start();
    }

    private void loadScores() {
        try (FileInputStream fis = new FileInputStream(scoreFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            player1Score = ois.readInt();
            player2Score = ois.readInt();
        } catch (FileNotFoundException e) {
            player1Score = 0;
            player2Score = 0;
        } catch (IOException e) {
            e.printStackTrace();
            player1Score = 0;
            player2Score = 0;
        }
    }

    private void saveScores() {
        try (FileOutputStream fos = new FileOutputStream(scoreFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeInt(player1Score);
            oos.writeInt(player2Score);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Label player1ScoreLabel;
    private Label player2ScoreLabel;
}