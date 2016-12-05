package statewalkertest;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public enum StateEnum implements State{

    NULL;

    @Override
    public State getNullState() {
        return NULL;
    }
    
}
