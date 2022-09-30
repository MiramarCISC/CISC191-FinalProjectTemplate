package edu.sdccd.cisc191.template;

import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestController {
    @Test
    void testSet() {
        JFXPanel panel = new JFXPanel();
        // tricks JUNIT
        Button button = new Button();
        TextField textfield = new TextField();

        Controller controller = new Controller();

        controller.setTextFieldAndButton(textfield, "test", button, "test");

        assertEquals("test", textfield.getPromptText());
    }

    // Make interfaces classes with testable methods
    // instatiate question, get answer, assertEqual question and answer
    // for loop
    // Hash Map questions to answers
    // Build upon in September


    // method that creates button and sets controller method
    // test button creation
    // Have method return button
    @Test
    void testCheckAnswer() {
        Controller controller = new Controller();

        String answer = "answer";
        String correctAnswer = "answer";

        controller.checkAnswer(correctAnswer, answer);

        String wrongAnswer = "wrong answer";

        controller.checkAnswer(wrongAnswer, answer);

        // Test Per topic you're demonstrating


    }
}
