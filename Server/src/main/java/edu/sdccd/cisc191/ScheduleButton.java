package edu.sdccd.cisc191;

import javafx.scene.control.Button;

//class use to set up all the buttons that will represent the different classes in the timetable
public class ScheduleButton extends Button{

    private int row;
    private int col;
    private boolean showingInfo;

    public ScheduleButton(int row, int col)
    {
        this.row = row;
        this.col = col;
        showingInfo = false;
        setPrefHeight(100);
        setPrefWidth(100);
        // use exception catch clause to avoid null exception error
        try
        {
            setWrapText(true);
            setText(Schedule.timeTable[row][col].getSubject());
        }
        catch (Exception e)
        {
            setText("Empty for now!");
        }
    }

    // method to switch between 2 states, simple and informational
    public void handleClick()
    {
        if(showingInfo)
        {
            try
            {
                setWrapText(true);
                setText(Schedule.timeTable[row][col].getSubject());
                showingInfo = false;
            } catch (Exception e)
            {
                setText("No class here!");
            }
        }
        else
        {
            try
            {
                setWrapText(true);
                setText(Schedule.timeTable[row][col].getClassName() + "\n"
                        + Schedule.timeTable[row][col].getTeachersName() + "\n"
                        + Schedule.timeTable[row][col].getBuilding() + "-"
                        + Schedule.timeTable[row][col].getRoomNumber());
                showingInfo = true;
            }
            catch (Exception e)
            {
                setText("No class here!");
            }

        }
    }
}