package edu.sdccd.cisc191.template;

import java.util.Scanner;
public class Assignment {

    private String nameOfAssignment;
    private int daysUntilDueDate;
    private int pointsOfAssignment;
    //private int estInMinutes;
    private boolean busyWork;

    public Assignment() {
        nameOfAssignment = "";
        daysUntilDueDate = 0;
        pointsOfAssignment = 0;
        busyWork = false;
    }

    public Assignment(String name) {
        nameOfAssignment = name;
        daysUntilDueDate = 0;
        pointsOfAssignment = 0;
        busyWork = false;
    }
    public String getNameOfAssignment() {
        return nameOfAssignment;
    }

    public void setNameOfAssignment(String nameOfAssignment) {
        this.nameOfAssignment = nameOfAssignment;
    }

    public int getPointsOfAssignment() {
        return pointsOfAssignment;
    }
    public void setPointsOfAssignment(int pointsOfAssignment) {
        this.pointsOfAssignment = pointsOfAssignment;
    }

//    public int getEstInMinutes() {
//        return estInMinutes;
//    }

//    public void setEstInMinutes(int estInMinutes) {
//        this.estInMinutes = estInMinutes;
//    }

    public boolean isBusyWork() {
        return busyWork;
    }

    public void setBusyWork(boolean busyWork) {
        this.busyWork = busyWork;
    }

    public int getDaysUntilDueDate() {
        return daysUntilDueDate;
    }

    public void setDaysUntilDueDate(int daysUntilDueDate) {
        this.daysUntilDueDate = daysUntilDueDate;
    }
}
