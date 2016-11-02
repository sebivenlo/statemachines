package cruisecontrol;

import statestack.DeviceBase;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public class Device implements DeviceBase{
    boolean braking = false;

    boolean isBraking() {
        return this.braking;
    }

    void startEngine() {
        System.out.println( "starting engine" );
    }

    void stopEngine() {
        System.out.println( "stopping engine" );
    }
}
