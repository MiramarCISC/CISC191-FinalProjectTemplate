import java.util.Scanner;

public class SingleArrayModule1
{
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

    public static void main(String args[])
    {

        // Program starts by asking user for the number of students
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of students: ");
        int numOfStudents = scanner.nextInt();

        // Boolean array for studentAttendance
        // true means student is here, false means student is absent
        Boolean[] studentAttendance = new Boolean[numOfStudents];

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
                option = scanner.nextInt();
                switch (option)
                {
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

    /* option1 goes down the student list in numerical order
     * to take attendance
     */
    private static void option1(Boolean[] studentAttendance)
    {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < studentAttendance.length; i++)
        {
            System.out.println("Is student[" + i + "] here? (Y/N)");
                char yesOrNo;
                yesOrNo = scanner.next().charAt(0);

                /* toLowerCase method is used so the case is ignored
                 * whether the user inputs 'y' or 'Y'
                 */
                if (Character.toLowerCase(yesOrNo) == 'y')
                {
                    studentAttendance[i] = true;
                } else if (Character.toLowerCase(yesOrNo) == 'n')
                {
                    studentAttendance[i] = false;
                } else
                {
                    System.out.println("Invalid value for Student[" + i + "]");
                    /* index variable i is subtracted by 1 when the user doesn't input
                     * y or n, so they can retake attendance for the same student
                     * because the for loop increments by 1. (Took me a while to
                     * figure this out lol)
                     */
                    i -= 1;
                }
        }
    }

    /* option2 allows the user to edit a specific student's
     * attendance if it was marked incorrectly. For example,
     * if a student was marked absent when they were here,
     * the user could fix this and mark them as "here".
     */
    private static void option2(Boolean[] studentAttendance)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input student's index number: ");
        int studentIndexNumber;

        studentIndexNumber = scanner.nextInt();
        // try catch block for when user inputs invalid studentIndexNumber
        try
        {
            System.out.println("Is this student[" + studentIndexNumber +
                    "] here? (Y/N)");
            char yesOrNo;
            yesOrNo = scanner.next().charAt(0);

            if (Character.toLowerCase(yesOrNo) == 'y') {
                studentAttendance[studentIndexNumber] = true;
                System.out.println("Student[" + studentIndexNumber + "] " +
                        "marked as present.");
            } else if (Character.toLowerCase(yesOrNo) == 'n') {
                studentAttendance[studentIndexNumber] = false;
                System.out.println("Student[" + studentIndexNumber + "] " +
                        "marked as absent.");
            } else {
                System.out.println("Invalid input. Please enter 'Y' or 'N'");
            }
        } catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Student[" + studentIndexNumber + "] not found.\n" +
                    "Please enter a valid student index number.");
        }
    }

    /* option3 prints out the attendance of every student as
     * true or false. True being "here". False being "absent".
     * "Null" means attendance for that student has not been taken.
     */
    private static void option3(Boolean[] studentAttendance) {
        System.out.println("'true' means student is present.\n" +
                        "'false' means student is absent.\n" +
                        "'null' means attendance has not been taken " +
                        "for that student.");
        for (int i = 0; i < studentAttendance.length; i++) {
            System.out.println("Student[" + i + "] attendance = " +
                    studentAttendance[i]);
        }
    }
}

