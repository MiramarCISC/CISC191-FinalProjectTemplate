package edu.sdccd.cisc191.template;

public class Board {
    private int[][] board;

    public Board(){
        board = new int[][]{{0,0,0},{0,0,0},{0,0,0}};
    }

    //passes in the player making the move, the row, and the column
    //if the column is empty, then the player id will be put
    //where the row, column is specified. If it is not available,
    //it will print invalid move
    public void move(Player player, int row, int column){
        if(checkMove(row, column)){
            board[row][column] = player.id;
            return;
        }
        System.out.println("Invalid Move");
    }

    //checks if there is a free spot to move
    //if it is not an empty spot to move, it will
    //return false. If it is empty, it will return true
    //used in the move method
    private boolean checkMove(int row, int column){
        if(board[row][column] != 0){
            return false;
        }
        return true;
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


