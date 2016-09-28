package statestack;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
class Device {
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
