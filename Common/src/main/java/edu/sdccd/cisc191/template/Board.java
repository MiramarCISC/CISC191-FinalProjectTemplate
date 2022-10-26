package edu.sdccd.cisc191.template;

public class Board {
    private int[][] board;
    private Player player1;
    private Player player2;

    public Player playerMove;

    public Board(Player player1, Player player2){
        board = new int[][]{{0,0,0},{0,0,0},{0,0,0}};
        this.player1 = player1;
        this.player2 = player2;
        if((int)(Math.random() * 2 + 1) == 1){
            playerMove = player1;
        }
        else{
            playerMove = player2;
        }
    }

    public Player getPlayer1(){
        return player1;
    }

    public Player getPlayer2(){
        return player2;
    }

    public void nextPlayer(){
        if(playerMove == player1){
            playerMove = player2;
        }
        else {
            playerMove = player1;
        }
    }

    //passes in the player making the move, the row, and the column
    //then the player id will be put where the row, column is specified.
    public void move(Player player, int row, int column){
        board[row][column] = player.id;
    }

    //checks if there is a free spot to move
    //if it is not an empty spot to move, it will
    //return false. If it is empty, it will return true
    //used in the move method

    //deprecated method, no need to check if move is valid since buttons grey out
    //when they are clicked
    private boolean checkMove(int row, int column){
        if(board[row][column] != 0){
            return false;
        }
        return true;
    }

    //resets the 3x3 array to 0
    public void resetBoard(){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                board[i][j] = 0;
            }
        }
    }

    //Checks if the player passed in is a winner
    //checks all possible win conditions.
    public boolean winner(Player player){
        //top row
        if(board[0][0] == player.id && board[0][1] == player.id && board[0][2] == player.id){
            return true;
        }
        //middle row
        else if(board[1][0] == player.id && board[1][1] == player.id && board[1][2] == player.id){
            return true;
        }
        //bottom row
        else if(board[2][0] == player.id && board[2][1] == player.id && board[2][2] == player.id){
            return true;
        }
        //left column
        else if(board[0][0] == player.id && board[1][0] == player.id && board[2][0] == player.id){
            return true;
        }
        //middle column
        else if(board[0][1] == player.id && board[1][1] == player.id && board[2][1] == player.id){
            return true;
        }
        //right column
        else if(board[0][2] == player.id && board[1][2] == player.id && board[2][2] == player.id){
            return true;
        }
        //negative slope diagonal
        else if(board[0][0] == player.id && board[1][1] == player.id && board[2][2] == player.id){
            return true;
        }
        //positive slope diagonal
        else if(board[2][0] == player.id && board[1][1] == player.id && board[0][2] == player.id){
            return true;
        }
        return false;
    }
}


