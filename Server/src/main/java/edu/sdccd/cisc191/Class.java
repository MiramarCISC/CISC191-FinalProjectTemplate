package edu.sdccd.cisc191;

//create an abstract superclass as a base
public abstract class Class implements MathInfo, ScienceInfo, EnglishInfo
{
    private String subject;
    private String className;
    private String teachersName;
    private String building;
    private int roomNumber;
    private String firstDay;
    private String lastDay;

    private String classDate1;
    private String classDate2;

    public Class(String sub, String cla, String teacher, String location, int roomNum, String first,
                 String last, String date1, String date2)
    {
        subject = sub;
        className = cla;
        teachersName = teacher;
        building = location;
        roomNumber = roomNum;
        firstDay = first;
        lastDay = last;
        classDate1 = date1;
        classDate2 = date2;
    }

    //add some simple getter methods
    public String getSubject() {
        return subject;
    }

    public String getClassName() {
        return className;
    }

    public String getTeachersName() {
        return teachersName;
    }
    public String getBuilding() {
        return building;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getFirstDay() {
        return firstDay;
    }

    public String getLastDay() {
        return lastDay;
    }

    public String getClassDate1() {
        return classDate1;
    }

    public String getClassDate2() {
        return classDate2;
    }

    // this method would swap monday, wednesday classes into tuesday, thursday classes and vice versa
    public void swapDate()
    {
        if (classDate1.equals("Monday"))
        {
            classDate1 = "Tuesday";
            classDate2 = "Thursday";
        }
        else
        {
            classDate1 = "Monday";
            classDate2 = "Wednesday";
        }
    }
}