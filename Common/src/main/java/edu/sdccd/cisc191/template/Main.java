package edu.sdccd.cisc191.template;

public class Main {
    public static void main(String[] args){
        Player player1 = new Player("Player1", 1);
        Player player2 = new Player("Player2", 2);
        Board board = new Board(player1, player2);
        UI ui = new UI(board);
    }
}
