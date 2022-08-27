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
        int option = 1;
        while (option != 4) {
            printMenu(options);
            try {
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        option1();
                        break;
                    case 2:
                        option2();
                        break;
                    case 3:
                        option3();
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
    private static void option1() {
        System.out.println("Thanks for choosing option 1");
    }
    private static void option2() {
        System.out.println("Thanks for choosing option 2");
    }
    private static void option3() {
        System.out.println("Thanks for choosing option 3");
    }
}

