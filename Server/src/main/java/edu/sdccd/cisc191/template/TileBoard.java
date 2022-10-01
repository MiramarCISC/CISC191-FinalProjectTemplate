package edu.sdccd.cisc191.template;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.Objects;

public class TileBoard {

    private final StackPane pane;
    private final InfoCenter infoCenter;
    private final Tile[][] playerBoard = new Tile[5][5];
    private final String[][] solutionBoard = new String[5][5];


    private boolean isEndOfGame = false;

    //constructor of the game board portion
    public TileBoard(InfoCenter infoCenter) {
        this.infoCenter = infoCenter;
        pane = new StackPane();
        pane.setMinSize(UIDimensions.APP_WIDTH, UIDimensions.APP_HEIGHT);
        pane.setTranslateX(UIDimensions.APP_WIDTH / 2);
        pane.setTranslateY((UIDimensions.TILE_BOARD_HEIGHT / 2) + UIDimensions.INFO_CENTER_HEIGHT);

        addAllTiles();
    }

    //initialize the playerBoard and the solutionBoard 2-d arrays
    private void addAllTiles() {

        //fill the playerBoard with tiles
        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 5; column++) {
                Tile tile = new Tile();
                tile.getStackPane().setTranslateX((column * 100) - 200);
                tile.getStackPane().setTranslateY((row * 100));
                pane.getChildren().add(tile.getStackPane());
                playerBoard[row][column] = tile;
            }
        }

        //fill the solution board with 5 treasure spots
        for (int i = 0; i < 5; i++) {
            solutionBoard[(int) (Math.random() * 5)][(int) (Math.random() * 5)] = "t";
        }

        //fill the solution board with the rest as empty
        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 5; column++) {
                if (Objects.equals(solutionBoard[row][column], "t")) return;
                else {
                    solutionBoard[row][column] = "e";
                }
            }
        }
    }

    //method to reset both the board to their original states to be ready for a new game
    public void startNewGame() {
        isEndOfGame = false;

        //set the players board back to blank
        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 5; column++) {
                playerBoard[row][column].setValue("");
            }
        }


        //reset the solution board and create a new solution
        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 5; column++) {
                solutionBoard[row][column] = ("");
            }
        }

        //add the 5 treasure spots
        for (int i = 0; i < 5; i++) {
            solutionBoard[(int) (Math.random() * 5)][(int) (Math.random() * 5)] = "t";
        }

        //fill the solution board with the rest as empty
        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 5; column++) {
                if (Objects.equals(solutionBoard[row][column], "t")) return;
                else {
                    solutionBoard[row][column] = "e";
                }
            }
        }

    }

    //getter method
    public StackPane getStackPane() {
        return pane;
    }

    //method to return if the guess tile had treasure below it or not
    public String checkInput(int x, int y) {
        if (Objects.equals(solutionBoard[x][y], "t")) {
            return "T";
        } else {
            return "E";
        }
    }

    //method to check to see if the player has found all the treasure
    //if all the treasure is found, call endGame() to display game won
    public void checkForWinner() {
        //find total treasure amount
        int totalTreasure = 0;

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (Objects.equals(solutionBoard[row][col], "t")) {
                    totalTreasure++;
                }

            }
        }
        int foundTreasure = 0;
        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 5; column++) {
                if (Objects.equals(playerBoard[row][column].getValue(), "T")) {
                    foundTreasure++;
                    ;
                }
            }
        }
        if (totalTreasure == foundTreasure) {
            endGame();
        }
    }

    //method to end the game and display the victory message
    //shows the start new game button to give the player the option to start a new game
    private void endGame() {
        isEndOfGame = true;
        infoCenter.updateTitle("You have found all the treasure!");
        infoCenter.showStartButton();
    }

    //constructor class to give every tile a label which will display if the guess was correct.
    private class Tile {
        private final StackPane pane;
        private final Label label;

        public Tile() {
            pane = new StackPane();
            pane.setMinSize(50, 50);

            Rectangle border = new Rectangle();
            border.setHeight(50);
            border.setWidth(50);
            border.setFill(Color.TRANSPARENT);
            border.setStroke(Color.BLACK);
            pane.getChildren().add(border);

            label = new Label("");
            label.setAlignment(Pos.CENTER);
            label.setFont(Font.font(32));
            pane.getChildren().add(label);


            //mouse event listener lambda method to pass the guess to checkInput() and make sure it is a valid guess
            pane.setOnMouseClicked(event -> {
                if (label.getText().isEmpty() && !isEndOfGame) {
                    //find if treasure is found and update box
                    label.setText(checkInput((int) (label.getParent().getBoundsInParent().getMinX() + 225) / 100,
                            (int) (label.getParent().getBoundsInParent().getMinY() + 25) / 100));
                    checkForWinner();
                }
            });
        }

        //getter method for StackPane
        public StackPane getStackPane() {
            return pane;
        }

        //get the value of the tile which is undiscovered, empty or treasure
        public String getValue() {
            return label.getText();
        }

        //set the value of the tile
        public void setValue(String value) {
            label.setText(value);
        }
    }

    //end of class TileBoard
}
