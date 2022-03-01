package edu.sdccd.cisc191.template;

import javafx.scene.control.Button;
import javafx.geometry.Pos;
import java.awt.*;

public class WordleButton extends Button {

    WordleButton(String s) {
        setText(s);
        setPrefSize(60, 40);
        setAlignment(Pos.CENTER_RIGHT);
    }
}