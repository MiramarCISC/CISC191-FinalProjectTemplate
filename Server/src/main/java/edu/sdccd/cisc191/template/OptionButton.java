package edu.sdccd.cisc191.template;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javax.swing.text.html.ImageView;

public class OptionButton extends Button {
    private String text;
    private double width;
    private double height;
    private boolean isClicked;
    private Paint textColor;
    private Font font = Font.font("Montserrat", FontWeight.BOLD, 18);

    public OptionButton (String text, double width, double height) {
        setText(text);
        setPrefWidth(width);
        setPrefHeight(height);
        setFont(font);
    }
    public void changeTextColor(Paint color){
        setTextFill(color);
    }
    public void setTextColor(int i){

    }
    public void changeTextColor(int i){
        switch (i) {
            case 0:
                setTextFill(Color.web("#FF0000"));
                setStyle("-fx-background-color: #ff7777");
                break;
            case 1:
                setTextFill(Color.web("#0000FF"));
                setStyle("-fx-background-color: #7789ff");
                break;
            case 2:
                setTextFill(Color.web("#FFFF00"));
                setStyle("-fx-background-color: #ffcf77");
                break;
            case 3:
                setTextFill(Color.web("#00FF00"));
                setStyle("-fx-background-color: #166137");
                break;
            case 4:
                setTextFill(Color.web("#FFA500"));
                setStyle("-fx-background-color: #e89313");
                break;
            case 5:
                setTextFill(Color.web("#A020F0"));
                setStyle("-fx-background-color: #5a0b6e");
            default:
        }
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