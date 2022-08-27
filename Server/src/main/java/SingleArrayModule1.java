import java.util.Scanner;

public class SingleArrayModule1 {
    public static void printMenu(String[] options) {
        for (String option : options) {
            System.out.println(option);
        }
        System.out.print("Choose an option: ");
    }

    public static void main(String args[]) {
        // Boolean array for studentAttendance
        // true means student is here, false means student is absent
        Boolean[] studentAttendance = new Boolean[10];


        String[] options = {"1- take attendance",
                "2- mark student absent",
                "3- view student attendance",
                "4- Exit"
        };
        Scanner scanner = new Scanner(System.in);
        int option;
        while (true) {
            printMenu(options);
            option = scanner.nextInt();
        }

    }
}
