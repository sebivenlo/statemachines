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
public interface  Device<C extends ContextBase<C,D,S>,D extends Device<C,D,S>, S extends StateBase<C,D,S>> {
    
}
