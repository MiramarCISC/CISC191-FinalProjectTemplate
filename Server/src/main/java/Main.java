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
    Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {
        char option;
        System.out.println("1. Run Setup");
        System.out.println("2. Update Subject");
        System.out.println("3 Add Assignment");
        System.out.println("4. Update Assignment\n");
        //create while pit here
        System.out.print("Choose an option, 1, 2, 3, or 4: ");
        option = keyboard.nextChar();
        ArrayList<Subject> subjectArray = new ArrayList<Subject>();
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
                subjectArray = new ArrayList<Subject>(amountOfSubjects);
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
                break;
            // Update/Delete Subject names in array list
            case '2':
                int subjectOption;
                System.out.println("1. Update Subject");
                System.out.println("2. Remove Subject");
                //while pit
                System.out.print("Pick an option, 1 or 2: ");
                subjectOption = keyboard.nextInt();
                switch (subjectOption) {
                    //Update Subject
                    case 1:
                        break;

                    //Remove Subject
                    case 2:
                        int period = 0;
                        //while pit
                        System.out.println("Enter the period of the subject you want to remove: ");
                        period = keyboard.nextInt();
                        break;
                }
            // Add Assignment
            case '3':
                //wtf is going on here idk what im doing
                //while pit
                System.out.print("Enter the period of the subject you would like to add an assignment to : ");
                int period = keyboard.nextInt() - 1;

            // Update Current Assignments
            case '4':

            // ur mom
            default:

        }
    }
}
