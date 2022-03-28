package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * This program is a JavaFX based Wordle solver. It works by pulling a word bank of over 5000 words
 * and filtering their exclusions and inclusions based off of user input in 7 different TextField objects
 *
 */
public class Wordle extends Application {
    /**
     * Our respective 7 TextFields declared publicly so that their input can be read by the
     * action button class later in the program.
     */
    public static Insets BOX_PADDING= new Insets(20);
    public static TextField excludedLettersField;
    public static TextField containedLettersFieldOne;
    public static TextField containedLettersFieldTwo;
    public static TextField containedLettersFieldThree;
    public static TextField containedLettersFieldFour;
    public static TextField containedLettersFieldFive;
    public static TextField positionOneField;
    public static TextField positionTwoField;
    public static TextField positionThreeField;
    public static TextField positionFourField;
    public static TextField positionFiveField;

    /**
     * Each word in the .txt file will be instantiated with these fields. A string to hold the
     * 5 letter word and 7 booleans to be altered to filter excluded and included words
     */
    private String testString;
    private boolean excludedLetters = false;

    private boolean containedLetters = false;
    private boolean containedLettersOne = false;
    private boolean containedLettersTwo = false;
    private boolean containedLettersThree = false;
    private boolean containedLettersFour = false;
    private boolean containedLettersFive = false;


    private boolean positionalLetters = false;
    private boolean positionalLettersOne = false;
    private boolean positionalLettersTwo = false;
    private boolean positionalLettersThree = false;
    private boolean positionalLettersFour = false;
    private boolean positionalLettersFive = false;

    /**
     Setters and getters for each fields.
     */
    public void setExcludedLetters(boolean b) {
        this.excludedLetters = b;
    }

    public boolean getExcludedLetters() {
        return excludedLetters;
    }

    public void setContainedLetters(boolean b) {
        this.containedLetters = b;
    }

    public boolean getContainedLetters() {
        return containedLetters;
    }

    public void setContainedLettersOne(boolean b) {
        this.containedLettersOne = b;
    }

    public boolean getContainedLettersOne() {
        return containedLettersOne;
    }

    public void setContainedLettersTwo(boolean b) {
        this.containedLettersTwo = b;
    }

    public boolean getContainedLettersTwo() {
        return containedLettersTwo;
    }

    public void setContainedLettersThree(boolean b) {
        this.containedLettersThree = b;
    }

    public boolean getContainedLettersThree() {
        return containedLettersThree;
    }

    public void setContainedLettersFour(boolean b) {
        this.containedLettersFour = b;
    }

    public boolean getContainedLettersFour() {
        return containedLettersFour;
    }

    public void setContainedLettersFive(boolean b) {
        this.containedLettersFive = b;
    }

    public boolean getContainedLettersFive() {
        return containedLettersFive;
    }

    public void setPositionalLetters(boolean b) {
        this.positionalLetters = b;
    }

    public boolean getPositionalLetter() {
        return positionalLetters;
    }

    public void setPositionalLettersOne(boolean b) {
        this.positionalLettersOne = b;
    }

    public boolean getPositionalLettersOne() {
        return positionalLettersOne;
    }

    public void setPositionalLettersTwo(boolean b) {
        this.positionalLettersTwo = b;
    }

    public boolean getPositionalLettersTwo() {
        return positionalLettersTwo;
    }

    public void setPositionalLettersThree(boolean b) {
        this.positionalLettersThree = b;
    }

    public boolean getPositionalLettersThree() {
        return positionalLettersThree;
    }

    public void setPositionalLettersFour(boolean b) {
        this.positionalLettersFour = b;
    }

    public boolean getPositionalLettersFour() {
        return positionalLettersFour;
    }

    public void setPositionalLettersFive(boolean b) {
        this.positionalLettersFive = b;
    }

    public boolean getPositionalLettersFive() {
        return positionalLettersFive;
    }

    public String getTestString() {
        return testString;
    }


    public Wordle() {
        // No arg constructor
    }

    /**
     * Constructor to instantiate each of the ~5000 Wordle objects with its respective word
     * immediately and in only one line of code.
     *
     * @param s The wordle word read from the .txt file.
     */
    public Wordle(String s) {
        this.testString = s;
    }

