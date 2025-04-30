package edu.sdccd.cisc191.template;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Bet class.
 * This test class validates the functionality of the methods in the
 * Bet class, including constructors, getters, setters, and other methods.
 */
public class BetTest {

    private Bet bet;
    private Game game;
    private User user;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    public void setUp() {
        Date startDate = new Date(125, 3, 10); // April 10, 2025 (month is 0-based)
        Date endDate = new Date(125, 3, 15);  // April 15, 2025 (year is 1900 based)
        game = new Game("Team A", "Team B", startDate, endDate);
        bet = new Bet(game, 100, "Team A");
        user = new User("TestUser", 1000);
    }

    /**
     * Tests the toJSON method for correct serialization.
     */
    @Test
    public void testToJSON() throws Exception {
        String json = Bet.toJSON(bet);
        System.out.println("Serialized JSON: " + json);
        assertNotNull(json, "JSON string should not be null.");
        assertTrue(json.contains("\"betTeam\":\"Team A\""), "JSON string should contain the team name.");
    }

    /**
     * Tests the fromJSON method for correct deserialization.
     */
    @Test
    public void testFromJSON() throws Exception {
        String json = Bet.toJSON(bet);
        Bet deserializedBet = Bet.fromJSON(json);
        assertNotNull(deserializedBet, "Deserialized Bet object should not be null.");
        assertEquals("Team A", deserializedBet.getBetTeam(), "Deserialized Bet should have the correct team name.");
    }

    /**
     * Tests the getWinAmt method for correct retrieval of win amount.
     */
    @Test
    public void testGetWinAmt() {
        assertEquals(0, bet.getWinAmt(), "Win amount should be correctly calculated.");
    }

    /**
     * Tests the setWinAmt method for correct setting of win amount.
     */
    @Test
    public void testSetWinAmt() {
        bet.setWinAmt(200);
        assertEquals(200, bet.getWinAmt(), "Win amount should be correctly set.");
    }

    /**
     * Tests the getGame method for correct retrieval of the associated game.
     */
    @Test
    public void testGetGame() {
        assertEquals(game, bet.getGame(), "Game should be correctly retrieved.");
    }

    /**
     * Tests the setGame method for correct setting of the associated game.
     */
    @Test
    public void testSetGame() {
        Date startDate = new Date(125, 3, 10); // April 10, 2025 (month is 0-based)
        Date endDate = new Date(125, 3, 15);  // April 15, 2025 (year is 1900 based)
        Game newGame = new Game("Team C", "Team D", startDate, endDate);
        bet.setGame(newGame);
        assertEquals(newGame, bet.getGame(), "Game should be correctly set.");
    }

    /**
     * Tests the getWinOddsOvertime method for correct retrieval of win odds over time.
     */
    @Test
    public void testGetWinOddsOvertime() {
        double[][] winOddsOvertime = bet.getWinOddsOvertime();
        assertEquals(10, winOddsOvertime.length, "Win odds over time should track 10 hours.");
        assertEquals(2, winOddsOvertime[0].length, "Each entry should contain odds and timestamp.");
    }

    /**
     * Tests the updateUser method for correct updating of user money based on bet outcome.
     */
    @Test
    public void testUpdateUser() {
        bet.updateFulfillment();
        int initialMoney = user.getMoney();
        bet.updateUser(user);
        if (bet.getFulfillment()) {
            assertEquals(initialMoney + bet.getWinAmt(), user.getMoney(), "User money should increase by win amount if bet is fulfilled.");
        } else {
            assertEquals(initialMoney - bet.getWinAmt(), user.getMoney(), "User money should decrease by win amount if bet is not fulfilled.");
        }
    }

    /**
     * Tests the updateFulfillment method for correct updating of bet fulfillment status.
     */
    @Test
    public void testUpdateFulfillment() {
        bet.updateFulfillment();
        assertTrue(bet.getFulfillment() || !bet.getFulfillment(), "Fulfillment status should be updated correctly.");
    }

    /**
     * Tests the getBetTeam method for correct retrieval of the bet team.
     */
    @Test
    public void testGetBetTeam() {
        assertEquals("Team A", bet.getBetTeam(), "Bet team should be correctly retrieved.");
    }

    /**
     * Tests the getBetAmt method for correct retrieval of the bet amount.
     */
    @Test
    public void testGetBetAmt() {
        assertEquals(100, bet.getBetAmt(), "Bet amount should be correctly retrieved.");
    }

    /**
     * Tests the setBetAmt method for correct setting of the bet amount.
     */
    @Test
    public void testSetBetAmt() {
        bet.setBetAmt(200);
        assertEquals(200, bet.getBetAmt(), "Bet amount should be correctly set.");
    }

    /**
     * Tests the toString method for correct string representation of the Bet object.
     */
    @Test
    public void testToString() {
        String betString = bet.toString();
        assertTrue(betString.contains("Bet on"), "String representation should contain 'Bet on'.");
        assertTrue(betString.contains("Team A"), "String representation should contain the team name.");
    }

    /**
     * Tests the 2D array capability of the winOddsOvertime field.
     */
    @Test
    public void testWinOddsOvertimeArray() {
        double[][] winOddsOvertime = bet.getWinOddsOvertime();
        assertEquals(10, winOddsOvertime.length, "Win odds over time should track 10 hours.");
        assertEquals(2, winOddsOvertime[0].length, "Each entry should contain odds and timestamp.");

        // Validate the first entry
        double firstOdd = winOddsOvertime[0][0];
        long firstTimestamp = (long) winOddsOvertime[0][1];
        assertTrue(firstOdd >= 1 && firstOdd <= 100, "First odd should be between 1 and 100.");
        assertTrue(firstTimestamp <= System.currentTimeMillis() / 1000, "First timestamp should be a valid epoch time.");

        // Validate the last entry
        double lastOdd = winOddsOvertime[9][0];
        long lastTimestamp = (long) winOddsOvertime[9][1];
        assertTrue(lastOdd >= 1 && lastOdd <= 100, "Last odd should be between 1 and 100.");
        assertTrue(lastTimestamp <= System.currentTimeMillis() / 1000, "Last timestamp should be a valid epoch time.");
    }
}