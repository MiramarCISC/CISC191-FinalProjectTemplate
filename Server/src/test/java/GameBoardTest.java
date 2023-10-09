package edu.sdccd.cisc191;

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
    void testResetBoard() {
        gameBoard.resetBoard();

        // Check pawns
        for (int x = 0; x < 8; x++) {
            assertTrue(gameBoard.boardState[x][1] instanceof Pawn);
            assertTrue(gameBoard.boardState[x][6] instanceof Pawn);
        }

        // Check rooks
        assertTrue(gameBoard.boardState[0][0] instanceof Rook);
        assertTrue(gameBoard.boardState[7][0] instanceof Rook);
        assertTrue(gameBoard.boardState[0][7] instanceof Rook);
        assertTrue(gameBoard.boardState[7][7] instanceof Rook);

        // Check knights
        assertTrue(gameBoard.boardState[1][0] instanceof Knight);
        assertTrue(gameBoard.boardState[6][0] instanceof Knight);
        assertTrue(gameBoard.boardState[1][7] instanceof Knight);
        assertTrue(gameBoard.boardState[6][7] instanceof Knight);

        // Check bishops
        assertTrue(gameBoard.boardState[2][0] instanceof Bishop);
        assertTrue(gameBoard.boardState[5][0] instanceof Bishop);
        assertTrue(gameBoard.boardState[2][7] instanceof Bishop);
        assertTrue(gameBoard.boardState[5][7] instanceof Bishop);

        // Check queens
        assertTrue(gameBoard.boardState[3][0] instanceof Queen);
        assertTrue(gameBoard.boardState[3][7] instanceof Queen);

        // Check kings
        assertTrue(gameBoard.boardState[4][0] instanceof King);
        assertTrue(gameBoard.boardState[4][7] instanceof King);
    }
}
