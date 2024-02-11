package edu.sdccd.cisc191.template;
import java.util.*;

/**
 * The RandomEvent Class
 */
public class RandomEvent {
    private final Random rand = new Random();

    /**
     * Asks user a random event
     */
    public void askRandomEvent() {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("A random event has popped up!");
        System.out.println("If you clear this then you get rewards!");
        System.out.println("But if you fail, then you will get punished...");
        System.out.println("Will you take the chance? (Y/N)");

        if (keyboard.nextLine().equalsIgnoreCase("Y")) {
            generateRandomEvent();
        }
        else {
            System.out.println("Ah, feeling unlucky today, I see.");
        }
    } //end askRandomEvent()

    /**
     * random # generator to select random event
     */
    public void generateRandomEvent() {

        int numOfEvents = 2; // Change this value to how many events there are
        String[] events = new String[numOfEvents]; //Create an array according to how many events there are

        //assign games
        events[0] = "TicTacToe";
        events[1] = "TicTacToe";

        //switch to decide what game to play
        String eventName = events[rand.nextInt(numOfEvents)];
        switch (eventName) {
            case "TicTacToe":
                playTicTacToe();
                break;
            case "TestGame2":
                playTicTacToe();
                break;
            default:
                System.out.println("ur code brokee");
                break;
        }
    } //end generateRandomEvent()

    /**
     * TicTacToe Game
     */
    public void playTicTacToe() {
        TicTacToe game = new TicTacToe();
        game.drawBoard();
        game.beginGame();
    } //end playTicTacToe()

    /**
     * TestGame2 Game
     */
    public void playTestGame2() {
        System.out.println("TestGame2"); // temp code
    } //end playTestGame2()
}
