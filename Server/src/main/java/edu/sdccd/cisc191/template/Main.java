package edu.sdccd.cisc191.template;

import edu.sdccd.cisc191.template.Assignment;

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
        // TODO add q to quit as option when implementing javafx
        String option;
        System.out.println("1. Run Setup");
        System.out.println("2. Update edu.sdccd.cisc191.template.Subject");
        System.out.println("3. Remove edu.sdccd.cisc191.template.Subject");
        System.out.println("4. Add edu.sdccd.cisc191.template.Assignment");
        System.out.println("5. Update edu.sdccd.cisc191.template.Assignment");
        System.out.println("6. Remove edu.sdccd.cisc191.template.Assignment");
        //create while pit here
        System.out.print("Choose an option, 1, 2, 3, 4, 5, or 'q' to quit ");
        option = keyboard.next();
        ArrayList<Subject> subjectArray = new ArrayList<Subject>();
        switch (option) {
            /*
            Run Setup:
                Set edu.sdccd.cisc191.template.Subject array
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
            // Update/Delete edu.sdccd.cisc191.template.Subject names in array list
            case "2":
                int period = 0;
                System.out.print("Enter the period of the subject you want to update: ");
                period = keyboard.nextInt() - 1;
                System.out.print("\nEnter the new name of the subject: ");
                String subjectName = keyboard.nextLine();
                Subject subject = new Subject();
                subject.setNameOfSubject(subjectName);
                subjectArray.set(period, subject);
                break;

                //Remove edu.sdccd.cisc191.template.Subject
            case "3":
                //while pit
                System.out.println("Enter the period of the subject you want to remove: ");
                int period1 = keyboard.nextInt() - 1;
                subjectArray.remove(period1);
                break;

            // Add edu.sdccd.cisc191.template.Assignment
            case "4":
                //while pit
                Assignment assignment = new Assignment();
                ArrayList<Assignment> assignmentArrayList = new ArrayList<Assignment>();

                System.out.print("Enter the period of the subject you would like to add an assignment to: ");
                int period3 = keyboard.nextInt() - 1;
                Subject subject3 = new Subject(subjectArray.get(period3));

                System.out.print("\nEnter the name of the assignment: ");
                assignment.setNameOfAssignment(keyboard.nextLine());
                System.out.print("Enter the days until its due: ");
                assignment.setDaysUntilDueDate(keyboard.nextInt());
                System.out.print("Enter the points for the assignment: ");
                assignment.setPointsOfAssignment(keyboard.nextInt());
                //TODO while pit
                System.out.print("Is it busy work? y/n ");
                String input = keyboard.next();
                if (input == "y") {
                    assignment.setBusyWork(true);
                }
                else if (input == "n") {
                    assignment.setBusyWork(false);
                }

                assignmentArrayList = subject3.getAssignmentList();
                assignmentArrayList.add(assignment);
                subject3.setAssignmentList(assignmentArrayList);

                subjectArray.set(period3, subject3);
                break;
            // Update Current Assignments
            case "5":
                ArrayList<Assignment> tempArrayList = new ArrayList<Assignment>();
                Subject subject2 = new Subject();

                //ask user for subject
                System.out.print("Enter the period of the assignment you would like to update information for: ");
                int period2 = keyboard.nextInt() - 1;
                subject2 = subjectArray.get(period2);
                tempArrayList = subject2.getAssignmentList();

                //display list for user to pick from, then update info
                printAllAssignments(subject2);
                System.out.print("Enter the number of the assignment you would like to update: ");
                int index1 = keyboard.nextInt() - 1;
                Assignment assignment2 = new Assignment(tempArrayList.get(index1));
                System.out.print("\nEnter the name of the assignment: ");
                String temp = keyboard.nextLine();
                assignment2.setNameOfAssignment(temp);
                System.out.print("Enter the days until its due: ");
                assignment2.setDaysUntilDueDate(keyboard.nextInt());
                System.out.print("Enter the points for the assignment: ");
                assignment2.setPointsOfAssignment(keyboard.nextInt());
                //TODO while pit
                System.out.print("Is it busy work? y/n ");
                String input1 = keyboard.next();
                if (input1 == "y") {
                    assignment2.setBusyWork(true);
                }
                else if (input1 == "n") {
                    assignment2.setBusyWork(false);
                }
                tempArrayList.set(index1, assignment2);
                subject2.setAssignmentList(tempArrayList);
                subjectArray.set(period2, subject2);

            // Remove .Assignment
            case "6":

                ArrayList<Assignment> assignmentArraylist1 = new ArrayList<Assignment>();

                System.out.print("Enter the period of the subject you would like to remove an assignment from: ");
                int period5 = keyboard.nextInt() - 1;

                Subject subject5 = subjectArray.get(period5);


                printAllAssignments(subject5);
                System.out.print("From the list above, enter the number correlating to the assignment you would like to remove: ");
                int temp4 = keyboard.nextInt() - 1;



                assignmentArraylist1 = subject5.getAssignmentList();
                assignmentArraylist1.remove(temp4);

                subject5.setAssignmentList(assignmentArraylist1);

                subjectArray.set(period5, subject5);

            default:

        }
    }
    public static int calculatePriorityPoints(Assignment o) {
        int counter = 0;
        int temp = o.getDaysUntilDueDate();
        counter += (10/temp);
        temp = o.getPointsOfAssignment();
        counter += (2/temp);
        return counter;
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
