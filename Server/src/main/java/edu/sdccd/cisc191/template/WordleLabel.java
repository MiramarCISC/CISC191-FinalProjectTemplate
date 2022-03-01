package edu.sdccd.cisc191.template;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class WordleLabel extends Label {
    public static Insets LABEL_PADDING = new Insets(0,0,0,10);

    public WordleLabel(String s)
    {
        setText(s);
        setFont(Font.font(20));
        setPadding(LABEL_PADDING);
    }

}