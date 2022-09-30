package edu.sdccd.cisc191.template;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javafx.scene.control.Button;
import org.junit.jupiter.api.TestInstance;

public class TestController {

    @Test
    void testCheckAnswer() {
        Controller controller = new Controller();

        String answer = "answer";
        String correctAnswer = "answer";

        controller.checkAnswer(correctAnswer, answer);

        String wrongAnswer = "wrong answer";

        controller.checkAnswer(wrongAnswer, answer);




    }
}
