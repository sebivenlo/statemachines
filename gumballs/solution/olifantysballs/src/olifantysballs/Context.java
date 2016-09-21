package olifantysballs;

import java.io.PrintStream;

/**
 * Context interface for the olifantys gum ball machine.
 * Abstract 'super' of the context object objects.
 * Java 8 style interface with default methods where relevant and meaningful.
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
interface Context {

    /**
     * Change the state of this context to a new state
     *
     * @param state new state to set
     */
    void setState( State state );

    /**
     * Change the state of this context to a new state.
     *
     * @return the current state
     */
    State getState();

    /**
     * Add balls.
     *
     * @param count of balls to add.
     *
     * @return this context implementation
     */
    Context refill( int count );

    /**
     * Give the coin back, if any.
     */
    default void ejectCoin() {
        this.getState().ejectCoin( this );
    }

    /**
     * Insert a new coin.
     */
    default void insertCoin() {
        this.getState().insertCoin( this );
    }

    /**
     * Count your balls left.
     *
     * @return the ball count.
     */
    int getBallCount();

    /**
     * Check if the customer is lucky and you can dispense another ball.
     *
     * @return true iff another ball can and should be dispensed.
     */
    boolean isWinner();

    /**
     * Give the customer one ball. This method should use the output.
     */
    void dispense();

    /**
     * Get the print stream.
     *
     * @return the print stream defaults to system out.
     */
    default PrintStream getOutput() {
        return System.out;
    }

    /**
     * Check if all balls are gone.
     *
     * @return true if ball count == 0, false otherwise.
     */
    default boolean isEmpty() {
        return 0 == getBallCount();
    }

    /**
     * Change state, executing exit and entry methods on the go.
     *
     * @param newState next state
     *
     * @return the previous state.
     */
    default State changeState( StateEnum newState ) {
        State oldState = getState();
        oldState.exit( this );
        setState( newState );
        newState.enter( this );
        return oldState;
    }

    void addBalls( int count );

}
