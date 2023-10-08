package edu.sdccd.cisc191;

import java.util.Scanner;

//class use to run the program
public class Server {

    public static void main(String[] args)
    {
        //start off by making a blank schedule array list
//        Schedule Schedule = new Schedule();
//        //boolean value to make a loop that the user can stop at any time
//        boolean creatingClasses = true;
//        //int value to determine which index of the array list to add the new class objects
//        int classNum = 1;
//        //int value to determine which class subclass object to make and what questions to ask
//        int classType = 0;
//        while (creatingClasses)
//        {
//            //create a scanner object to read the user's keyboard strokes
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("What class are you taking? Type 1 for science, 2 for math" +
//                    ", or 3 for english");
//            classType = scanner.nextInt();
//            //an autofill option to automatically set up all the classes, save time in the testing and debugging process
//            if (classType == 0)
//            {
//                autofill();
//                break;
//            }
//            scanner.nextLine();
//            System.out.println("Enter the subject");
//            String subject = scanner.nextLine();
//            System.out.println("Enter the class name");
//            String className = scanner.nextLine();
//            System.out.println("Enter the teacher's name");
//            String teachersName = scanner.nextLine();
//            System.out.println("Enter the building");
//            String building = scanner.nextLine();
//            System.out.println("Enter the room number");
//            int room = scanner.nextInt();
//            scanner.nextLine();
//            System.out.println("Enter the first day of class");
//            String firstDay = scanner.nextLine();
//            System.out.println("Enter the last day of class");
//            String lastDay = scanner.nextLine();
//            System.out.println("Enter the meeting date 1 (day of the week)");
//            String date1 = scanner.nextLine();
//            System.out.println("Enter the meeting date 2 (day of the week)");
//            String date2 = scanner.nextLine();
//
//            //switch clause that will change what questions will be asked and what object type to make
//            //change depending on the classType int variable
//            switch (classType)
//            {
//                case 1: System.out.println("Enter the lab location");
//                    String labLocation = scanner.nextLine();
//                    System.out.println("Enter the lab date");
//                    String labDate = scanner.nextLine();
//                    ScienceClass sci = new ScienceClass(subject, className, teachersName, building, room, firstDay,
//                            lastDay, date1, date2, labLocation, labDate);
//                    Schedule.addClass(classNum,sci);
//                    break;
//                case 2: System.out.println("Enter the math materials");
//                    String materials = scanner.nextLine();
//                    System.out.println("Enter the calculator type");
//                    String calculator = scanner.nextLine();
//                    MathClass mat = new MathClass(subject, className, teachersName, building, room, firstDay,
//                            lastDay, date1, date2, materials, calculator);
//                    Schedule.addClass(classNum,mat);
//                    break;
//                case 3: System.out.println("Enter the book");
//                    String book = scanner.nextLine();
//                    EnglishClass eng = new EnglishClass(subject, className, teachersName, building, room, firstDay,
//                            lastDay, date1, date2, book);
//                    Schedule.addClass(classNum,eng);
//                    break;
//            }
//
//            //ask the user to continue or exterminate the loop
//            System.out.println("Do you want to add another class? Enter Yes or No");
//
//            creatingClasses = scanner.nextLine().equals("Yes");
//
//            classNum++;
//
//        }
//
//
//        if (classType != 0)
//        {
//            Schedule.makeTimeTable();
//        }
//
        //run the class that make the GUI
        ViewSchedule.main(null);
    }

    //helpful automatic set up method
    public static void autofill()
    {
        //make different class objects
        ScienceClass sci = new ScienceClass("Physic", "Electricity and Magnetism",
                "Sadayoshi Okumoto", "S6", 103, "Aug 21", "May 30",
                "Monday", "Wednesday","S6-207", "Wednesday");
        MathClass mat = new MathClass("Algebra", "Intro to Linear Algebra",
                "Frances Hammock", "M", 207, "Aug 21", "May 30",
                "Tuesday", "Thursday","", "TI-84 Plus CE");
        EnglishClass eng = new EnglishClass("English", "Critical Thinking",
                "David McCullen", "I", 177, "Aug 21", "May 30",
                "Tuesday", "Thursday","The Toll");

        //make a schedule object
        Schedule exampleSchedule = new Schedule();
        exampleSchedule.addClass(1, sci);
        exampleSchedule.addClass(2, mat);
        exampleSchedule.addClass(3, eng);

        exampleSchedule.makeTimeTable();
    }
}
