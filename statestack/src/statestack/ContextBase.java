/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statestack;

import java.util.HashMap;
import java.util.Map;

/**
 * Primary functions for a state context<br>
 *
 * Please do not forget to pass the device in the constructor
 *
 * @author Sander
 * @param <C> Implementation of the Context
 * @param <D> Implementation of the Device
 * @param <S> State
 */
public class ContextBase<C extends ContextBase<C, D, S>, D extends Device<C, D, S>, S extends StateBase<C, D, S>> {

    protected StateStack<S> stateStack = new StateStack<>(5);
    protected Map<S, S> history = new HashMap<>();
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
            S stackTop = stateStack.peek();
            history.put(stackTop, cCState);
            stateStack.push(cCState);
            cCState.enter( ( C ) this);
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
            topState.exit( ( C ) this);
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
            topState.exit( ( C ) this);
            //            System.out.println( "leaving " + topState );
        }
        topState.exit( ( C ) this);
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
    
    public S getHistoryStateFrom(S from, S initial){
        if(!history.containsKey(from)){
            return initial;
        }
        return history.get(from);
    }
    
    public String logicalState() {
        return stateStack.logicalState();
    }
}
