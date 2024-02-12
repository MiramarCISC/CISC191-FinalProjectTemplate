import java.util.*;
/** Program Description
 * 1) Console opens w/ options
 * 2) Can Set Schedule with subject names of HW
 * 3)
 *
 * @author Logan Fleming, Theo Dela Cruz, Willy Do, Simon Nguyen
 * @version v1.0
 * @since 2/11/24
 */
public class Main {
    public static void main(String[] args) {
        char option;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("1. Run Setup");
        System.out.println("2. Update Subject");
        System.out.println("3 Add Assignment");
        System.out.println("4. Update Assignment\n");

        System.out.print("Choose an option, 1, 2, 3, or 4: ");
        option = keyboard.nextChar();

        switch (option) {
            /*
            Run Setup:
                Set Subject array
                Add initial assignments
            */
            case '1':
                int amountOfSubjects = 0;
                String nameOfSubject;
                boolean weighted;
                double grade;
                System.out.print("Enter the amount of classes you are in: ");
                amountOfSubjects = keyboard.nextInt();
                ArrayList<Subject> subjectArray = new ArrayList<Subject>(amountOfSubjects);
                for (int i = 0; i < amountOfSubjects; i++) {
                    System.out.println("Enter the name of period " + (amountOfSubjects + 1) + ":");
                    nameOfSubject = keyboard.nextLine();
                    System.out.println("Is the class weighted?");
                    weighted = keyboard.nextBoolean();
                    System.out.println("What is your current grade in the class?");
                    grade = keyboard.nextDouble();
                    Subject temp = new Subject(nameOfSubject, weighted, grade);
                    subjectArray.add(temp);
                }

            // Update/Delete Subject names in array list
            case '2':
                System.out.println("1. Update Subject");
                System.out.println("2. Remove Subject");


            // Add Assignment
            case '3':

            // Update Current Assignments
            case '4':

            // ur mom
            default:

        }
    }
}
