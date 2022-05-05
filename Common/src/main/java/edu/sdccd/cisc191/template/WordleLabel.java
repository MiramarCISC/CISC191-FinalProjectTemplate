package edu.sdccd.cisc191.template;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class WordleLabel extends Label {

    private GridPane resultsPane = new GridPane();

    public static Insets LABEL_PADDING = new Insets(0,0,0,10);

    public GridPane getGridPane() {
        return resultsPane;
    }

    public void addLabel(String labelName, int columnIndex, int rowIndex) {
        WordleLabel label = new WordleLabel();
        GridPane.setColumnIndex(label, columnIndex);
        GridPane.setRowIndex(label, rowIndex);
        label.setId(labelName + columnIndex);
        label.setText("test");

        resultsPane.getChildren().add(label);
    }

    public WordleLabel() {
        // No arg
    }



    public WordleLabel(String s)
    {
        setText(s);
        setFont(Font.font(20));
        setPadding(LABEL_PADDING);
    }


}