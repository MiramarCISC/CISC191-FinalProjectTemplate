package edu.sdccd.cisc191.template;

public class Player {
    private int score;
    public int id;
    public String username;

    //id should be one or two. If player 1, set id to 1
    //if player 2, set id to 2
    public Player(String username, int id){
        this.username = username;
        this.id = id;
        score = 0;
    }

    private void increaseScore(){
        score++;
    }

    private int getScore(){
        return score;
    }
}
