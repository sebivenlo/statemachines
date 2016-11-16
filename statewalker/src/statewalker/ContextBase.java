/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statewalker;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public class ContextBase<C extends ContextBase<C,D,S>,D extends Device<C,D,S>, S extends StateBase<C,D,S>> {
    StateStack<S> stack;
        protected StateStack<S> stateStack = new StateStack<>(5);
    protected D device;

    public ContextBase(D device) {
        this.device = device;
    }

    public ContextBase() {
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
