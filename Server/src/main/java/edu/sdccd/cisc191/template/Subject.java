package edu.sdccd.cisc191.template;

import java.util.ArrayList;
public class Subject {
    private String nameOfSubject;
    private boolean weighted;
    private double gradeInClass;
    private ArrayList<Assignment> assignmentList;

    public Subject() {
        nameOfSubject = "";
        weighted = false;
        gradeInClass = 0.0;
        assignmentList = new ArrayList<Assignment>();
    }

    public Subject(String name, boolean weight, double grade) {
        nameOfSubject = name;
        weighted = weight;
        gradeInClass = grade;
    }

    /**
     * Copy constructor
     * @param subject edu.sdccd.cisc191.template.Subject object to copy
     */
    public Subject(Subject subject) {
        nameOfSubject = subject.getNameOfSubject();
        weighted = subject.isWeighted();
        gradeInClass = subject.getGradeInClass();
        assignmentList = subject.getAssignmentList();
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
}


