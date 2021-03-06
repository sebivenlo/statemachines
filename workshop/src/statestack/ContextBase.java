package statestack;

/**
 * Primary functions for a state context<br>
 *
 * Please do not forget to pass the device in the constructor
 *
 * @author Sander
 * @param <S> Implementation of the SuperState
 * @param <D> Implementation of the Device
 */
public class ContextBase<S extends StateBase, D extends DeviceBase> {

    protected StateStack<S> stateStack = new StateStack<>(5);
    protected D device;

    public ContextBase(D device) {
        this.device = device;
    }

    final public void enterState(S... state) {
        addState(state);
        //System.out.println( "after enter logical state = " + logicalState() );
    }

    final public void addState(S... state) {
        for (S cCState : state) {
            stateStack.push(cCState);
            cCState.enter(this);
        }
    }
    
    final public void leaveSubStates(S state){
        if (!stateStack.has(state)) {
            throw new IllegalArgumentException("Cannor leave state '" + state
                    + "'because I am not in it "
            );
        }
        S topState;
        while ((topState = stateStack.peek()) != state) {
            stateStack.pop();
            topState.exit(this);
                        //System.out.println( "leaving " + topState );
            //stateStack.pop();
        }
        
    }

    final public void leaveState(S state) {
        if (!stateStack.has(state)) {
            throw new IllegalArgumentException("Cannor leave state '" + state
                    + "'because I am not in it "
            );
        }
        S topState;
        while ((topState = stateStack.pop()) != state) {
            topState.exit(this);
            //            System.out.println( "leaving " + topState );
        }
        topState.exit(this);
    }

    public D getDevice() {
        return device;
    }

    public S superState(S state) {
        return stateStack.peekDownFrom(state, 1);
    }

    public void changeFromToState(String event, S start, S... endState) {
        String oldState = logicalState();
        leaveState(start);
        enterState(endState);
        System.out.println("from logical state " + oldState + ", event '"
                + event + "' to logical state "
                + logicalState());
    }
    
    public void innerTransition(String event, S start, S... endState) {
        String oldState = logicalState();
        leaveSubStates(start);
        enterState(endState);
        System.out.println("from logical state " + oldState + ", event '"
                + event + "' to logical state "
                + logicalState());
    }

    public String logicalState() {
        return stateStack.logicalState();
    }
}
