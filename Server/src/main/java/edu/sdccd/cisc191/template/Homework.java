package edu.sdccd.cisc191.template;

import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.util.List;

public class Homework implements Urgent{
    private String subject;
    private boolean urgent;
    private String name;
    private String dueDate;

    /**
     * constructor for homework object
     * @param date contains a String for the homework assignment date
     * @param name contains a String for the homework name
     * @param subject contains a String for the subject
     * @param urgent is a boolean to describe state of urgency
     */
    public Homework (String name, String subject, boolean urgent, String date) {

        this.name = name;
        this.subject = subject;
        this.urgent = urgent;
        this.dueDate = date;
    }

    /**
     * return a String describing the homework details
     * @return a String with subject, name, due date information
     */
    public String getHomework() {
        String homework = subject + " - " + name + " - " + dueDate;
        //if no subject, handle case
        return homework;
    }

    /**
     * add an asterisk if the homework is urgent
     * @return an asterisk if the homework is urgent
     */
    @Override
    public String isUrgent() {
        if (urgent == true) {
            return "*\n";

        }
        else {
            return "\n";
        }
    }
}
