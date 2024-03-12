package edu.sdccd.cisc191.template;

import java.util.ArrayList;

public class Subject {
    private String nameOfSubject;
    private boolean weighted;
    private double gradeInClass;
    private ArrayList<Assignment> assignmentList;

    private int color;

    public Subject() {
        nameOfSubject = "";
        weighted = false;
        gradeInClass = 0.0;
        assignmentList = new ArrayList<Assignment>();
        color = 0;
    }

    public Subject(String name, boolean weight, double grade) {
        nameOfSubject = name;
        weighted = weight;
        gradeInClass = grade;
        assignmentList = new ArrayList<Assignment>();
        color = 0;
    }

    public Subject(String name, double grade) {
        nameOfSubject = name;
        gradeInClass = grade;
        assignmentList = new ArrayList<Assignment>();
        color = 0;
    }

    /**
     * Copy constructor
     *
     * @param subject edu.sdccd.cisc191.template.Subject object to copy
     */
    public Subject(Subject subject) {
        nameOfSubject = subject.getNameOfSubject();
        weighted = subject.isWeighted();
        gradeInClass = subject.getGradeInClass();
        assignmentList = subject.getAssignmentList();
        color = 0;

    }

    public String getNameOfSubject() {
        return nameOfSubject;
    }

    public void setNameOfSubject(String nameOfSubject) {
        this.nameOfSubject = nameOfSubject;
    }

    public boolean isWeighted() {
        return weighted;
    }

    public void setWeighted(boolean weighted) {
        this.weighted = weighted;
    }

    public double getGradeInClass() {
        return gradeInClass;
    }

    public void setGradeInClass(double gradeInClass) {
        this.gradeInClass = gradeInClass;
    }

    public ArrayList<Assignment> getAssignmentList() {
        return assignmentList;
    }

    public void setAssignmentList(ArrayList<Assignment> assignmentList) {
        this.assignmentList = assignmentList;
    }


    public void addAssignment(Assignment assignment) {
        assignmentList.add(assignment);
    }


    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}