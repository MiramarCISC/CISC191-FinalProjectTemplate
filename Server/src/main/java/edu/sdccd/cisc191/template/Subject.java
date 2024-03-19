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


    /**
     * for the 2D array thing also should we have javadocs for any of these methods in our project
     *
     * @return
     * @author Simon Nguyen
     */
    public String[][] convertAssignmentListTo2DArray() {
        int numRows = assignmentList.size();
        int numCols = 0;


        /*  make sure we have enough slots to fit all the assignments if they have different
        amount of stuff in it
        */

        for (Assignment assignment : assignmentList) {
            int assignmentNumCols = assignment.getAssignmentArray().length;
            if (assignmentNumCols > numCols) {
                numCols = assignmentNumCols;
            }
        }

            String[][] assignmentArray = new String[numRows][numCols];

            for (int i = 0; i < numRows; i++) {
                Assignment temp = assignmentList.get(i);

                String[] assignmentDetails = temp.getAssignmentArray();
                assignmentArray[i] = assignmentDetails;
            }



        return assignmentArray;
        }

    public String convertAssignmentListToString() {
        String result = "";

        String[][] assignmentArray = convertAssignmentListTo2DArray();

        for (String[] row : assignmentArray) {
            for (String element : row) {
                result += element + " ";
            }
            result += "\n";
        }

        return result;
    }



}



