package edu.sdccd.cisc191.template;

import javafx.scene.control.Button;
public class OptionButton extends Button {
    private String text;
    private double width;
    private double height;
    private boolean isClicked;

    public OptionButton (String text, double width, double height) {
        setText(text);
        setPrefWidth(width);
        setPrefHeight(height);
    }
    public void handleClick() {
        isClicked = true;
    }
}
