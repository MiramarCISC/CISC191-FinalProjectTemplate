package edu.sdccd.cisc191.template;

import javafx.scene.control.Button;

public class TimerButton extends AppButton {
    private TimerButtonState state;
    public TimerButtonState getState() {
        return state;
    }

    public void setState(TimerButtonState newState) {
        state = newState;
    }
    public TimerButton() {
        state = TimerButtonState.NOTHING;
        this.setText("Timer");
    }
}
