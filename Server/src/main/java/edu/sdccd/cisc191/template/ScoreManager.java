package edu.sdccd.cisc191.template;

import java.io.*;

public class ScoreManager {
    private int player1Score;
    private int player2Score;
    static final String scoreFile = "Common/player_scores.ser";

    public ScoreManager() {
        loadScores();
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public void incrementPlayer1Score() {
        player1Score++;
        saveScores();
    }

    public void incrementPlayer2Score() {
        player2Score++;
        saveScores();
    }

    private void loadScores() {
        try (FileInputStream fis = new FileInputStream(scoreFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            player1Score = ois.readInt();
            player2Score = ois.readInt();
        } catch (FileNotFoundException e) {
            player1Score = 0;
            player2Score = 0;
        } catch (IOException e) {
            e.printStackTrace();
            player1Score = 0;
            player2Score = 0;
        }
    }

    private void saveScores() {
        try (FileOutputStream fos = new FileOutputStream(scoreFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeInt(player1Score);
            oos.writeInt(player2Score);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
