package edu.sdccd.cisc191.template;

import javafx.scene.control.Button;

public class AppButton extends Button{

    public AppButton() {

        //sets the minimum size and style for all app buttons
        this.setMinSize(130, 30);
        this.setStyle("-fx-background-color: #FBF6FC; -fx-border-color: #E6C8EC; -fx-border-width: 5;" +
                "-fx-font-family: Arial; -fx-text-fill: #563C89");
    }
}
