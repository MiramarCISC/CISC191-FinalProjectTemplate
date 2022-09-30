package edu.sdccd.cisc191.template;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;
import org.junit.jupiter.api.Test;


public class TestView extends ApplicationTest {

    private Button[] buttons = new Button[1];
    private GridPane gridpane = new GridPane();

   @Override
    public void start(Stage primaryStage) {
       buttons[0] = new Button();
       gridpane = new GridPane();
   }

   @Test
    void testDisplayButtons() {
        View view = new View();

        view.displayButtons(gridpane, buttons);
    }
}
