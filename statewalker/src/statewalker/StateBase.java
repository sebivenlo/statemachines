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
public interface StateBase<C extends ContextBase<C,D,S>,D extends Device<C,D,S>, S extends StateBase<C,D,S>> {
    default void enter(ContextBase ctx){}//System.out.println( "entering state "+this.toString() );};
    default void exit(ContextBase ctx){}//System.out.println( "leaving state "+this.toString() );};
    
}
