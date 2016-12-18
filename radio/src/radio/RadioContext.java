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
    public void idleButtonAction() {
        getTopState().idleButtonAction( this );
    }

    @Override
    public void radioButtonAction() {
        getTopState().radioButtonAction( this );
    }

    @Override
    public void alarmButtonAction() {
        getTopState().alarmButtonAction( this );
    }

    @Override
    public void alarmTimeReached() {
        getTopState().alarmTimeReached( this );
    }

    @Override
    public void stopAlarmButtonAction() {
        getTopState().stopAlarmButtonAction( this );
    }
}
