/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statestack;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 * @param <C> Context
 * @param <D> Device
 * @param <S> StateBase
 */
public interface  Device<C extends ContextBase<C,D,S>,D extends Device<C,D,S>, S extends StateBase<C,D,S>> {
    
}
