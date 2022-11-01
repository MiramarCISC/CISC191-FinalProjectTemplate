package edu.sdccd.cisc191.template;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

//class to sort data from txt doc and display a leaderboard in javafx
//arranges all data from the database of profiles into an arraylist to sort into a leaderboard
//also writes to the text file after each game is complete
public class Leaderboard {
    private static final String filepath = "C:\\Users\\connor\\Desktop\\ProfileDatas.txt";
    ArrayList<ProfileData> mergedSortedArray = new ArrayList<ProfileData>();
    ArrayList<ProfileData> profileList  = new ArrayList<ProfileData>();
    ProfileData profileData = new ProfileData("", 0);

    public ArrayList<ProfileData> readAllProfiles() throws FileNotFoundException {
        File profilesList = new File(filepath);

        Scanner fileInput = new Scanner(profilesList);

        while(fileInput.hasNextLine()){
            ProfileData newProfile = new ProfileData("", 0);
            String newUserString = "";
            newUserString = fileInput.nextLine();

            String[] parts;
            parts = newUserString.split("\\s+");

            newProfile.setUserName(parts[0]);
            newProfile.setScore(parseInt(parts[1]));

            profileList.add(newProfile);
        }
        return profileList;
    }

    //this class writes the result of the finished game to the text file
    public void writeNewGame(String userName, int gameScore) throws IOException {
        FileOutputStream file = new FileOutputStream(filepath, true);
        PrintWriter output = new PrintWriter(file);
        output.println(userName + " " + gameScore);
        output.close();
    }

    //method to sort the profile arraylist uses an implementaion of mergesort

    public ArrayList<ProfileData> sortProfile(ArrayList<ProfileData> unsortedProfile){
        MergeSort ms = new MergeSort();
        ArrayList<ProfileData> sortedProfile = ms.mergeSort(unsortedProfile);
        return sortedProfile;
    }

    //method to open a window that will display the sorted profiles from highest score to lowest
    public void displayLeaderboard(ArrayList<ProfileData> listSorted){
        Stage leaderboardWindow = new Stage();
        VBox comp = new VBox();
        for(int i = 0; i<listSorted.size(); i++) {


            Label nextScore = new Label((i+1) + ". " + listSorted.get(i).getUserName() +
                    "       " + listSorted.get(i).getScore());

            comp.getChildren().add(nextScore);
        }
        Scene leaderboardStage = new Scene(comp, 400, 500);
        leaderboardWindow.setScene(leaderboardStage);
        leaderboardWindow.show();
    }
}
//end of class Leaderboard
