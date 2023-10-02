package edu.sdccd.cisc191.template;

public class Time {
    private int minutes;
    private int secondsTotal;

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
    public int getMinutes() {
        return minutes;
    }

    /** Converts user input's time in minutes to seconds
     * @param minutes user's time in minutes
     */
    public void setSecondsTotal(int minutes) { secondsTotal = minutes * 60; }
    /** @return user's minutes converted to seconds total
     */
    public int getSecondsTotal() {
        return secondsTotal;
    }
}