package edu.sdccd.cisc191.template;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Model {

    /**
     * Displays the Buttons in a column inside on the left
     * inside a GridPane.
     * @param gridPane the GridPane object
     * @param buttons the array of Buttons to be added to
     *                the GridPane
     */
    public void displayButtons(GridPane gridPane, Button[] buttons) {

        for (int i = 0; i < buttons.length; i++)
        {
        gridPane.add(buttons[i], 0, i + 1 );
        // i + 1 n rowIndex to leave room for the Label at the top
        }
    }

    /**
     * Displays TextFields in a column on the right of
     * a GridPane
     * @param gridPane the GridPane object
     * @param textFields the array of TextFields objects to be inserted in the
     *                   GridPane
     */
    public void displayTextFields(GridPane gridPane, TextField[] textFields) {

        for (int i = 0; i < textFields.length; i++) {
            // columnIndex 1 so the TextFields will be right next
            // to the Buttons
            gridPane.add(textFields[i], 1, i + 1);
        }
    }
}
