package statewalkertest;

import statewalker.StateBase;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public interface State extends StateBase<Context, Dev, State> {

    default void e1( Context ctx ) {
        ctx.superState( this ).e1( ctx );
    }

    default void e2( Context ctx ) {
        ctx.superState( this ).e2( ctx );
    }

    default void e3( Context ctx ) {
        ctx.superState( this ).e3( ctx );
    }

    default void e4( Context ctx ) {
        ctx.superState( this ).e4( ctx );
    }

    default void e5( Context ctx ) {
        ctx.superState( this ).e5( ctx );
    }

    default void e6( Context ctx ) {
        ctx.superState( this ).e6( ctx );
    }

    default void e7( Context ctx ) {
        ctx.superState( this ).e7( ctx );
    }

    @Override
    default void exit( Context ctx ) {
    }

    @Override
    default void enter( Context ctx ) {
    }
}
