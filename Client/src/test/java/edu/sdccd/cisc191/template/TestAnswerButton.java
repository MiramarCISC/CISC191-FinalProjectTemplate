package edu.sdccd.cisc191.template;

import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.Test;
import javafx.scene.control.Button;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAnswerButton extends ApplicationTest {
    private AnswerButton answerButton;

    @Test
    void testAnswerButton() {
        JFXPanel panel = new JFXPanel();
        answerButton = new AnswerButton("question", "answer");

        assertEquals("question", answerButton.getQuestion());
        assertEquals("answer", answerButton.getAnswer());

    }
}