    /* TODO: Currently doesn't function as intended. It needs to clear the text on the console so applicable
    words input to input are more easily readable.
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();

    }

    /**
     * @return Creates an ArrayList of Wordle objects to be extracted later by the filtering and
     * ActionEvent methods.
     */
    public static ArrayList<Wordle> initialize() {

        // The .txt pathfile will have to be changed for every user. The .txt is included in the repo.
        File file = new File("Server\\src\\main\\resources\\unlimited_words.txt");
        try {
            Scanner sc = new Scanner(file);
            // Create the array list
            ArrayList wordList = new ArrayList<>();

            // Read file and write a new object to the list for every word
            // and initialize the TestString attribute with the nextLine(); method
            while (sc.hasNext()) {
                wordList.add(new Wordle(sc.nextLine()));
            }
            sc.close();
            // Return the word list into main
            return wordList;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param wordList The unfiltered ArrayList of Wordle objects.
     * @param exclude The input from the excluded  text field.
     * @return Returns wordList with the excluded letters.
     */
    public static ArrayList<Wordle> excludeLetters(ArrayList<Wordle> wordList, String exclude)
    // Very simply see if words are contain any part of the passed in String then its value is set
    // to true and not printed later.
    {
        for (int k = 0; k < wordList.size(); k++) { // Original
            for(int j = 0; j < exclude.length(); j++) { // Nested
                if (wordList.get(k).getTestString().contains(Character.toString(exclude.charAt(j))))
                    wordList.get(k).setExcludedLetters(true);
            } // Nested
        } // Original
        return wordList;
    }

    /**
     *
     * @param wordList The filtered ArrayList of Wordle objects that previously passed through
     *                 the exclude method.
     // * @param contains The user input of letters contained in the Worlde word.
     * @return Returns wordList with the contained letters.
     */
    public static ArrayList<Wordle> containsLetters(ArrayList<Wordle> wordList, String containsOne,
    String containsTwo, String containsThree, String containsFour, String containsFive) {
        for (int i = 0; i < wordList.size(); i++) {
            String a = Character.toString(wordList.get(i).getTestString().charAt(0));
            if(a.equals(containsOne))
                wordList.get(i).setContainedLettersOne(false);
            else if (a.isEmpty() || wordList.get(i).getTestString().contains(containsOne))
                wordList.get(i).setContainedLettersOne(true);

            a = Character.toString(wordList.get(i).getTestString().charAt(1));
            if(a.equals(containsTwo))
                wordList.get(i).setContainedLettersTwo(false);
            else if (a.isEmpty() || wordList.get(i).getTestString().contains(containsTwo))
                wordList.get(i).setContainedLettersTwo(true);

            a = Character.toString(wordList.get(i).getTestString().charAt(2));
            if(a.equals(containsThree))
                wordList.get(i).setContainedLettersThree(false);
            else if (a.isEmpty() || wordList.get(i).getTestString().contains(containsThree))
                wordList.get(i).setContainedLettersThree(true);

            a = Character.toString(wordList.get(i).getTestString().charAt(3));
            if(a.equals(containsFour))
                wordList.get(i).setContainedLettersFour(false);
            else if (a.isEmpty() || wordList.get(i).getTestString().contains(containsFour))
                wordList.get(i).setContainedLettersFour(true);

            a = Character.toString(wordList.get(i).getTestString().charAt(4));
            if(a.equals(containsFive))
                wordList.get(i).setContainedLettersFive(false);
            else if (a.isEmpty() || wordList.get(i).getTestString().contains(containsFive))
                wordList.get(i).setContainedLettersFive(true);
        }

        for (int i = 0; i < wordList.size(); i++) {
            if(wordList.get(i).getContainedLettersOne()
                    && wordList.get(i).getContainedLettersTwo()
                    && wordList.get(i).getContainedLettersThree()
                    && wordList.get(i).getContainedLettersFour()
                    && wordList.get(i).getContainedLettersFive())
                wordList.get(i).setContainedLetters(true);
        }

        return wordList;
    }


    /**
     *
     * @param wordList The filtered ArrayList of Wordle objects that previously passed through
     *                 the exclude and contained method
     * @param positionOne The string of user input for the letter at position one.
     * @param positionTwo The string of user input for the letter at position two.
     * @param positionThree The string of user input for the letter at position three.
     * @param positionFour The string of user input for the letter at position four.
     * @param positionFive The string of user input for the letter at position five.
     * @return Returns the completed filtered ArrayList of Wordle objects.
     */
    public static ArrayList<Wordle> positionalLetters(ArrayList<Wordle> wordList, String positionOne,
                                                      String positionTwo, String positionThree,
                                                      String positionFour, String positionFive) {
        // The filtering of positional letters.
        for (int i = 0; i < wordList.size(); i++) {
            String a = Character.toString(wordList.get(i).getTestString().charAt(0));
            if (a.equals(positionOne) || positionOne.isEmpty())
                wordList.get(i).setPositionalLettersOne(true);
            else
                wordList.get(i).setPositionalLettersOne(false);

            a = Character.toString(wordList.get(i).getTestString().charAt(1));
            if (a.equals(positionTwo) || positionTwo.isEmpty())
                wordList.get(i).setPositionalLettersTwo(true);
            else
                wordList.get(i).setPositionalLettersTwo(false);

            a = Character.toString(wordList.get(i).getTestString().charAt(2));
            if (a.equals(positionThree) || positionThree.isEmpty())
                wordList.get(i).setPositionalLettersThree(true);
            else
                wordList.get(i).setPositionalLettersThree(false);

            a = Character.toString(wordList.get(i).getTestString().charAt(3));
            if (a.equals(positionFour) || positionFour.isEmpty())
                wordList.get(i).setPositionalLettersFour(true);
            else
                wordList.get(i).setPositionalLettersFour(false);

            a = Character.toString(wordList.get(i).getTestString().charAt(4));
            if (a.equals(positionFive) || positionFive.isEmpty())
                wordList.get(i).setPositionalLettersFive(true);
            else
                wordList.get(i).setPositionalLettersFive(false);

        }

        for (int i = 0; i < wordList.size(); i++) {
            if(wordList.get(i).getPositionalLettersOne()
                    && wordList.get(i).getPositionalLettersTwo()
                    && wordList.get(i).getPositionalLettersThree()
                    && wordList.get(i).getPositionalLettersFour()
                    && wordList.get(i).getPositionalLettersFive())
                wordList.get(i).setPositionalLetters(true);
        }
        return wordList;
    }

    /**
     *
     * @param wordList Inputs the word list to be reset and all attributes returned to false.
     * @return Returns the list to be used again.
     */
    public static ArrayList<Wordle> resetValues(ArrayList<Wordle> wordList) {
        // Steps through every Wordle object to set the values to false.
        for (int i = 0; i < wordList.size(); i++) {
            wordList.get(i).setExcludedLetters(false);
            wordList.get(i).setContainedLetters(false);
            wordList.get(i).setPositionalLettersOne(false);
            wordList.get(i).setPositionalLettersTwo(false);
            wordList.get(i).setPositionalLettersThree(false);
            wordList.get(i).setPositionalLettersFour(false);
            wordList.get(i).setPositionalLettersFive(false);
            wordList.get(i).setPositionalLetters(false);
        }
        return wordList;
    }

    /**
     *
     * @param args Launches the JavaFX application.
     */
    public static void main(String[] args) { // Start - Main
        launch(args);
    } // End - Main

    /**
     *
     * @param stage Creates Stage parameter
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception { // Start - start
        VBox rootBox = new VBox();

        // Refer to accompanying Java classes for details on the constructor dimensions passed
        // via inheritance.
        excludedLettersField = new TextField();
        Label excludedLettersLabel = new WordleLabel("Excluded");
        HBox excludedLetters = new HBox(excludedLettersField, excludedLettersLabel);
        excludedLetters.setAlignment(Pos.TOP_CENTER);
        excludedLetters.setPadding(BOX_PADDING);
        rootBox.getChildren().add(excludedLetters);

        containedLettersFieldOne = new WordleTextField();
        containedLettersFieldTwo = new WordleTextField();
        containedLettersFieldThree = new WordleTextField();
        containedLettersFieldFour = new WordleTextField();
        containedLettersFieldFive = new WordleTextField();
        Label containedLettersLabel = new WordleLabel("Contains");
        HBox containedLetters = new HBox(containedLettersFieldOne, containedLettersFieldTwo,
                containedLettersFieldThree, containedLettersFieldFour, containedLettersFieldFive,
                containedLettersLabel);
        containedLetters.setAlignment(Pos.TOP_CENTER);
        rootBox.getChildren().add(containedLetters);

        // Create TextFields for each positional character
        positionOneField = new WordleTextField();
        positionTwoField = new WordleTextField();
        positionThreeField = new WordleTextField();
        positionFourField = new WordleTextField();
        positionFiveField = new WordleTextField();
        // Create Label for positional characters
        Label positionLabel = new WordleLabel("Position");
        // Add all elements to positional letters HBox
        HBox positionalLetters = new HBox(positionOneField, positionTwoField,
                positionThreeField, positionFourField, positionFiveField, positionLabel);
        // Set settings and add to rootBox
        positionalLetters.setPadding(BOX_PADDING);
        positionalLetters.setAlignment(Pos.TOP_CENTER);
        rootBox.getChildren().add(positionalLetters);

        // Create the Go and Reset button and change settings
        Button startButton = new WordleButton("Go");
        // Links the button press to the ActionEvent class below
        startButton.setOnAction(new GoButtonHandler());

        // Reset buttons Action event currently not incorporated yet.
        Button resetButton = new WordleButton("Reset");
        resetButton.setOnAction(new ResetButtonHandler());

        HBox buttonBox = new HBox(startButton, resetButton);
        buttonBox.setPadding(BOX_PADDING);
        // Add the button to the root
        rootBox.getChildren().add(buttonBox);

        // Set the scene and begin
        Scene root = new Scene(rootBox, 600, 250);
        stage.setScene(root);
        stage.setTitle("Wordle Solver");
        stage.show();

    } // End - Start

    class ResetButtonHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            excludedLettersField.clear();
            containedLettersFieldOne.clear();
            positionOneField.clear();
            positionTwoField.clear();
            positionThreeField.clear();
            positionFourField.clear();
            positionFiveField.clear();
            clearScreen();
        }

    }


    class GoButtonHandler implements EventHandler<ActionEvent>
    {
        // Various String objects to hold the text passed into the JavaFX TextField objects
        public String excludedCharacters;
        public String containedCharacters;

        public String containedOne;
        public String containedTwo;
        public String containedThree;
        public String containedFour;
        public String containedFive;

        public String positionOne;
        public String positionTwo;
        public String positionThree;
        public String positionFour;
        public String positionFive;
        // Extract the ArrayList so it can be seen by the ActionEvent class and manipulated.
        public ArrayList<Wordle> wordList = initialize();


        @Override
        public void handle(ActionEvent event)
        {
            clearScreen();
            // Extracting all the text input from the TextFields and setting them to lower case
            // so no mismatches occur based on capitalization.
            excludedCharacters = excludedLettersField.getText().toLowerCase();
            containedCharacters = containedLettersFieldOne.getText().toLowerCase();

            containedOne = containedLettersFieldOne.getText().toLowerCase();
            containedTwo = containedLettersFieldTwo.getText().toLowerCase();
            containedThree = containedLettersFieldThree.getText().toLowerCase();
            containedFour = containedLettersFieldFour.getText().toLowerCase();
            containedFive = containedLettersFieldFive.getText().toLowerCase();

            positionOne = positionOneField.getText().toLowerCase();
            positionTwo = positionTwoField.getText().toLowerCase();
            positionThree = positionThreeField.getText().toLowerCase();
            positionFour = positionFourField.getText().toLowerCase();
            positionFive = positionFiveField.getText().toLowerCase();

            // Once the input is read the wordList can then be filtered
            wordList = excludeLetters(wordList, excludedCharacters);
            wordList = containsLetters(wordList, containedOne, containedTwo, containedThree, containedFour, containedFive);
            wordList = positionalLetters(wordList, positionOne, positionTwo, positionThree, positionFour, positionFive);

            // Print to the terminal all applicable words
            for(int i = 0; i < wordList.size(); i++) {
                if (!wordList.get(i).getExcludedLetters() && wordList.get(i).getContainedLetters()
                        && wordList.get(i).getPositionalLetter())
                    System.out.println(wordList.get(i).getTestString());
            }
            // Resets all the boolean attributes so that they can be set again properly.
            wordList = resetValues(wordList);

        } // End - handle(ActionEvent)
    } // End - Button Handler(Class)
} //end class Wordle

