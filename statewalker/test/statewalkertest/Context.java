/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statewalkertest;

import statewalker.ContextBase;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public class Context extends ContextBase<Context,Dev,State >{
    
    public Context( Dev device, Class<?> stateClass ) {
        super( device, stateClass );
    }
    
    void on (){
        getTopState().on( this );
    }

    void off (){
        getTopState().off( this );
    }

    void alarm (){
        getTopState().alarm( this );
    }
}
