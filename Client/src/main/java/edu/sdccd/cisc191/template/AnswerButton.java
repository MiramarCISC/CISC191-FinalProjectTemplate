package edu.sdccd.cisc191.template;

import javafx.scene.control.Button;

/**
 * AnswerButton is a type of Button that stores both the
 * question and the answer to the flashcards
 */

public class AnswerButton extends Button {
    private String question;
    private String answer;


    /**
     * AnswerButton constructor. It stores questions and answers as
     * Strings. The question will be the text on the Button.
     * @param question the question of the integral/derivative
     * @param answer the answer to the integral/derivative
     */
    public AnswerButton(String question, String answer) {
        this.question = question;
        this.answer = answer;

        setText(question);

    }


    public String getAnswer() {
        return answer;
    }
    public String getQuestion() {
        return question;
    }

}
