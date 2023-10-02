package edu.sdccd.cisc191.template;

import java.util.Random;

public class Quote {
    private String[] quoteArray = {"Sofia says hi.", "DO YOUR HOMEWORK NOW NOW NOW", "There are snakes on the plane.", "Teddy says hi."};
    private Random randomGenerator = new Random();

    /**
     * Randomize a quote out of four options in an array
     * @return a random String from an array
     */
    public String getQuote() {
        int randomNum = randomGenerator.nextInt(4);
        return quoteArray[randomNum];
    }

    /**
     * For testing purposes: tests to see if array is recognized
     * @return a String from index 1 of array above
     */
    public String getSecondQuote() {
        return quoteArray[1];
    }
}