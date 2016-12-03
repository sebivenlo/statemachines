/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statewalkertest;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static statewalkertest.ContextBaseTest.S.*;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public class ContextBaseTest {

    enum S implements State {
        SI {
            @Override
            public void on( Context ctx ) {
                ctx.changeFromToState( "on", this, S1 );
            }
            @Override
            public void off( Context ctx ) {
                ctx.changeFromToState( "on", this, S2 );
            }

        }, S1, S2 {
            @Override
            public State getInitialState() {
                return S21;
            }
        }, S11, S12, S21{
            @Override
            public void alarm( Context ctx ) {
                ctx.changeFromToState( "alarm on S21", this, S22 );
            }

        }, S22{
        
            @Override
            public State getInitialState() {
                return S222;
            }
        
        },S222, NULL {
            @Override
            public State getInitialState() {
                return SI; //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void alarm( Context ctx ) {
                
            }

            @Override
            public void off( Context ctx ) {
                
            }

            @Override
            public void on( Context ctx ) {
                
            }
            
        };

        @Override
        public State getNullState() {
            return NULL;
        }

        @Override
        public void exit( Context ctx ) {
            System.out.println( "about te exit "+this.name() );
            State.super.exit( ctx ); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void enter( Context ctx ) {
            State.super.enter( ctx ); //To change body of generated methods, choose Tools | Templates.
            System.out.println( "just entered  "+this.name() );
        }
        
    }

    Context ctx;

    @Before
    public void setup() {
        ctx = new Context( new Dev(), S.class );
    }

    @Test
    public void constructorWorks() {
        String ss = ctx.logicalState();
        assertEquals( "SI", ss );
    }

    @Test
    public void testEnterS1() {
        ctx.changeFromToState( "away", SI, S1 );
        assertEquals( "S1", ctx.logicalState() );
    }

    @Test
    public void testEnterS2S21() {
        ctx.changeFromToState( "away", SI, S2 );
        assertEquals( "S2.S21", ctx.logicalState() );
    }

    @Test
    public void testEventOn() {
        ctx.on();
        assertEquals( "S1", ctx.logicalState() );
    }

    @Test
    public void testEventOff() {
        ctx.off();
        assertEquals( "S2.S21", ctx.logicalState() );
    }

    @Test
    public void testEventAlarm() {
        ctx.off();
        ctx.alarm();
        assertEquals( "S2.S22.S222", ctx.logicalState() );
    }
}
