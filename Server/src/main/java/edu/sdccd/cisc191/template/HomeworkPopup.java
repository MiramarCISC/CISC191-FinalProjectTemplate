package edu.sdccd.cisc191.template;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

import java.time.LocalDate;

public class HomeworkPopup extends Popup {

    private TextField inputHomeworkName = new TextField();
    private CheckBox urgentCheck = new CheckBox();
    private DatePicker inputDueDate = new DatePicker();
    private ComboBox<String> subjectBox = new ComboBox<>();
    private AppLabel nameAdd = new AppLabel();
    private AppLabel urgentSet = new AppLabel();
    private AppLabel subjectSet = new AppLabel();
    private AppLabel dateSet = new AppLabel();


    /**
     * Get a string containing the user's homework name
     * @param inputHomeworkName contains user's homework name
     * @return a String containing user's homework name
     */
    public String getName(TextField inputHomeworkName) {

        String name = new String();
        if (inputHomeworkName.getText().equals("")) {
            name = "No Name";
        }
        else {
            name = inputHomeworkName.getText();
        }
        return name;
    }

    /**
     * get a string containing the subject information
     * @param subjectBox contains the user's selection for subject
     * @return a String containing the subject information
     */
    public String getSubject(ComboBox<String> subjectBox) {

        String subject = new String();
        if (subjectBox.getValue() == null) {
            subject = "No Subject";
        }
        else {
            subject = subjectBox.getValue();
        }
        return subject;
    }

    /**
     * get a string containing the date information
     * @param inputDueDate is used to set the date of the assignment only if the user makes a selection
     * @return a string containing the date of the homework assignment
     */
    public String getDate(DatePicker inputDueDate) {

        String date = new String();
        if (inputDueDate.getValue() == null) {
            date = new String("No Due Date");
        }
        else {
            LocalDate localDate = inputDueDate.getValue();
            date = localDate.toString();
        }

        return date;
    }

    /**
     * set urgent to true if the checkbox is selected
     * @param urgentCheck is used to give the urgent boolean a value
     */
    public boolean getUrgent(CheckBox urgentCheck) {

        boolean urgent = urgentCheck.isSelected();
        return urgent;
    }

    /**
     * clear user input fields and reset for new use
     * @param inputHomeworkName gets cleared
     * @param urgentCheck gets unselected
     * @param inputDueDate gets its value removed
     * @param subjectBox gets its value removed
     */
    public void reset(TextField inputHomeworkName, CheckBox urgentCheck, DatePicker inputDueDate, ComboBox<String> subjectBox) {

        inputHomeworkName.clear();
        subjectBox.setValue(null);
        urgentCheck.setSelected(false);
        inputDueDate.setValue(null);
    }

    /**
     * sets content and style of the VBox
     * @param inputDueDate allows the user to input a due date for the homework
     * @param inputHomeworkName allows the user to input a name for the homework
     * @param subjectBox allows the user to select between a list of subjects
     * @param urgentCheck allows the user to mark an assignment as urgent
     * @return a VBox with contents for the homework popup
     */
    public VBox getHomeworkAdding(TextField inputHomeworkName, CheckBox urgentCheck, DatePicker inputDueDate, ComboBox<String> subjectBox) {
        nameAdd.setText("Homework Name: ");
        subjectSet.setText("Subject: ");
        urgentSet.setText("Urgent: ");
        dateSet.setText("Due Date: ");
        HBox homeworkName = new HBox(5, nameAdd, inputHomeworkName);
        HBox homeworkSubject = new HBox(5, subjectSet, subjectBox);
        HBox homeworkUrgency = new HBox(5, urgentSet, urgentCheck);
        HBox homeworkDate = new HBox(5, dateSet, inputDueDate);
        VBox homeworkAdding = new VBox(5, homeworkName, homeworkSubject, homeworkDate, homeworkUrgency);
        homeworkAdding.setStyle("-fx-background-color: #E6F5E3; -fx-border-color: #E6C8EC;-fx-border-width:5");
        homeworkAdding.setPadding(new Insets(10));
        return homeworkAdding;
    }
}
