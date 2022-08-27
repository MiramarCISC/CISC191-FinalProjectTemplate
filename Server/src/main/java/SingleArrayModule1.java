import java.util.Scanner;

public class SingleArrayModule1 {
    public static void printMenu(String[] options) {
        for (String option : options) {
            System.out.println(option);
        }
        System.out.print("Choose an option: ");
    }

    public static void main(String args[]) {

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
            } catch (Exception ex){
                System.out.println("Please enter an integer value between 1 and " +
                                options.length);
                    scanner.next();
            }
        }

    }

    // Options
    private static void option1(Boolean[] studentAttendance) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < studentAttendance.length; i++) {
            System.out.println("Is student[" + i + "] here? (Y/N)");
                char yesOrNo;
                yesOrNo = scanner.next().charAt(0);
                Character.toLowerCase(yesOrNo);
                if (yesOrNo == 'y') {
                    studentAttendance[i] = true;
                } else if (yesOrNo == 'n') {
                    studentAttendance[i] = false;
                } else {
                    System.out.println("Invalid value for Student[" + i + "]");
                }
                }
            }



    private static void option2(Boolean[] studentAttendance) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input student's index number: ");
        int studentIndexNumber;
        studentIndexNumber = scanner.nextInt();
        System.out.println("Is this student here? (Y/N)");
        char yesOrNo;
        yesOrNo = scanner.next().charAt(0);
        Character.toLowerCase(yesOrNo);
        if (yesOrNo == 'y') {
            studentAttendance[studentIndexNumber] = true;
            System.out.println("Student[" + studentIndexNumber + "] " +
                    "marked as present.");
        } else if (yesOrNo == 'n') {
            studentAttendance[studentIndexNumber] = false;
            System.out.println("Student[" + studentIndexNumber + "] " +
                    "marked as absent.");
        }
    }
    private static void option3(Boolean[] studentAttendance) {
        for (int i = 0; i < studentAttendance.length; i++) {
            System.out.println("Student[" + i + "] attendance = " +
                    studentAttendance[i]);
        }
    }
}

