package statestack;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
interface CCState {
    default void enter(Context ctx){}//System.out.println( "entering state "+this.toString() );};
    default void exit(Context ctx){}//System.out.println( "leaving state "+this.toString() );};
    
    default void accelerate(Context ctx){ ctx.superState( this ).accelerate( ctx );};
    default void resume(Context ctx){ctx.superState( this ).resume( ctx );};
    default void engineOff(Context ctx){ctx.superState( this ).engineOff( ctx );};
    default void engineOn(Context ctx){ctx.superState( this ).engineOn( ctx );};
    default void brakePressed(Context ctx){ctx.superState( this ).brakePressed( ctx );};
    default void cruiseSpeedReached(Context ctx){ctx.superState( this ).cruiseSpeedReached( ctx );};
    default void cruise(Context ctx){ctx.superState( this ).cruise( ctx );};
}
