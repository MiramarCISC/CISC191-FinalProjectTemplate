package edu.sdccd.cisc191.template;

import java.util.Random;

public class Dessert extends Snack{

    private String[][] snackArray = new String[][]{
            {"White Bread", "Brioche", "Cookie"},
            {"Nutella", "PBJ", "Honey", "Jam"}
    };
    private String[] drinkArray = new String[] {"Green Tea", "Black Tea", "Herbal Tea", "Coffee"};
    private Random random = new Random();
    private String snackDescription = new String();
    private String drinkDescription = new String();

    //get String with the randomly generated dessert ingredients

    /**
     * Randomizes a dessert based on ingredients listed in array above
     * @return a String containing description of a dessert and its toppings
     */
    @Override
    public String getSnack() {

        String dessert = snackArray[0][random.nextInt(3)];
        String topping = snackArray[1][random.nextInt(4)];
        snackDescription = "Have some " + topping + " on " + dessert;
        return snackDescription;
    }

    /**
     * Randomizes a drink suggestion
     * @return a String containing the description of a drink
     */
    @Override
    public String getDrink() {
        String drink = drinkArray[random.nextInt(4)];
        drinkDescription = "Enjoy it with some " + drink;
        return drinkDescription;
    }

    /**
     * For testing purposes: tests if program can maneuver between two rows of an array
     * @return a String containing the description of a dessert
     */
    @Override
    public String getFirst() {
        String dessert = snackArray[0][0];
        String topping = snackArray[1][0];
        snackDescription = "Have some " + topping + " on " + dessert;
        return snackDescription;
    }
}