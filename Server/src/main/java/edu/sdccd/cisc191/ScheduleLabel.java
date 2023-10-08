package edu.sdccd.cisc191;

import javafx.geometry.Insets;
import javafx.scene.control.Label;

//class use to set up all the labels that will hold text message
public class ScheduleLabel extends Label {
    public static Insets LABEL_PADDING = new Insets(20);

    public ScheduleLabel() {
        setPadding(LABEL_PADDING);
    }
}