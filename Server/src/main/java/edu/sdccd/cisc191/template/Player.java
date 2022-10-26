package edu.sdccd.cisc191.template;

public class Player implements Person{
    private int score;
    public int id;
    private String username;

    //id should be one or two. If player 1, set id to 1
    //if player 2, set id to 2
    public Player(String username, int id){
        this.username = username;
        this.id = id;
        score = 0;
    }

    public void increaseScore(){
        score++;
    }

    public int getScore(){
        return score;
    }

    public String getUsername(){
        return username;
    }

    @Override
    public String toString(){
        return username;
    }
}