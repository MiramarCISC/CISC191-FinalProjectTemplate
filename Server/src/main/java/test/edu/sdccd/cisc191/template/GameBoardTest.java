package edu.sdccd.cisc191.template;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameBoardTest {

    @Test
    public void testEditBoard() {
        GameBoard gameBoard = new GameBoard();
        gameBoard.editBoard(0, 1);
        Assertions.assertArrayEquals(new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0}, gameBoard.board);
        Assertions.assertArrayEquals(new int[][]{{1, 0, 0}, {0, 0, 0}, {0, 0, 0}}, gameBoard.board2D);
    }
}
