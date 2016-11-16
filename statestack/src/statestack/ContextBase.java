/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statestack;

/**
 * Primary functions for a state context<br>
 *
 * Please do not forget to pass the device in the constructor
 *
 * @author Sander
 * @param <C> Implementation of the SuperState
 * @param <D> Implementation of the Device
 */
public class ContextBase<C extends StateBase, D extends DeviceBase> {

    protected StateStack<C> stateStack = new StateStack<>(5);
    protected D device;

    public ContextBase(D device) {
        this.device = device;
    }

    final public void enterState(C... state) {
        addState(state);
        //System.out.println( "after enter logical state = " + logicalState() );
    }

    final public void addState(C... state) {
        for (C cCState : state) {
            stateStack.push(cCState);
            cCState.enter(this);
        }
    }
    
    final public void leaveSubStates(C state){
        if (!stateStack.has(state)) {
            throw new IllegalArgumentException("Cannor leave state '" + state
                    + "'because I am not in it "
            );
        }
        C topState;
        while ((topState = stateStack.peek()) != state) {
            stateStack.pop();
            topState.exit(this);
                        //System.out.println( "leaving " + topState );
            //stateStack.pop();
        }
        
    }

    final public void leaveState(C state) {
        if (!stateStack.has(state)) {
            throw new IllegalArgumentException("Cannor leave state '" + state
                    + "'because I am not in it "
            );
        }
        C topState;
        while ((topState = stateStack.pop()) != state) {
            topState.exit(this);
            //            System.out.println( "leaving " + topState );
        }
        topState.exit(this);
    }

    public D getDevice() {
        return device;
    }

    public C superState(C state) {
        return stateStack.peekDownFrom(state, 1);
    }

    public void changeFromToState(String event, C start, C... endState) {
        String oldState = logicalState();
        leaveState(start);
        enterState(endState);
        System.out.println("from logical state " + oldState + ", event '"
                + event + "' to logical state "
                + logicalState());
    }
    
    public void innerTransition(String event, C start, C... endState) {
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
