/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statewalker;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public class StateBaseTest {
    StateBase sb = new StateBase(){
        @Override
        public int ordinal() {
            return 0;
        }

        @Override
        public StateBase getNullState() {
            return null;
        }
    };
    
    ContextBase cb = new ContextBase(new Device(){}, StateBase.class);
    /**
     * Only for coverage, the methods have empty bodies
     */
    @Test
    public void testSomeMethod() {
        assertNull(sb.getInitialState());
        assertFalse(sb.isInitialStateHistory());
        sb.enter(cb );
        sb.exit(cb);
    }
    
}
