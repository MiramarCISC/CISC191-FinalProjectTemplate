package edu.sdccd.cisc191.template;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Arrays;
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
    int player1Score = 0;
    int player2Score = 0;

    public Graphics() {
        gameBoard = new GameBoard();
        this.setPrefSize(Width, Height);
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(10));

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
        for(int i = 0; i < 9; i++){
            if(e.getSource() == tiles[i]){
                if(tiles[i].getText().isEmpty()){
                    if(FirstPlayerActive){
                        tiles[i].setTextFill(Color.DARKGRAY);
                        tiles[i].setText(markX);
                        FirstPlayerActive = false;
                        gameBoard.editBoard(i, 1);
                    } else {
                        tiles[i].setTextFill(Color.CYAN);
                        tiles[i].setText(markO);
                        FirstPlayerActive = true;
                        gameBoard.editBoard(i, 2);
                    }

                    checkState();
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


    public void player1Wins(){
        player1Score++;
        System.out.println("Player 1 wins!\nPlayer 1 score: " + player1Score + "\nPlayer 2 score: " + player2Score);
    }

    public void player2Wins(){
        player2Score++;
        System.out.print("Player 2 wins!\nPlayer 1 score: " + player1Score + "\nPlayer 2 score: " + player2Score);
    }

}
