package edu.sdccd.cisc191.template;

public class Player {
    private String playerName;
    private int health, strength, defense;

    /*
     * no-arg constructor
     */
    public Player() {
        playerName = "Adventurer";
        health = 100;
        strength = 75;
        defense = 75;
    } //end no-arg constructor

    /*
     * Constructor
     * @param playerName take in player's name
     * @param health takes in player's health
     * @param strength takes in player's strength stat
     * @param defense takes in player's defense stat
     */
    public Player(String playerName, int health, int strength, int defense) {
        this.playerName = playerName;
        this.health = health;
        this.strength = strength;
        this.defense = defense;
    } //end constructor

    /*
     * sets player name
     * @param playerName takes in player's name
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    } //end setPlayerName()

    /*
     * gets player name
     * @return player's name
     */
    public String getPlayerName() {
        return playerName;
    } //end getPlayerName()

    /*
     * sets player's health
     * @param health takes in player's health
     */
    public void setHealth(int health) {
        this.health = health;
    } //end setHealth()

    /*
     * gets player's health
     * @return player's health
     */
    public int getHealth() {
        return health;
    } //end getHealth()

    /*
     * sets player's strength stat
     * @param strength takes in player's strength stat
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    /*
     * gets player's strength stat
     * @return player's strength stat
     */
    public int getStrength() {
        return strength;
    } //end getStrength()

    /*
     * sets player's defense stat
     * @param defense takes in player's defense stat
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /*
     * gets player's defense stat
     * @return player's defense stat
     */
    public int getDefense() {
        return defense;
    } //end getDefense()

    /*
     * Tells current stats of player
     * @return player data
     */
    public String getStats() {
        return "These are your current stats: " + getHealth() + " health, " +
                getStrength() + " strength, " + getDefense() + " defense.";
    }
}
