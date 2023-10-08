package edu.sdccd.cisc191;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;

//class that will set up the GUI
public class ViewSchedule extends Application
{
    private ScheduleLabel topMessage;
    private GridPane scheduleGrid;
    private ScheduleLabel bottomMessage;
    private Canvas editCanvas;

    private int classNum = 1;
    private Schedule schedule;

    public static void main(String[] args)
    {
        launch(args);
    }


    private void updateCalendarButtons() {
        this.scheduleGrid.getChildren().removeAll();
        this.schedule.makeTimeTable();

        for (int row=0; row < 5; row++)
        {
            for (int col=0; col < 5; col++)
            {
                ScheduleButton button = new ScheduleButton(row, col);
                button.setOnAction(e ->
                {
                    button.handleClick();
                });
                this.scheduleGrid.add(button, col, row);
            }
        }
    }

    @Override
    public void start(Stage stage) throws Exception {

        //basic setting up process
        this.schedule = new Schedule();
        topMessage = new ScheduleLabel();
        scheduleGrid = new GridPane();
        bottomMessage = new ScheduleLabel();
        editCanvas = new Canvas();

        topMessage.setPadding(new Insets(20));
        bottomMessage.setPadding(new Insets(20));

        topMessage.setText("Hello, this is your schedule!");
        bottomMessage.setText("Use these buttons to edit your schedule");

        // Create the BorderPane, HBox, and VBox
        BorderPane root = new BorderPane();
        HBox header = new HBox(topMessage);
        HBox footer = new HBox(bottomMessage);
        HBox bottom = new HBox(editCanvas);

        // Add buttons onto the canvas
        // We want something the screen to look like this
        //      Hello, this is your schedule!
        // _______________________________________
        // | [Science][Math]   [Science][Math]   |
        // | []       [English][]       [English]|
        // |_____________________________________|

        //loop through a nested loop with the same dimensions as the timetable 2d array
        //for each element in the array, make a corresponding button.
        this.updateCalendarButtons();

        // Create the "Add Class" button
        Button addButton = new Button("Add Class");
        header.getChildren().add(addButton);

        // Handle the button click event
        addButton.setOnAction(e -> {
            // Create a new window or dialog for class input
            Stage addClassWindow = new Stage();
            addClassWindow.setTitle("Add Class");



            // Create input fields for class details
            TextField subjectTextField = new TextField();
            TextField classNameTextField = new TextField();
            TextField dayOfWeekTextField1 = new TextField();
            TextField dayOfWeekTextField2 = new TextField();
            TextField startDateTextField = new TextField();
            TextField endDateTextField = new TextField();
            TextField buildingTextField = new TextField();
            TextField roomNumberTextField = new TextField();
            TextField teacherTextField = new TextField();

            // Include a "Submit" button to confirm the class addition
            Button submitButton = new Button("Submit");

            // Set up the layout for the input fields and the submit button
            VBox inputLayout = new VBox(new Text("Enter Class Details:"),
                    new Label("Subject"),
                    subjectTextField,
                    new Label("Class Name"),
                    classNameTextField,
                    new Label("first day of the Week"),
                    dayOfWeekTextField1,
                    new Label("second day of the week"),
                    dayOfWeekTextField2,
                    new Label("Start Date"),
                    startDateTextField,
                    new Label("End Date"),
                    endDateTextField,
                    new Label("Building"),
                    buildingTextField,
                    new Label("Room Number"),
                    roomNumberTextField,
                    new Label("Teacher's Name"),
                    teacherTextField,
                    submitButton);
            inputLayout.setAlignment(Pos.CENTER);
            inputLayout.setSpacing(10);



            // Handle class addition when the submit button is clicked
            submitButton.setOnAction(event -> {
                // Collect class details from input fields
                String subject = subjectTextField.getText();
                String className = classNameTextField.getText();
                String dayOfWeek = dayOfWeekTextField1.getText();
                String teacherName = teacherTextField.getText();
                String location = buildingTextField.getText();
                String startDate = startDateTextField.getText();
                String endDate = endDateTextField.getText();
                String date2 = dayOfWeekTextField2.getText();

                // Create a new class instance based on user input
                Class newClass;



                if (subject.equalsIgnoreCase("Science")) {
                    newClass = new ScienceClass(subject, className, teacherName, location, 101,
                            startDate, endDate, dayOfWeek, date2,
                            "Lab Location", "Lab Date");
                } else if (subject.equalsIgnoreCase("Math")) {
                    newClass = new MathClass(subject, className, teacherName, location, 201,
                            startDate, endDate, dayOfWeek, date2,
                            "Material", "Calculator");
                } else if (subject.equalsIgnoreCase("English")) {
                    newClass = new EnglishClass(subject, className, teacherName, location, 301,
                            startDate, endDate, dayOfWeek, date2,
                            "Book");
                } else {
                    // Handle invalid input (e.g., show an error message)
                    System.out.println("Invalid subject input.");
                    return; // Exit the action event handler
                }

                if (newClass != null) {
                    // Add the new class to the schedule
                    schedule.addClass(classNum, newClass);
                    classNum++;

                    // Update the scheduleGrid (you need to implement this part)
                    updateCalendarButtons();
                    // Increment classNum for the next class

                    // Close the addClassWindow
                    addClassWindow.close();
                }
            });

            Scene addClassScene = new Scene(inputLayout, 300, 650); // Adjust width and height
            addClassWindow.setScene(addClassScene);
            addClassWindow.show();
        });

        // Set up the screen
        Scene scene = new Scene(root);
        stage.setTitle("Class Schedule");
        stage.setScene(scene);
        root.setTop(header);
        root.setCenter(scheduleGrid);
        root.setBottom(footer);
        root.setBottom(bottom);
        stage.show();
    }
}