package radio;

import statewalker.ContextBase;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public class RadioContext extends ContextBase<RadioContext, RadioDevice, RadioState> implements RadioOps {

    final RadioDevice rdev;
    RadioContext( RadioDevice rdev ) {
        super( RadioStateImpl.class );
        this.rdev= rdev;
    }

    @Override
    public RadioDevice getDevice() {
        return rdev;
    }

    
    public RadioContext(){
        this(new RadioDevice());
    }

    @Override
    public void powerOn() {
        getTopState().powerOn( this );
    }

    @Override
    public void powerOff() {
        getTopState().powerOff( this );
    }

    @Override
    public void idleButtonPressed() {
        getTopState().idleButtonPressed( this );
    }

    @Override
    public void radioButtonPressed() {
        getTopState().radioButtonPressed( this );
    }

    @Override
    public void alarmButtonPressed() {
        getTopState().alarmButtonPressed( this );
    }

    @Override
    public void alarmTimeReached() {
        getTopState().alarmTimeReached( this );
    }

    @Override
    public void stopAlarmButtonPressed() {
        getTopState().stopAlarmButtonPressed( this );
    }
}
