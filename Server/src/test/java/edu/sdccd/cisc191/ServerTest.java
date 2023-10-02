package edu.sdccd.cisc191;


import edu.sdccd.cisc191.template.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServerTest {
    @Test
    void testQuoteArray() {
        //test if quotes are recognized in array
        Quote quote = new Quote();
        String quoteLine = quote.getSecondQuote();
        assertEquals(quoteLine, "DO YOUR HOMEWORK NOW NOW NOW");
    }
    @Test
    void test2DSnackArray() {
        //test if dessert is able to maneuver between rows of 2d array - getFirst() method calls from row 0 and row 1
        Dessert dessert = new Dessert();
        String dessertLine = dessert.getFirst();
        assertEquals(dessertLine, "Have some Nutella on White Bread");
    }
    @Test
    void testGUI() {
        //Can the app launch?
    }
    @Test
    void testTimeConversion() {
        //tests functionality of conversion methods in class Time
        Time time = new Time();
        time.setSecondsTotal(1);
        assertEquals(time.getSecondsTotal(), 60);
    }
    @Test
    void testAbstractClass() {
        //test if sandwich recognizes method built off of abstract parent - getDrink() is built off abstract class Snack
        Sandwich sandwich = new Sandwich();
        String sandwichLine = sandwich.getFirst();
        assertEquals(sandwichLine, "Make a sandwich with: White Bread, Salami\nand Cream Cheese");
    }
    @Test
    void testInterfaceUrgent() {
        //test if homework reacts as if it implements interface Urgent's property
        Homework homework = new Homework("","", true, "");
        assertEquals("*\n", homework.isUrgent());
    }
}