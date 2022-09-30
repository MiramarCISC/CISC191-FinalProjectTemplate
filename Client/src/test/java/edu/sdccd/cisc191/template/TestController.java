package edu.sdccd.cisc191.template;
import com.sun.javafx.robot.FXRobot;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javafx.scene.control.Button;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;


public class TestController extends ApplicationTest {

private Button button;
private TextField textField;
    @Override
    public void start(Stage stage) {
        button = new Button();
        textField = new TextField();

        stage.setScene(new Scene(new StackPane(button), 100, 100));
        stage.show();
    }
    @Test
    void testSetTextFieldAndButton() {
        JFXPanel panel = new JFXPanel();
        // tricks JUNIT
        Button button = new Button();
        TextField textfield = new TextField();

        Controller controller = new Controller();

        controller.setTextFieldAndButton(textfield, "prompt text", button, "answer text");

        assertEquals("prompt text", textfield.getPromptText());


    }

    // Make interfaces classes with testable methods
    // instatiate question, get answer, assertEqual question and answer
    // for loop
    // Hash Map questions to answers
    // Build upon in September

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

    @Test
    void getCorrectAnswerCount() {
        Controller controller = new Controller();
        assertEquals(0, controller.getCorrectAnswerCount());

        int test = controller.getCorrectAnswerCount() + 1;

        assertEquals(1, test);
    }

}
