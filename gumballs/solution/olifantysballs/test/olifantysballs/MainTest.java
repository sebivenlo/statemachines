package olifantysballs;

import org.junit.Test;

/**
 * Just to get coverage report a nice saturated green.
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public class MainTest {

    @Test
    public void call_application() {
        String[] args = {};
        Main m = new Main();
        Main.main( args );
    }

}
