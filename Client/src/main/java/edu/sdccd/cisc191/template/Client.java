package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;


import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This program opens a connection to a computer specified
 * as the first command-line argument.  If no command-line
 * argument is given, it prompts the user for a computer
 * to connect to.  The connection is made to
 * the port specified by LISTENING_PORT.  The program reads one
 * line of text from the connection and then closes the
 * connection.  It displays the text that it read on
 * standard output.  This program is meant to be used with
 * the server program, DateServer, which sends the current
 * date and time on the computer where the server is running.
 */

public class Client extends Application {
    private Button enter;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;






    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("sample.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        primaryStage.setTitle("Hello!");
        primaryStage.setScene(scene);
        primaryStage.show();



    }

    private String[][] setStudents(int rows) {

        String[][] students = new String[rows][2];
        return students;
    }

    public static void printMenu(String[] options)
    {
        for (String option : options)
        {
            System.out.println(option);
        }
        System.out.print("Choose an option: ");
    }


    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public CustomerResponse sendRequest() throws Exception {
        out.println(CustomerRequest.toJSON(new CustomerRequest(1)));
        return CustomerResponse.fromJSON(in.readLine());
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String[] args) {
        launch();

        // Program starts by asking user for the number of students
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of students: ");
        /* input must be an integer or else program will ask
            the user to enter valid input and then end
         */
        try {
            int numOfStudents = scanner.nextInt();

        /* Declares and initializes a square 2D array that contains
           students' names and their attendance status.
         */
            String[][] studentAttendance = new String[numOfStudents][2];

        /* Ask each student's name
            and assigns it as a String value in
            column 0.
         */
            // nextLine() method is needed, so it won't skip the first element
            scanner.nextLine();
            for (int row = 0; row < studentAttendance.length; row++) {
                System.out.print("Enter name for student [" + row + "]: ");
                studentAttendance[row][0] = scanner.nextLine();
            }

            // Options menus
            String[] options = {"1- take attendance",
                    "2- edit student attendance",
                    "3- view student attendance",
                    "4- Exit"
            };

            // User selects one of the 4 options
            int option = 1;
            while (option != 4) {
                printMenu(options);
                try {
                    option = scanner.nextInt();
                    switch (option) {
                        case 1:
                            option1(studentAttendance);
                            break;
                        case 2:
                            option2(studentAttendance);
                            break;
                        case 3:
                            option3(studentAttendance);
                            break;
                        case 4:
                            System.exit(0);
                    }
                } catch (Exception ex) {
                    // For when the user tries to input a number that is not between 1 and 4
                    System.out.println("Please enter an integer value between 1 and " +
                            options.length);
                    scanner.next();
                }
            }
        } catch (InputMismatchException ex)
        {
            System.out.println("Please enter an integer for the number of students");
        }

        // end
        Client client = new Client();
        try {
            client.startConnection("127.0.0.1", 4444);
            System.out.println(client.sendRequest().toString());
            client.stopConnection();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void option1(String[][] studentAttendance) {
        // creates Scanner for this method
        Scanner scanner = new Scanner(System.in);

        // Gives user instructions on how to take attendance
        System.out.println("Input 'h' if student is absent,\n" +
                "'l' if student is late, or\n" +
                "'a' if student is absent.");

        // creates a char value that will be the user's input
        char hereLateOrAbsent;
        /* for loop that will ask whether each student was
            here, late, or absent
         */
        for (int row = 0; row < studentAttendance.length; row++)
        {
            System.out.println("Is " + studentAttendance[row][0] +
                    " HERE, LATE, or ABSENT? (H/L/A)");
            hereLateOrAbsent = scanner.next().charAt(0);
            // toLowerCase so the case won't matter for user input
            if (Character.toLowerCase(hereLateOrAbsent) == 'h')
            {
                studentAttendance[row][1] = "HERE";
            } else if (Character.toLowerCase(hereLateOrAbsent) == 'l')
            {
                studentAttendance[row][1] = "LATE";
            } else if (Character.toLowerCase(hereLateOrAbsent) == 'a')
            {
                studentAttendance[row][1] = "ABSENT";
            } else
            {
                // Forces the user to enter correct input for the student
                System.out.println("Invalid input. Please input " +
                        "'h', 'l', or 'a'");
                row -= 1;
            }

        }
    }
    /* option2 lets user edit a student's attendance status
       if there were any mistakes for a specific student.
       For example, if a student came late, they could
       change their attendance from 'absent' to 'late'.
    */
    public static void option2(String[][] studentAttendance) {
        Scanner scanner = new Scanner(System.in);
        // Show index number of each student
        int studentIndex = 0;

        System.out.println("Student index numbers: ");
        for (int row = 0; row < studentAttendance.length; row++)
        {
            System.out.println(studentIndex + " - " +
                    studentAttendance[row][0]);
            studentIndex++;
        }

        System.out.println("Enter the index number of the student " +
                "whose attendance you would like to edit: ");
        studentIndex = scanner.nextInt();
        try
        {
            char hereLateOrAbsent;
            System.out.println("Is student[" + studentIndex +
                    "] - " + studentAttendance[studentIndex][0] +
                    " HERE, LATE, or ABSENT? (H/L/A)");
            hereLateOrAbsent = scanner.next().charAt(0);
            if (Character.toLowerCase(hereLateOrAbsent) == 'h')
            {
                studentAttendance[studentIndex][1] = "HERE";
                System.out.println(studentAttendance[studentIndex][0] +
                        " marked as HERE");
            } else if (Character.toLowerCase(hereLateOrAbsent) == 'l')
            {
                studentAttendance[studentIndex][1] = "LATE";
                System.out.println(studentAttendance[studentIndex][0] +
                        " marked as LATE");
            } else if (Character.toLowerCase(hereLateOrAbsent) == 'a')
            {
                studentAttendance[studentIndex][1] = "ABSENT";
                System.out.println(studentAttendance[studentIndex][0] +
                        " marked as ABSENT");
            } else
            {
                // Forces the user to enter correct input for the student
                System.out.println("Invalid input. Please input " +
                        "'h', 'l', or 'a'");
            }


        } catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Please enter a valid student index number");
        }


    }

    /* option3 prints out the names of the students
        and if they were here, absent, or late
     */
    public static void option3(String[][] studentAttendance)
    {
        System.out.println("'null' means attendance has not yet been taken " +
                "for that student.\n" +
                "Student Attendance List:");
        for (int row = 0; row < studentAttendance.length; row++) {
            for (int column = 0; column < studentAttendance[row].length; column++) {
                System.out.print(String.format("%20s", studentAttendance[row][column]));
            }
            System.out.println();
        }
    }

} //end class Client

