package statewalker;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.Map;
=======
import java.util.ArrayList;
import java.util.logging.Logger;
>>>>>>> ac5ae922a2d56f8b9f7b4bfaad5d156ff66a25d4

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 * @param <C> Context for this state machine. This
 * @param <D> Device for all operations
 * @param <S> State to maintain.
 */
public abstract class ContextBase<C extends ContextBase<C, D, S>, D extends Device<C, D, S>, S extends StateBase<C, D, S>> {

<<<<<<< HEAD
    StateStack<S> stack;
    protected StateStack<S> stateStack = new StateStack<>( 5 );
    protected D device;
    protected Map<S, S> history = new HashMap<>();
=======
    private final StateStack<S> stack = new StateStack<>( 6 );
    //private D device;
    private ArrayList<S> initialMap;
    private final S nullState;
    private boolean debug = false;
    private static final Logger LOGGER = Logger.getLogger( ContextBase.class.getCanonicalName() );
>>>>>>> ac5ae922a2d56f8b9f7b4bfaad5d156ff66a25d4

    @SuppressWarnings( "unchecked" )
    public ContextBase( Class<?> stateClass ) {
        //this();
        //this.device = device;
        if ( stateClass.isEnum() ) {
            Object[] enums = stateClass.getEnumConstants();
            this.initialMap = new ArrayList<>( enums.length );
            for ( Object aEnum : enums ) {
                this.initialMap.add( ( ( S ) aEnum ).getInitialState() );
            }
            nullState = ( ( S ) enums[ 0 ] ).getNullState();
            this.stack.push( nullState );
        } else {
            nullState = null;
        }
        if ( null != nullState ) {
            final S initialState = nullState.getInitialState();
            if ( initialState != null ) {
                this.enterState( initialState );
            }
        }
    }

//    public final ContextBase setDevice( D device ) {
//        this.device = device;
//        return this;
//    }

    @SafeVarargs
    public final void enterState( S... state ) {
        for ( S s : state ) {

            addState( s );
            S substate = initialMap.get( s.ordinal() );
            if ( null != substate ) {
                addState( substate );
            }
        }
    }

    @SafeVarargs
    @SuppressWarnings( "unchecked" )
<<<<<<< HEAD
    final public void addState( S... state ) {
        for ( S cCState : state ) {
            S stackTop = stateStack.peek();
            history.put(stackTop, cCState);
            stateStack.push( cCState );
            cCState.enter( ( C ) this );
=======
    public final void addState( S... state ) {
        for ( S childState : state ) {
            S parent = stack.peek();
            int parentId = parent.ordinal();
            stack.push( childState );
            childState.enter( ( C ) this );
            if ( parent.isInitialStateHistory() ) {
                this.initialMap.set( parentId, childState );
            }

>>>>>>> ac5ae922a2d56f8b9f7b4bfaad5d156ff66a25d4
        }
    }

    /**
     * Top state (child-most) state is the place where to enter the events.
     *
     * @return the top most (inner most/sub state most) state.
     */
    protected final S getTopState() {
        return stack.peek();
    }

    /**
     * Leave sub states of state, but not state itself.
     *
     * @param state for which the current sub-states should be left.
     */
    @SuppressWarnings( "unchecked" )
    public final void leaveSubStates( S state ) {
        if ( !stack.has( state ) ) {
            throw new IllegalArgumentException( "Cannor leave state '" + state
                    + "'because it is not active" );
        }
        S topState;
        while ( ( topState = stack.peek() ) != state ) {
            leaveAndPop();
        }
    }

    /**
     * Leave a state and all its sub-states in natural order.
     *
     * @param state to leave.
     */
    @SuppressWarnings( "unchecked" )
    public final void leaveState( S state ) {
        leaveSubStates( state );
        leaveAndPop();
    }

    @SuppressWarnings( "unchecked" )
    private void leaveAndPop() {
        stack.peek().exit( ( C ) this );
        stack.pop();
    }

    /**
     * Get the device for all operations.
     *
     * @return the device
     */
    public abstract D getDevice();

    /**
     * Get the super state of a state.
     *
     * @param state for which the super state should be retrieved.
     * @return the super (parent) state of state.
     */
    public final S superState( S state ) {
        return stack.peekDownFrom( state, 1 );
    }

    /**
     * Do a full transition from a current state to a new state with optional
     * sub-states. For the start state the leave method is invoked, for each
     * state in endState the enter state is invoked.
     *
     * @param event name for the transition
     * @param start state to leave
     * @param endState states to enter in order given.
     */
    @SafeVarargs
    public final void changeFromToState( String event, S start, S... endState ) {
        String oldState = logicalState();
        leaveState( start );
        enterState( endState );
        if ( debug ) {
            System.out.println( "from " + oldState + ", event '"
                    + event + "' to "
                    + logicalState() );
        }
    }

    /**
     * Do a transition with out leaving this state. The sub states of state are
     * left, then the endStates are entered in the order given.
     *
     * @param event name for the transition
     * @param start state that is NOT left
     * @param endState new inner state.
     */
    @SafeVarargs
    public final void innerTransition( String event, S start, S... endState ) {
        String oldState = logicalState();
        leaveSubStates( start );
        enterState( endState );
        if ( debug ) {
            System.out.println( "from " + oldState + ", event '"
                    + event + "' to "
                    + logicalState() );
        }
    }
    
    public S getHistoryStateFrom(S from, S initial){
        if(!history.containsKey(from)){
            return initial;
        }
        return history.get(from);
    }

    /**
     * Produce a string to identify the sequence or nesting of states. The NULL
     * state is left out.
     *
     * @return a string describing the full state value of this context.
     */
    public final String logicalState() {
        return stack.logicalState();
    }

    /**
     * Get the first active sub state of this parent or super state.
     *
     * @param parent for which the child must be produced.
     * @return The child, if any.
     */
    public S getFirstChild( S parent ) {
        return stack.peekDownFrom( parent, -1 );
    }

    public boolean isDebug() {
        return debug;
    }

    @SuppressWarnings( "unchecked" )
    public C setDebug( boolean d ) {
        debug = d;
        return ( C ) this;
    }

}
