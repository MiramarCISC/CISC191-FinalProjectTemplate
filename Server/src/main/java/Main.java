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
    public static Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {
        String option;
        System.out.println("1. Run Setup");
        System.out.println("2. Update Subject");
        System.out.println("3. Add Assignment");
        System.out.println("4. Update Assignment Information");
        System.out.println("5. Remove Assignment");
        //create while pit here
        System.out.print("Choose an option, 1, 2, 3, or 4 or 'q' to quit ");
        option = keyboard.next();
        ArrayList<Subject> subjectArray = new ArrayList<Subject>();
        switch (option) {
            /*
            Run Setup:
                Set Subject array
                Add initial assignments
            */
            case "1":
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
            case "2":
                int subjectOption;
                System.out.println("1. Rename Subject");
                System.out.println("2. Remove Subject");
                //while pit
                System.out.print("Pick an option, 1 or 2: ");
                subjectOption = keyboard.nextInt();
                switch (subjectOption) {
                    //Update Subject
                    case 1:
                        int period = 0;
                        System.out.print("Enter the period of the subject you want to rename: ");
                        period = keyboard.nextInt() - 1;
                        System.out.print("\nEnter the new name of the subject: ");
                        String subjectName = keyboard.nextLine();
                        Subject subject = new Subject();
                        subject.setNameOfSubject(subjectName);
                        subjectArray.set(period, subject);
                        break;

                    //Remove Subject
                    case 2:
                        //while pit
                        System.out.println("Enter the period of the subject you want to remove: ");
                        int period1 = keyboard.nextInt() - 1;
                        subjectArray.remove(period1);
                        break;
                }
            // Add Assignment
            case "3":
                //wtf is going on here idk what im doing
                //while pit
                Assignment assignment = new Assignment();
                Arraylist<Assignment> assignmentArraylist = new Arraylist<Assignment>();

                System.out.print("Enter the period of the subject you would like to add an assignment to: ");
                int period = keyboard.nextInt() - 1;
                Subject subject = new Subject(subjectArray.get(period));

                System.out.println("\nEnter the name of the assignment: ");
                assignment.setNameOfAssignment(keyboard.nextLine());
                System.out.println("Add other information by using the update assignment option!");

                assignmentArrayList = subject.getAssignmentList();
                assignmentArrayList.add(assignment);
                subject.setAssignmentList(assignmentArrayList);

                subjectArray.set(period, subject);
                break;
            // Update Current Assignments
            case "4":
                ArrayList<Assignment> tempArrayList = new ArrayList<Assignment>();
                Subject subject2 = new Subject();

                System.out.print("Enter the period of the assignment you would like to update information for: ");
                int period2 = keyboard.nextInt() - 1;
                subject2 = subjectArray.get(period2);



            // Remove Assignment
            case "5":

                Arraylist<Assignment> assignmentArraylist1 = new Arraylist<Assignment>();

                Systen.out.print("Enter the period of the subject you would like to remove an assignment from: ");
                int period5 = keyboard.nextInt() - 1;

                Subject subject5 = subjectArray.get(period5);


                printAllAssignments(subject5);
                System.out.print("From the list above, enter the number correlating to the assignment you would like to remove:  ")
                int temp = keyboard.nextInt() - 1;



                assignmentArraylist = subject5.getAssignmentList1();
                assignmentArraylist.remove(temp);

                subjectArray.set(period5, subject5);

            default:

        }
    }
    public static int calculatePriority(Assignment o) {
        int counter = 0;
        int temp = o.getDaysUntilDueDate();
        counter += (10/temp)
    }
    public static void printAllAssignments(Subject o) {
        ArrayList<Assignment> tempArray = new ArrayList<Assignment>();
        tempArray = o.getAssignmentList();
        int size = tempArray.size();
        for (int i = 0; i < size; i++) {
            int a = i + 1;
            System.out.println(a + ". " + tempArray.get(i).getNameOfAssignment());
        }
    }
}
