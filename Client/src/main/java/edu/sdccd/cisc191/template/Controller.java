package edu.sdccd.cisc191.template;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {
    private int correctAnswerCount = 0;


    /**
     * Compares the user input to the actual answers stored in
     * the interfaces
     * @param textField the TextField object
     * @param answer the text of the actual answer to be compared
     */
    public void checkAnswer(String textField, String answer) {
        if (textField.equals(answer)) {
            correctAnswerCount++;
            System.out.println("User input matches answer." +
                    " Score so far: " + correctAnswerCount + "/8");
        } else {
            System.out.println("User input did not match answer. " +
                    "TextField changed to match answer." +
                    " Score so far: " + correctAnswerCount + "/8");
        }
    }

    /**
     * Sets the TextField's PromptText and the Button's event actions
     * to compare the user input to the actual answer, and then displays
     * the actual answer
     * @param textField the TextField object
     * @param promptText the hint text you want in the TextField
     *                   before the user types their input
     * @param button the Button object
     * @param answer the String answer to the integral/derivative that is stored in
     *               the interfaces DerivativesOfTrigFunctions and
     *               IntegralsOfTrigFunctions
     */
    public void setTextFieldAndButton(TextField textField, String promptText,
                                      Button button, String answer) {
        textField.setPromptText(promptText);
        button.setOnAction(event -> {

            // uses the checkAnswer method above to compare user input
            checkAnswer(textField.getText(), answer);

            // displays the correct answer in the TextField
            textField.setText(answer);

            // disables the button
            button.setDisable(true);
        });


    }

    /**
     * Shows the amount of correct answers the user got
     * in the console
     * @param submit the submit button
     */
    public void countCorrectAnswers(Button submit) {
        submit.setOnAction(event -> {
            /* Only prints out in the console so the user can
            pretend they're taking a real quiz as they study */
            System.out.println("Final score: " + correctAnswerCount + "/8");
        });
    }

    /**
     * Resets all the Buttons and TextFields as if the program was
     * just starting again, so the user can re-quiz themselves on
     * the formulas
     * @param reset the reset buttons
     * @param buttons the array of Buttons to re-enable
     * @param textFields the array of TextFields to clear
     */
    public void resetButton(Button reset, Button[] buttons, TextField[] textFields) {

        reset.setOnAction(event -> {
            // console message
            System.out.println("Flashcards and score have been reset.");

            // for loop to get all items in the buttons and textFields arrays
            for(int i = 0; i < buttons.length; i++) {
            // allows user to press the buttons again
            buttons[i].setDisable(false);

            // clears the TextField so the promptText shows up again
            textFields[i].setText("");

            // resets the correctAnswerCount for grading
            correctAnswerCount = 0;
        }
        });
    }


}
