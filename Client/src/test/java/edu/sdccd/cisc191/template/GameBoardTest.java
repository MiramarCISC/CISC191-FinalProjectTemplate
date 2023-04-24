package edu.sdccd.cisc191.template;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {
    private GameBoard gameBoard;

    @BeforeEach
    void setUp() {
        gameBoard = new GameBoard();
    }

    @Test
    void editBoardTest() {
        int index = 4;
        int value = 1;

        gameBoard.editBoard(index, value);

        // Check if the value at the specified index is updated
        assertEquals(value, gameBoard.board[index]);

        // Check if the 2D board is updated
        int row = index / 3;
        int col = index % 3;
        assertEquals(value, gameBoard.board2D[row][col]);
    }
}
