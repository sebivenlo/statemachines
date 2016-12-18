package clockradio;

import static clockradio.ClockRadioStateEnum.*;
import statestack.ContextBase;

/**
 *
 * @author Jeroen (GitHub: jbeulen)
 */
public class ClockRadioContext extends ContextBase<ClockRadioState, ClockRadioDevice> {

    public ClockRadioContext() {
        super(new ClockRadioDevice());
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

    public void alarmButtonPressed() {
        stateStack.peek().alarmButtonPressed(this);
    }
    
    public void stopAlarmButtonPressed() {
        stateStack.peek().stopAlarmButtonPressed(this);
    }
    
    public void alarmTimeReached() {
        stateStack.peek().alarmTimeReached(this);
    }

}
