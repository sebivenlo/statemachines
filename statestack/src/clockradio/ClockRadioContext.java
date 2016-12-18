package clockradio;

import static clockradio.ClockRadioStateEnum.*;
import statestack.ContextBase;

/**
 *
 * @author Jeroen (GitHub: jbeulen)
 */
public class ClockRadioContext extends ContextBase<ClockRadioContext, ClockRadioDevice, ClockRadioState> {

    public ClockRadioContext(ClockRadioDevice rdev) {
        super(rdev);
        stateStack.push(NULL);
        addState(POWERED);
        System.out.println("current state is: " + stateStack.peek().toString());
    }

    public void powerOn() {
        stateStack.peek().powerOn(this);
    }

    public void powerOff() {
        stateStack.peek().powerOff(this);
    }

    public void idleButtonPressed() {
        stateStack.peek().idleButtonPressed(this);
    }

    public void radioButtonPressed() {
        stateStack.peek().radioButtonPressed(this);
    }

    public void radioAlarmButtonPressed() {
        stateStack.peek().radioAlarmButtonPressed(this);
    }

    public void buzzerAlarmButtonPressed() {
        stateStack.peek().buzzerAlarmButtonPressed(this);
    }

    public void alarmButtonPressed() {
        stateStack.peek().alarmButtonPressed(this);
    }

    public void alarmButtonReleased() {
        stateStack.peek().alarmButtonReleased(this);
    }

    public void alarmTimeReached() {
        stateStack.peek().alarmTimeReached(this);
    }

    public void snoozeButtonPressed() {
        stateStack.peek().snoozeButtonPressed(this);
    }

    public void snoozeTimeout() {
        stateStack.peek().snoozeTimeout(this);
    }
}
