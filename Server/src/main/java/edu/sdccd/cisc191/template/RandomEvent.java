package edu.sdccd.cisc191.template;
import java.util.*;

/**
 * The RandomEvent Class
 */
public class RandomEvent {
    private Scanner keyboard = new Scanner(System.in);
    private Random rand = new Random();

    /**
     * Asks user a random event
     */
    public void askRandomEvent() {

        System.out.println("A random event has popped up!");
        System.out.println("If you clear this then you get rewards!");
        System.out.println("But if you fail then you will get punished...");
        System.out.println("Will you take the chance? (Y/N)");
        if (keyboard.nextLine().equalsIgnoreCase("Y")) {
            generateRandomEvent();
        }
        else {
            System.out.println("Ah, feeling unlucky today, I see.");
        }
    }

    /**
     * random # generator to select random event
     */
    public void generateRandomEvent() {

        int numOfEvents = 2; // Change this value to how many events there are

        String eventName;
        String[] events = new String[numOfEvents];

        events[0] = "TicTacToe";
        events[1] = "TestGame2";

        eventName = events[rand.nextInt(numOfEvents)];

        switch (eventName) {
            case "TicTacToe":
                TicTacToe();
                break;
            case "TestGame2":
                //hi
                //gaming
                break;
            default:
                System.out.println("ur code brokee");
                break;
        }
    }

    /**
     * TicTacToe Game
     */
    public void TicTacToe() {
        System.out.println("TicTacToe"); // temp code
    }

    /**
     * TestGame2 Game
     */
    public void TestGame2() {
        System.out.println("TestGame2"); // temp code
    }
}
