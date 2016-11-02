package statestack;

import static statestack.StateEnum.*;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
class Context {
    Device device = new Device();
    private StateStack<CCState> stateStack = new StateStack<>( 5 );

    public Context() {
        stateStack.push( NULL );
        addState( IDLE );
    }

    final void leaveState( CCState state ) {
        if ( !stateStack.has( state ) ) {
            throw new IllegalArgumentException( "Cannor leave state '" + state
                                                        + "'because I am not in it "
            );
        }
        CCState topState;
        while ( ( topState = stateStack.pop() ) != state ) {
            topState.exit( this );
            //            System.out.println( "leaving " + topState );
            //stateStack.pop();
        }
        topState.exit( this );
        //System.out.println( "after leave logical state = " + logicalState() );
    }

    final void enterState( CCState... state ) {
        addState( state );
        //System.out.println( "after enter logical state = " + logicalState() );
    }

    final void addState( CCState... state ) {
        for ( CCState cCState : state ) {
            stateStack.push( cCState );
            cCState.enter( this );
        }
    }

    void changeFromToState( String event, CCState start, CCState... endState ) {
        String oldState = logicalState();
        leaveState( start );
        enterState( endState );
        System.out.println( "from logical state " + oldState + ", event '"
                                    + event + "' to logical state "
                                    + logicalState() );
    }

    CCState superState( CCState state ) {
        return stateStack.peekDownFrom( state, 1 );
    }

    void engineOn() {
        stateStack.peek().engineOn( this );
    }

    void accelerate() {
        stateStack.peek().accelerate( this );
    }

    void engineOff() {
        stateStack.peek().engineOff( this );
    }

    void cruise() {
        stateStack.peek().cruise( this );
    }

    void brake() {
        stateStack.peek().brakePressed( this );
    }

    String logicalState() {
        return stateStack.logicalState();
    }

    public Device getDevice() {
        return device;
    }

    
}
