package statewalkertest;

import statewalker.ContextBase;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public class Context extends ContextBase<Context, Dev, State> {

    public Context( Class<?> stateClass ) {
        super( stateClass );
    }

    void e1() {
        getTopState().e1( this );
    }

    void e2() {
        getTopState().e2( this );
    }

    void e3() {
        getTopState().e3( this );
    }

    void e4() {
        getTopState().e4( this );
    }

    void e5() {
        getTopState().e5( this );
    }

    void e6() {
        getTopState().e6( this );
    }

    void e7() {
        getTopState().e7( this );
    }

    static Dev dev = new Dev();

    @Override
    public Dev getDevice() {
        return dev;
    }

}
