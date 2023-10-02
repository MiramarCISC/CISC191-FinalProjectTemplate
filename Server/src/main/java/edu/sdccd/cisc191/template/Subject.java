package edu.sdccd.cisc191.template;

public class Subject {

    private String subjectName;
    private String subjectNumber;

    public Subject() {}

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setSubjectNumber(String subjectNumber) {
        this.subjectNumber = subjectNumber;
    }
    public String getSubject() {
        return subjectName + subjectNumber;
    }
}
