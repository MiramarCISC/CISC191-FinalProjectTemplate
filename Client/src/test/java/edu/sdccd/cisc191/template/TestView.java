package edu.sdccd.cisc191.template;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javafx.scene.control.Button;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestView {
    private Button[] buttons;
    private TextField[] textFields;
    View view;

    @BeforeAll public void setUp() {
        buttons = new Button[2];
        buttons[0] = new Button();
        buttons[1] = new Button();

        textFields = new TextField[2];
        textFields[0] = new TextField();
        textFields[1] = new TextField();

        view = new View();


    }

    @Test
    void testDisplayButtons() {
        GridPane gridpane = new GridPane();
        view.displayButtons(gridpane, buttons);

    }


    // Add testFx to maven dependencies during test scope
}
