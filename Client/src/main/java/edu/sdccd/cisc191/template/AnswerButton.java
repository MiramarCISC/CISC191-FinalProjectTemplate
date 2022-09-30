package edu.sdccd.cisc191.template;

import javafx.scene.control.Button;

public class AnswerButton extends Button {
    private Button button;
    private String question;
    private String answer;

    private String[] questionArray = new String[8];


    public AnswerButton(Button button, String question, String answer) {
        this.button = button;
        this.question = question;
        this.answer = answer;

        button.setText(question);

    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

}
