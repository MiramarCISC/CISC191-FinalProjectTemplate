package edu.sdccd.cisc191.template;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// Test if gameboard is set up properly.
public class BoardInfoTest {

    @Test
    public void testBoard() {
        BoardInfo boardInfo = new BoardInfo();
        Assertions.assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0}, boardInfo.board);
    }

    @Test
    public void testBoard2D() {
        BoardInfo boardInfo = new BoardInfo();
        Assertions.assertArrayEquals(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, boardInfo.board2D);
    }
}