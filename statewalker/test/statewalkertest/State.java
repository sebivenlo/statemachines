/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statewalkertest;

import statewalker.StateBase;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public interface State extends StateBase<Context,Dev,State>{

    default void on(Context ctx){ ctx.superState(this).on(ctx);}
    default void off(Context ctx){ ctx.superState(this).off(ctx);}
    default void alarm(Context ctx){ ctx.superState(this).off(ctx);}

    @Override
    default void exit( Context ctx ){}
    @Override
    default void enter( Context ctx ){};
    

}
