package edu.sdccd.cisc191;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ChessGUI extends Application {

    private GameBoard gameBoard = new GameBoard();
    private Button[][] boardButtons = new Button[8][8];

    private Piece selectedPiece;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Button button = new Button();
                button.setMinWidth(60);
                button.setMinHeight(60);
                boardButtons[i][j] = button;
                root.add(button, i, j);

                int finalI = i;
                int finalJ = j;
                button.setOnAction(e -> handleButtonClick(finalI, finalJ));
            }
        }

        updateBoard();

        Scene scene = new Scene(root, 480, 480);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Chess Game");
        primaryStage.show();
    }

    private void handleButtonClick(int x, int y) {
        // Handle the button click logic here
        // For example, select a piece and then move it
        if (selectedPiece == null) {
            // First click: Select a piece
            selectedPiece = gameBoard.boardState[x][y];
        } else {
            // Second click: Move the piece
            if (gameBoard.isMoveValid(selectedPiece, x, y)) {
                gameBoard.movePiece(selectedPiece, x, y);
                selectedPiece = null; // Deselect the piece
                updateBoard(); // Update the GUI
            }
        }
    }

    private void updateBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = gameBoard.boardState[i][j];
                if (piece != null) {
                    boardButtons[i][j].setText(piece.toString());
                } else {
                    boardButtons[i][j].setText("");
                }
            }
        }
    }
}
