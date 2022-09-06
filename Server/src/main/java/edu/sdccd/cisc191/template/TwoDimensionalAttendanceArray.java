package edu.sdccd.cisc191.template;

import java.util.Scanner;

public class TwoDimensionalAttendanceArray
{
    public static void printMenu(String[] options)
    {
        for (String option : options)
        {
            System.out.println(option);
        }
        System.out.print("Choose an option: ");
    }

    public static void main(String[] args)
    {
        // Program starts by asking user for the number of students
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of students: ");
        int numOfStudents = scanner.nextInt();

        /* Declares and initializes a square 2D array that contains
           students' names and their attendance status.
         */
        String[][] studentAttendance = new String[numOfStudents][2];

        /* Ask each student's name
            and assigns it as a String value in
            column 0.
            firstColumn must always be 0, so it will have all the
            student's names in the leftmost column
         */
        final int firstColumn = 0;
        // nextLine() method is needed so it won't skip the first element
        scanner.nextLine();
        for (int row = 0; row < studentAttendance.length; row++)
        {
            System.out.print("Enter name for student [" + row + "]: ");
            studentAttendance[row][firstColumn] = scanner.nextLine();
        }

        // Options menus
        String[] options = {"1- take attendance",
                "2- edit student attendance",
                "3- view student attendance",
                "4- Exit"
        };

        // User selects one of the 4 options
        int option = 1;
        while (option != 4)
        {
            printMenu(options);
            try
            {
                // TODO: Make options for 2D Array
                option = scanner.nextInt();
                switch (option)
                {
                    case 1:
                        option1(studentAttendance);
                        break;
                    case 2:
                        option2(/*studentAttendance*/);
                        break;
                    case 3:
                        option3(studentAttendance);
                        break;
                    case 4:
                        System.exit(0);
                }
            } catch (Exception ex)
            {
                // For when the user tries to input a number that is not between 1 and 4
                System.out.println("Please enter an integer value between 1 and " +
                        options.length);
                scanner.next();
            }
        }

    }
    // Options
    private static void option1(String[][] studentAttendance) {
        for (int row = 0; row < studentAttendance.length; row++)
        {

        }
    }
    private static void option2() {
        System.out.println("Thanks for choosing option 2");
    }

    /* option3 prints out the names of the students
        and if they were here, absent, or late
     */
    private static void option3(String[][] studentAttendance)
    {
        System.out.println("Student Attendance List: ");
        for (int row = 0; row < studentAttendance.length; row++) {
            for (int column = 0; column < studentAttendance[row].length; column++) {
                System.out.print(studentAttendance[row][column] + " ");
            }
            System.out.println();
        }
    }
}



