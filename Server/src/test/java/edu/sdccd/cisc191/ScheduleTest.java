package edu.sdccd.cisc191;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleTest
{
    @Test
    void testSchedule()
    {
        //make different class objects
        ScienceClass sci = new ScienceClass("Physic", "Electricity and Magnetism",
                "Sadayoshi Okumoto", "S6", 103, "Aug 21", "May 30",
                "Monday", "Wednesday","S6-207", "Wednesday");
        MathClass mat = new MathClass("Algebra", "Intro to Linear Algebra",
                "Frances Hammock", "M", 207, "Aug 21", "May 30",
                "Tuesday", "Thursday","", "TI-84 Plus CE");
        EnglishClass eng = new EnglishClass("English", "Critical Thinking",
                "David McCullen", "I", 177, "Aug 21", "May 30",
                "Tuesday", "Thursday","The Toll");

        //make a schedule object
        Schedule exampleSchedule = new Schedule();
        exampleSchedule.addClass(1, sci);
        exampleSchedule.addClass(2, mat);
        exampleSchedule.addClass(3, eng);

        //use the makeTimeTable method to make a 2d array
        exampleSchedule.makeTimeTable();

        //test the ScienceClass object
        assertEquals("Physic", sci.getSubject());
        assertEquals("Electricity and Magnetism", sci.getClassName());
        assertEquals("Sadayoshi Okumoto", sci.getTeachersName());
        assertEquals("S6", sci.getBuilding());
        assertEquals(103, sci.getRoomNumber());
        assertEquals("Aug 21", sci.getFirstDay());
        assertEquals("May 30", sci.getLastDay());
        assertEquals("Monday", sci.getClassDate1());
        assertEquals("Wednesday", sci.getClassDate2());
        assertEquals("S6-207", sci.getLabLocation());
        assertEquals("Wednesday", sci.getLabDate());

        //test the MathClass object in the array list of Schedule object;
        //the array list should look like this
        //<sci, math, eng>
        assertEquals("Algebra", exampleSchedule.getClassList().get(1).getSubject());
        assertEquals("Intro to Linear Algebra", exampleSchedule.getClassList().get(1).getClassName());
        assertEquals("Frances Hammock", exampleSchedule.getClassList().get(1).getTeachersName());
        assertEquals("M", exampleSchedule.getClassList().get(1).getBuilding());
        assertEquals(207, exampleSchedule.getClassList().get(1).getRoomNumber());
        assertEquals("Aug 21", exampleSchedule.getClassList().get(1).getFirstDay());
        assertEquals("May 30", exampleSchedule.getClassList().get(1).getLastDay());
        assertEquals("Tuesday", exampleSchedule.getClassList().get(1).getClassDate1());
        assertEquals("Thursday", exampleSchedule.getClassList().get(1).getClassDate2());
        assertEquals("", exampleSchedule.getClassList().get(1).getMaterial());
        assertEquals("TI-84 Plus CE", exampleSchedule.getClassList().get(1).getCalculator());

        //test the EnglishClass object in the timetable 2d array
        //the EnglishClass should be at indexes [1][1] and [1][3] of the 2d array
        //the 2d array should look like this
        //[sci][math][sci][math][]
        //[]   [eng] []   [eng] []

        assertEquals("English", Schedule.timeTable[1][1].getSubject());
        assertEquals("Critical Thinking", Schedule.timeTable[1][1].getClassName());
        assertEquals("David McCullen", Schedule.timeTable[1][1].getTeachersName());
        assertEquals("I", Schedule.timeTable[1][1].getBuilding());
        assertEquals(177, Schedule.timeTable[1][1].getRoomNumber());
        assertEquals("Aug 21", Schedule.timeTable[1][3].getFirstDay());
        assertEquals("May 30", Schedule.timeTable[1][3].getLastDay());
        assertEquals("Tuesday", Schedule.timeTable[1][3].getClassDate1());
        assertEquals("Thursday", Schedule.timeTable[1][3].getClassDate2());
        assertEquals("The Toll", Schedule.timeTable[1][3].getBook());

        //test the inheritance
        assertEquals("", eng.getLabDate());
        assertEquals("", mat.getBook());
        assertEquals("", sci.getCalculator());
    }
}
