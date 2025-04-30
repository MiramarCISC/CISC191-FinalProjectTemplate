package edu.sdccd.cisc191.template;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the  User  class.
 * This test class validates the functionality of the methods in the
 * User class, including getters, setters, and methods
 * that manage bets and money attributes.
 *
 * @author Andy Ly
 */
class UserTest {

    private User user;

    /**
     * Sets up a test user before each test case.
     */
    @BeforeEach
    void setUp() {
        user = new User("John Doe", 1000);
    }

    /**
     * Tests the constructor to ensure a user is correctly initialized.
     */
    @Test
    void testConstructor() {
        assertEquals("John Doe", user.getName(), "The user's name should be 'John Doe'.");
        assertEquals(1000, user.getMoney(), "The user's initial money should be 1000.");
        assertEquals(0, user.getMoneyLine(), "The user's moneyLine should be initialized to 0.");
        assertEquals(1000, user.getMoneyBet(), "The user's moneyBet should match the initial money.");
        assertTrue(user.getBets().isEmpty(), "The user's bets list should be empty upon initialization.");
    }

    /**
     * Tests the setName and getName methods.
     */
    @Test
    void testSetAndGetName() {
        user.setName("Jane Doe");
        assertEquals("Jane Doe", user.getName(), "The user's name should be updated to 'Jane Doe'.");
    }

    /**
     * Tests the setMoney an  getMoney methods.
     */
    @Test
    void testSetAndGetMoney() {
        user.setMoney(500);
        assertEquals(500, user.getMoney(), "The user's total money should be updated to 500.");
    }

    /**
     * Tests the addBet method to ensure bets are added correctly and money attributes are updated.
     */
    @Test
    void testAddBet() {
        Game game = new Game("Team A", "Team B", new Date(), new Date());
        Bet bet = new Bet(game, 200, "Team A");

        user.addBet(bet);

        assertEquals(1, user.getBets().size(), "The user's bets list should contain one bet.");
        assertEquals(800, user.getMoneyBet(), "The user's moneyBet should be reduced by the bet amount.");
        assertEquals(200, user.getMoneyLine(), "The user's moneyLine should be increased by the bet amount.");
    }

    /**
     * Tests the removeBet method to ensure bets are removed correctly.
     */
    @Test
    void testRemoveBet() {
        Game game = new Game("Team A", "Team B", new Date(), new Date());
        Bet bet = new Bet(game, 200, "Team A");

        user.addBet(bet);
        user.removeBet(bet);

        assertTrue(user.getBets().isEmpty(), "The user's bets list should be empty after removing the bet.");
    }

    /**
     * Tests the getMoneyLine and getMoneyLine methods.
     */
    @Test
    void testSetAndGetMoneyLine() {
        user.setMoneyLine(300);
        assertEquals(300, user.getMoneyLine(), "The user's moneyLine should be updated to 300.");
    }

    /**
     * Tests the setMoneyBet and getMoneyBet methods.
     */
    @Test
    void testSetAndGetMoneyBet() {
        user.setMoneyBet(700);
        assertEquals(700, user.getMoneyBet(), "The user's moneyBet should be updated to 700.");
    }

    /**
     * Tests the checkBet method to verify if an active bet exists for a given game.
     */
    @Test
    void testCheckBet() {
        Game game1 = new Game("Team A", "Team B", new Date(), new Date());
        Game game2 = new Game("Team C", "Team D", new Date(), new Date());
        Bet bet = new Bet(game1, 200, "Team A");

        user.addBet(bet);

        assertTrue(user.checkBet(game1), "The user should have an active bet on 'Team A vs. Team B'.");
        assertFalse(user.checkBet(game2), "The user should not have an active bet on 'Team C vs. Team D'.");
    }

    /**
     * Tests the toJSON method to ensure the User object is serialized correctly.
     */
    @Test
    void testToJSON() throws Exception {
        String json = User.toJSON(user);

        assertNotNull(json, "The serialized JSON string should not be null.");
        assertTrue(json.contains("\"name\":\"John Doe\""), "The JSON should contain the user's name.");
        assertTrue(json.contains("\"money\":1000"), "The JSON should contain the user's money.");
    }

    /**
     * Tests the fromJSON method to ensure the  User  object is deserialized correctly.
     */
    @Test
    void testFromJSON() throws Exception {
        String json = """
        {
            "name": "John Doe",
            "money": 1000,
            "moneyLine": 0,
            "moneyBet": 1000,
            "bets": []
        }
        """;

        User deserializedUser = User.fromJSON(json);

        assertNotNull(deserializedUser, "The deserialized User object should not be null.");
        assertEquals("John Doe", deserializedUser.getName(), "The deserialized user's name should be 'John Doe'.");
        assertEquals(1000, deserializedUser.getMoney(), "The deserialized user's money should be 1000.");
        assertEquals(0, deserializedUser.getMoneyLine(), "The deserialized user's moneyLine should be 0.");
        assertEquals(1000, deserializedUser.getMoneyBet(), "The deserialized user's moneyBet should be 1000.");
        assertTrue(deserializedUser.getBets().isEmpty(), "The deserialized user's bets list should be empty.");
    }
}
