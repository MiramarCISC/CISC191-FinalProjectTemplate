package edu.sdccd.cisc191.template;

import java.net.*;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This program is a server that takes connection requests on
 * the port specified by the constant LISTENING_PORT.  When a
 * connection is opened, the program sends the current time to
 * the connected socket.  The program will continue to receive
 * and process connections until it is killed (by a CONTROL-C,
 * for example).  Note that this server processes each connection
 * as it is received, rather than creating a separate thread
 * to process the connection.
 */
public class Server extends Application {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) throws Exception {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            CustomerRequest request = CustomerRequest.fromJSON(inputLine);
            CustomerResponse response = new CustomerResponse(request.getId(), "Jane", "Doe");
            out.println(CustomerResponse.toJSON(response));
        }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

    /**
     * Creates an interactive console menu for selecting options
     * by typing in a number
     */
    public static void printMenu(String[] options)
    {
        for (String option : options)
        {
            System.out.println(option);
        }
        System.out.print("Choose an option: ");
    }

    public static void main(String[] args) {

        Server server = new Server();


        try {
            server.start(4444);
            server.stop();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    // Options

    /* option1 should be the first option users choose. It goes
        through every student in the first column and asks
        whether they are here, late, or absent.
     */
    private static void option1(String[][] studentAttendance) {
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
    private static void option2(String[][] studentAttendance) {
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
    private static void option3(String[][] studentAttendance)
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
} //end class Server
