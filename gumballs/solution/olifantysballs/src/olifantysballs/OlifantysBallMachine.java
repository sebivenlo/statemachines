package olifantysballs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Head First Design Patterns Gum ball machine alternative implementation.
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public class OlifantysBallMachine implements Context {

    State state = StateEnum.SoldOut;
    final List<OlifantysGumball> balls = new ArrayList<>();


    public OlifantysBallMachine() {
        state = StateEnum.SoldOut; // aka empty
    }

    public void draw() {
        state.draw( this );
    }

    @Override
    public void setState( State state ) {
        this.state = state;
    }

    @Override
    public int getBallCount() {
        return balls.size();
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public OlifantysBallMachine refill( int count ) {
        this.state.refill( this, count );
        return this;
    }

    public void addBalls(int count){
        this.balls.addAll( OlifantysGumball.newBalls( count ) );
        
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int count = getBallCount();
        result.append( "Olifantys Magic Ball Dispenser, Inc." )
                .append( "\nJava-enabled Standing Gumball Model #2016" )
                .append( "\nInventory: " )
                .append( count )
                .append( " gumball" )
                .append( ( count != 1 ) ? "s" : "" )
                .append( "\n" )
                .append( "Machine is " + state + "\n" );
        return result.toString();
    }

    @Override
    public void dispense() {
        OlifantysGumball ball = balls.remove( 0 );
        getOutput().println( ball );
    }

    final Random rnd = new Random();

    @Override
    public boolean isWinner() {
        return !isEmpty() && rnd.nextInt( 10 ) == 9;
    }
}
