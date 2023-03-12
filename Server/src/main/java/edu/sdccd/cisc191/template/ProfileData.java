package edu.sdccd.cisc191.template;

//generics class to create profile object that holds profile name, total games played, and highest score achieved
//this object is used to save scores to the user that achieved them

public class ProfileData {

    //maybe the arraylist gets made in here?
    String profileName;
    int playerScore;

    public ProfileData(String pName, int hScore){
        this.profileName = pName;
        this.playerScore = hScore;
    }

    public void setUserName(String nameToAdd){
        profileName = nameToAdd;
    }

    public String getUserName(){
        return profileName;
    }

    public void setScore(int newHighScore) {
        playerScore = newHighScore;
    }

    public int getScore(){
        return playerScore;
    }
}
//end of class ProfileData
