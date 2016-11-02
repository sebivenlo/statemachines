/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statestack;

/**
 * Primary functions for a state machine
 * 
 * Define a default method that returns void.
 * 
 * Example: default void accelerate(ContextBase<CCState, Device> ctx){ ctx.superState( this ).accelerate( ctx );};
 * 
 * @author Sander
 */
public interface StateBase {
    default void enter(ContextBase ctx){}//System.out.println( "entering state "+this.toString() );};
    default void exit(ContextBase ctx){}//System.out.println( "leaving state "+this.toString() );};
}
