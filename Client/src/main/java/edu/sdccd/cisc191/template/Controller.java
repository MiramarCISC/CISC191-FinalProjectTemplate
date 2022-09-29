package edu.sdccd.cisc191.template;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    /**
     * Compares the user input to the actual answers stored in
     * the interfaces
     * @param textField the TextField object
     * @param answer the text of the actual answer to be compared
     */
    public void checkAnswer(String textField, String answer) {
        if (textField.equals(answer)) {
            System.out.println("User input matches answer");
        } else {
            System.out.println("User input did not match answer. " +
                    "TextField changed to match answer.");
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
            checkAnswer(textField.getText(), answer);
            textField.setText(answer);
        });


    }

}
