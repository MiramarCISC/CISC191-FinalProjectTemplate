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
import java.io.*;
import java.util.ArrayList;
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
     * 5-letter word and 7 booleans to be altered to filter excluded and included words
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

    public static void mergeSort(ArrayList<Wordle> wordList) {


        if (wordList.size() > 2) {
            ArrayList<Wordle> left = new ArrayList<>();
            for (int i = 0; i < wordList.size() / 2; i++) {
                left.add(wordList.get(i));
            }
            ArrayList<Wordle> right = new ArrayList<>();
            for (int i = wordList.size() / 2; i < wordList.size(); i++) {
                right.add(wordList.get(i));
            }

            mergeSort(left);
            mergeSort(right);
            merge(wordList, left, right);
        }
    }

    public static void merge(ArrayList<Wordle> wordList, ArrayList<Wordle> left, ArrayList<Wordle> right) {
        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < wordList.size(); i++) {
            if (i2 >= right.size() || (i1 < left.size() &&
                    left.get(i1).getTestString().compareToIgnoreCase(right.get(i2).getTestString()) < 0 )) {
                wordList.set(i, left.get(i1));
                i1++;
            } else {
                wordList.set(i, right.get(i2));
                i2++;
            }
        }
    }

    /**
     * @return Creates an ArrayList of Wordle objects to be extracted later by the filtering and
     * ActionEvent methods.
     */
    public static ArrayList<Wordle> initialize() {
        InputStream is = Wordle.class.getClassLoader().getResourceAsStream("unlimited_words.txt");

        try {
            assert is != null;
            Scanner sc = new Scanner(is);
            // Create the array list of type Wordle
            ArrayList<Wordle> wordList = new ArrayList<>();

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
     */
    public static void excludeLetters(ArrayList<Wordle> wordList, String exclude)
    // Very simply see if words are contain any part of the passed in String then its value is set
    // to true and not printed later.
    {
        for (Wordle wordle : wordList) { // Original
            for (int j = 0; j < exclude.length(); j++) { // Nested
                if (wordle.getTestString().contains(Character.toString(exclude.charAt(j))))
                    wordle.setExcludedLetters(true);
            } // Nested
        } // Original
    }

    /**
     *
     * @param wordList The filtered ArrayList of Wordle objects that previously passed through
     *                 the exclude method.
     * @param containsOne The user input of letter at contained at position one.
     * @param containsTwo The user input of letter at contained at position two.
     * @param containsThree The user input of letter at contained at position three.
     * @param containsFour The user input of letter at contained at position four.
     * @param containsFive The user input of letter at contained at position five.
     */
    public static void containsLetters(ArrayList<Wordle> wordList, String containsOne,
    String containsTwo, String containsThree, String containsFour, String containsFive) {

        // Parses every word from the Wordle list
        for (Wordle wordle : wordList) {
            String a = Character.toString(wordle.getTestString().charAt(0));
            if (a.equals(containsOne)) // If the letter is contained at the position it can be eliminated, and it sets false
                wordle.setContainedLettersOne(false);
            else if (a.isEmpty() || wordle.getTestString().contains(containsOne)) // If it contains the letter NOT in the inputted position returns true
                wordle.setContainedLettersOne(true);

            a = Character.toString(wordle.getTestString().charAt(1));
            if (a.equals(containsTwo))
                wordle.setContainedLettersTwo(false);
            else if (a.isEmpty() || wordle.getTestString().contains(containsTwo))
                wordle.setContainedLettersTwo(true);

            a = Character.toString(wordle.getTestString().charAt(2));
            if (a.equals(containsThree))
                wordle.setContainedLettersThree(false);
            else if (a.isEmpty() || wordle.getTestString().contains(containsThree))
                wordle.setContainedLettersThree(true);

            a = Character.toString(wordle.getTestString().charAt(3));
            if (a.equals(containsFour))
                wordle.setContainedLettersFour(false);
            else if (a.isEmpty() || wordle.getTestString().contains(containsFour))
                wordle.setContainedLettersFour(true);

            a = Character.toString(wordle.getTestString().charAt(4));
            if (a.equals(containsFive))
                wordle.setContainedLettersFive(false);
            else if (a.isEmpty() || wordle.getTestString().contains(containsFive))
                wordle.setContainedLettersFive(true);
        }

        // Returns a Wordle object's contained letter to true if it is an applicable word.
        for (Wordle wordle : wordList) {
            if (wordle.getContainedLettersOne()
                    && wordle.getContainedLettersTwo()
                    && wordle.getContainedLettersThree()
                    && wordle.getContainedLettersFour()
                    && wordle.getContainedLettersFive())
                wordle.setContainedLetters(true);
        }

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
     */
    public static void positionalLetters(ArrayList<Wordle> wordList, String positionOne,
                                                      String positionTwo, String positionThree,
                                                      String positionFour, String positionFive) {
        // The filtering of positional letters.
        for (Wordle wordle : wordList) {
            String a = Character.toString(wordle.getTestString().charAt(0));
            // Returns true if the letter is correct empty
            wordle.setPositionalLettersOne(a.equals(positionOne) || positionOne.isEmpty());

            a = Character.toString(wordle.getTestString().charAt(1));
            wordle.setPositionalLettersTwo(a.equals(positionTwo) || positionTwo.isEmpty());

            a = Character.toString(wordle.getTestString().charAt(2));
            wordle.setPositionalLettersThree(a.equals(positionThree) || positionThree.isEmpty());

            a = Character.toString(wordle.getTestString().charAt(3));
            wordle.setPositionalLettersFour(a.equals(positionFour) || positionFour.isEmpty());

            a = Character.toString(wordle.getTestString().charAt(4));
            wordle.setPositionalLettersFive(a.equals(positionFive) || positionFive.isEmpty());

        }

        // Returns a Wordle object's positional letter to true if it is an applicable word.
        for (Wordle wordle : wordList) {
            if (wordle.getPositionalLettersOne()
                    && wordle.getPositionalLettersTwo()
                    && wordle.getPositionalLettersThree()
                    && wordle.getPositionalLettersFour()
                    && wordle.getPositionalLettersFive())
                wordle.setPositionalLetters(true);
        }

    }

    /**
     *
     * @param wordList Inputs the word list to be reset and all attributes returned to false.
     */
    static void resetValues(ArrayList<Wordle> wordList) {
        // Steps through every Wordle object to set the values to false.
        for (Wordle wordle : wordList) {
            wordle.setExcludedLetters(false);
            wordle.setContainedLetters(false);
            wordle.setContainedLettersOne(false);
            wordle.setContainedLettersTwo(false);
            wordle.setContainedLettersThree(false);
            wordle.setContainedLettersFour(false);
            wordle.setContainedLettersFive(false);
            wordle.setPositionalLettersOne(false);
            wordle.setPositionalLettersTwo(false);
            wordle.setPositionalLettersThree(false);
            wordle.setPositionalLettersFour(false);
            wordle.setPositionalLettersFive(false);
            wordle.setPositionalLetters(false);
        }
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
     */
    @Override
    public void start(Stage stage) { // Start - start
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

    static class ResetButtonHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            excludedLettersField.clear();
            containedLettersFieldOne.clear();
            containedLettersFieldTwo.clear();
            containedLettersFieldThree.clear();
            containedLettersFieldFour.clear();
            containedLettersFieldFive.clear();
            positionOneField.clear();
            positionTwoField.clear();
            positionThreeField.clear();
            positionFourField.clear();
            positionFiveField.clear();
            clearScreen();
        }

    }


    static class GoButtonHandler implements EventHandler<ActionEvent>
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
            mergeSort(wordList);
            excludeLetters(wordList, excludedCharacters);
            containsLetters(wordList, containedOne, containedTwo, containedThree, containedFour, containedFive);
            positionalLetters(wordList, positionOne, positionTwo, positionThree, positionFour, positionFive);

            // Print to the terminal all applicable words
            for (Wordle wordle : wordList) {
                if (!wordle.getExcludedLetters() && wordle.getContainedLetters()
                        && wordle.getPositionalLetter())
                    System.out.println(wordle.getTestString());
            }
            //
            System.out.println();
            // Resets all the boolean attributes so that they can be set again properly.
            resetValues(wordList);

        } // End - handle(ActionEvent)
    } // End - Button Handler(Class)
} // End - Class Wordle