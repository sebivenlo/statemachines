package statewalker;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 * @param <C> Context
 * @param <D> Device
 * @param <S> State
 */
public class ContextBase<C extends ContextBase<C, D, S>, D extends Device<C, D, S>, S extends StateBase<C, D, S>> {

    StateStack<S> stack;
    protected StateStack<S> stateStack = new StateStack<>( 5 );
    protected D device;

    public ContextBase( D device ) {
        this.device = device;
    }

    public ContextBase() {
    }

    public ContextBase setDevice( D device ) {
        this.device = device;
        return this;
    }

    @SuppressWarnings( "unchecked" )
    final public void enterState( S... state ) {
        addState( state );
        //System.out.println( "after enter logical state = " + logicalState() );
    }

    @SuppressWarnings( "unchecked" )
    final public void addState( S... state ) {
        for ( S cCState : state ) {
            stateStack.push( cCState );
            cCState.enter( ( C ) this );
        }
    }

    @SuppressWarnings( "unchecked" )
    final public void leaveSubStates( S state ) {
        if ( !stateStack.has( state ) ) {
            throw new IllegalArgumentException( "Cannor leave state '" + state
                    + "'because I am not in it "
            );
        }
        S topState;
        while ( ( topState = stateStack.peek() ) != state ) {
            stateStack.pop();
            topState.exit( ( C ) this );
            //System.out.println( "leaving " + topState );
            //stateStack.pop();
        }

    }

    @SuppressWarnings( "unchecked" )
    final public void leaveState( S state ) {
        if ( !stateStack.has( state ) ) {
            throw new IllegalArgumentException( "Cannor leave state '" + state
                    + "'because I am not in it "
            );
        }
        S topState;
        while ( ( topState = stateStack.pop() ) != state ) {
            topState.exit( ( C ) this );
            //            System.out.println( "leaving " + topState );
        }
        topState.exit( ( C ) this );
    }

    public D getDevice() {
        return device;
    }

    public S superState( S state ) {
        return stateStack.peekDownFrom( state, 1 );
    }

    @SuppressWarnings( "unchecked" )
    public void changeFromToState( String event, S start, S... endState ) {
        String oldState = logicalState();
        leaveState( start );
        enterState( endState );
        System.out.println( "from logical state " + oldState + ", event '"
                + event + "' to logical state "
                + logicalState() );
    }

    @SuppressWarnings( "unchecked" )
    public void innerTransition( String event, S start, S... endState ) {
        String oldState = logicalState();
        leaveSubStates( start );
        enterState( endState );
        System.out.println( "from logical state " + oldState + ", event '"
                + event + "' to logical state "
                + logicalState() );
    }

    public String logicalState() {
        return stateStack.logicalState();
    }

}
