/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statewalker;

import java.util.Map;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 * @param <C> Context
 * @param <D> Device
 * @param <S> StateBase
 */
public interface StateBase<C extends ContextBase<C, D, S>, D extends Device<C, D, S>, S extends StateBase<C, D, S>> {

    /**
     * This method is called whenever a state is entered.
     *
     * @param ctx the context for all operations.
     */
    default void enter( C ctx ) { }

    /**
     * This method is called whenever a state is left.
     *
     * @param ctx the context for all operations.
     */
    default void exit( C ctx ) { }

    /**
     * This method is used to ask all states for their initial sub state, so
     * that history can be implemented inside the framework.
     *
     * @return the initial sub-state of this state.
     */
    S getInitialState();
}
