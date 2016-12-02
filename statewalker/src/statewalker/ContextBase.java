package statewalker;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 * @param <C> Context for this state machine. This
 * @param <D> Device for all operations 
 * @param <S> State to maintain.
 */
public class ContextBase<C extends ContextBase<C, D, S>, D extends Device<C, D, S>, S extends StateBase<C, D, S>> {

    private StateStack<S> stack;
    private final  StateStack<S> stateStack = new StateStack<>( 5 );
    private D device;
    private Map<S,S> initialMap = Collections.<S,S>emptyMap();

    @SuppressWarnings("unchecked")
    public ContextBase( D device, Class<?> stateClass ) {
        this();
        this.device = device;
        if (stateClass.getClass().isEnum()) {
            Object[] enums = stateClass.getEnumConstants();
            //Class<? extends Enum> eClass= (Class<? extends Enum> )stateClass;
            EnumMap m = new EnumMap(stateClass);
            for ( Object aEnum : enums ) {
                m.put( (S)aEnum, (S)((StateBase)aEnum).getInitialState() );
            }
            this.initialMap= m;
        }
    }

    public ContextBase() {
        
    }

    public final ContextBase setDevice( D device ) {
        this.device = device;
        return this;
    }

    @SafeVarargs
    public final void enterState( S... state ) {
        addState( state );
        //System.out.println( "after enter logical state = " + logicalState() );
    }

    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final void addState( S... state ) {
        for ( S cCState : state ) {
            stateStack.push( cCState );
            cCState.enter( ( C ) this );
        }
    }

    @SuppressWarnings("unchecked")
    public final void leaveSubStates( S state ) {
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
    public final  void leaveState( S state ) {
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

    public final D getDevice() {
        return device;
    }

    public final S superState( S state ) {
        return stateStack.peekDownFrom( state, 1 );
    }

    @SafeVarargs
    public final void changeFromToState( String event, S start, S... endState ) {
        String oldState = logicalState();
        leaveState( start );
        enterState( endState );
        System.out.println( "from logical state " + oldState + ", event '"
                + event + "' to logical state "
                + logicalState() );
    }

    @SafeVarargs
    public final void innerTransition( String event, S start, S... endState ) {
        String oldState = logicalState();
        leaveSubStates( start );
        enterState( endState );
        System.out.println( "from logical state " + oldState + ", event '"
                + event + "' to logical state "
                + logicalState() );
    }

    public final String logicalState() {
        return stateStack.logicalState();
    }

}
