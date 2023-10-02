package edu.sdccd.cisc191.template;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

public class SubjectPopup extends Popup {

    private TextField inputSubjectName = new TextField();
    private TextField inputSubjectNumber = new TextField();
    private SubjectButton subjectButton = new SubjectButton();
    private AppLabel nameAdd = new AppLabel();
    private AppLabel numberAdd = new AppLabel();

    /**
     * Sets contents of subject popup
     * @param inputSubjectName textfield for user subject name
     * @param inputSubjectNumber textfield for user subject number
     */
    public VBox getSubjectAdding(TextField inputSubjectName, TextField inputSubjectNumber) {

        nameAdd.setText("Name: ");
        numberAdd.setText("Number: ");
        HBox subjectNameBox = new HBox(5, nameAdd, inputSubjectName);
        HBox subjectNumberBox = new HBox(5, numberAdd, inputSubjectNumber);
        VBox subjectAdding = new VBox(5, subjectNameBox, subjectNumberBox);
        subjectAdding.setStyle("-fx-background-color: #E3F5F2; -fx-border-color: #E6C8EC;-fx-border-width:5");
        subjectAdding.setPadding(new Insets(10));
        return subjectAdding;
    }

    /**
     * Resets contents of subject popup after user is finished
     * @param subjectButton resets button text to default
     * @param inputSubjectName clears textfield contents
     * @param inputSubjectNumber clears textfield contents
     */
    public void reset(SubjectButton subjectButton, TextField inputSubjectName, TextField inputSubjectNumber) {

        subjectButton.setText("New Subject");
        inputSubjectName.clear();
        inputSubjectNumber.clear();
    }
}
