package edu.sdccd.cisc191.template;
import java.util.*;

/**
 * The SystemMenu Class
 */
public class SystemMenu extends Player{
    Player adventurer = new Player();
    Scanner keyboard = new Scanner(System.in);

    /**
     * starts classCreation for the user
     */
    public void classCreation() {

        //player testing
        String[] classes = {"Warrior", "Tank", "Assassin"};
        System.out.println("To begin your journey, please select your class. " + Arrays.toString(classes));


        switch (keyboard.nextLine().toUpperCase()) {
            case "WARRIOR":
                adventurer = new Player("Warrior", 100, 90, 60);
                System.out.println("Welcome " + adventurer.getPlayerName() + ". " + adventurer);
                potionMenu();
                break;
            case "TANK":
                adventurer = new Player("Tank", 135, 30, 85);
                System.out.println("Welcome " + adventurer.getPlayerName() + ". " + adventurer);
                potionMenu();
                break;
            case "ASSASSIN":
                adventurer = new Player("Assassin", 80, 125, 45);
                System.out.println("Welcome " + adventurer.getPlayerName() + ". " + adventurer);
                potionMenu();
                break;
            default:
                System.out.println("Sorry, we did not recognize your class. Please try again.");
                classCreation();
                break;
        }
        nameCreation();
    } // end classCreation()

    public void nameCreation() {

        System.out.println("Is there a name you go by?");
        adventurer.setPlayerName(keyboard.nextLine());

        System.out.println("I see. I wish you the best of luck on your journey, " + adventurer.getPlayerName() + ".\n");

    } // end nameCreation()

    public void potionMenu() {

        String action;
        String type;
        String cont;

        System.out.println("Do you want to open the Potion menu? (Y/N)");
        cont = keyboard.nextLine();

        while (cont.equalsIgnoreCase("Y")) {

            System.out.println("What action do want to do to the potion?\n(Drink or Pour)");
            action = keyboard.nextLine();

            System.out.println("What type of potion?\n(Shield or Strength)");
            type = keyboard.nextLine();

            if (action.equalsIgnoreCase("Drink")) {
                switch (type.toUpperCase()) {
                    case "SHIELD":
                        System.out.println("You drank a shield potion! Increasing current health and defense...");
                        adventurer.addHealth(25);
                        adventurer.addDefense(25);
                        break;
                    case "STRENGTH":
                        System.out.println("You drank a strength potion! Increasing strength...");
                        adventurer.addStrength(25);
                        break;
                    default:
                        System.out.println("Invalid Potion Type.");
                        break;
                }
                System.out.println("Done! " + adventurer);
            } else if (action.equalsIgnoreCase("Pour")) {
                switch (type.toUpperCase()) {
                    case "SHIELD":
                        System.out.println("You poured a shield potion! A blue lily grew in its place.");
                        break;
                    case "STRENGTH":
                        System.out.println("You poured a strength potion! A thorny red rose grew in its place.");
                        break;
                    default:
                        System.out.println("Invalid Potion Type.");
                        break;
                }
            } else {
                System.out.println("Invalid Action");
                potionMenu();
            }

            System.out.println("Do you want to open the Potion menu again? (Y/N)");
            cont = keyboard.nextLine();
        }
    } // end potion()
} // end class
