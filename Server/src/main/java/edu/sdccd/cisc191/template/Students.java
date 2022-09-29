package edu.sdccd.cisc191.template;

public class Students {
    public int numOfStudents;
    public String[][] studentAttendance = new String[numOfStudents][2];

    public void setNumOfStudents(int numOfStudents) {
        this.numOfStudents = numOfStudents;
    }
}
