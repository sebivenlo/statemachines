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
public class ContextBaseTest {
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
    enum SB implements StateBase{
        SB;

        @Override
        public StateBase getNullState() {
            return this;
        }
    };
    Device dev = new Device() {
    };
    Device dev2 = new Device() {
    };
    @SuppressWarnings( "unchecked" )
    ContextBase cb = new ContextBase(sb.getClass() ){
        @Override
        public Device getDevice() {
            return dev;
        }
    };
    @SuppressWarnings( "unchecked" )
    ContextBase cb2 = new ContextBase(SB.SB.getClass() ){
        @Override
        public Device getDevice() {
            return dev2;
        }
    };
    
    /**
     * For coverage.
     */
    @Test
    @SuppressWarnings( "unchecked" )
    public void testSomeMethod() {
        assertSame(dev,cb.getDevice());
        assertNotSame(dev,cb2.getDevice());
    }
    
}
