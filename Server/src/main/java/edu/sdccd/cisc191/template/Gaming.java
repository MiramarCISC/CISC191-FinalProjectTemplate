package edu.sdccd.cisc191.template;
import java.util.*;

/**
 * This Gaming Class
 */
public class Gaming {
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        Player adventurer = new Player();
        RandomEvent randomEvent = new RandomEvent();

        //player testing
        String[] classes = {"Warrior", "Tank", "Assassin"};
        System.out.println("To begin your journey, please select your class. " + Arrays.toString(classes));

        switch (keyboard.nextLine().toUpperCase()) {
            case "WARRIOR":
                adventurer = new Player("Warrior", 100, 90, 60);
                System.out.println("Welcome " + adventurer.getPlayerName() + ". " + adventurer);
                System.out.println("You drank a strength potion! Increasing your strength...");
                adventurer.setStrength(115);
                System.out.println("Done! " + adventurer);
                break;
            case "TANK":
                adventurer = new Player("Tank", 135, 30, 85);
                System.out.println("Welcome " + adventurer.getPlayerName() + ". " + adventurer);
                System.out.println("You drank a shield potion! Increasing current health and defense...");
                adventurer.setHealth(160);
                adventurer.setDefense(110);
                System.out.println("Done! " + adventurer);
                break;
            case "ASSASSIN":
                adventurer = new Player("Assassin", 80, 125, 45);
                System.out.println("Welcome " + adventurer.getPlayerName() + ". " + adventurer);
                System.out.println("You drank a strength potion! Increasing strength...");
                adventurer.setStrength(150);
                System.out.println("Done! " + adventurer);
                break;
            default:
                System.out.println("Sorry, as we did not recognize your class, we will be using the default one.");
                System.out.println("Welcome " + adventurer.getPlayerName() + ". " + adventurer);
                break;
        }

        System.out.println("\nIs there a name you go by?");
        adventurer.setPlayerName(keyboard.nextLine());

        System.out.println("I see. I wish you the best of luck on your journey, " + adventurer.getPlayerName() + ".\n");

        //Random Event
        randomEvent.askRandomEvent();
    }
}
