package edu.sdccd.cisc191.template;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import javax.swing.text.html.ImageView;
import java.awt.event.MouseEvent;

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
    public void changeTextColor(Paint color){
        setTextFill(color);
    }
    public void changeBackGroundColor(){
        setStyle("-fx-background-color: #98DBC5");
    }
    public void addImage(ImageView image){
    }
    public void handleClick() {
        isClicked = true;
    }
}