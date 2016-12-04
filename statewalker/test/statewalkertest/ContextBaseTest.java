/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statewalkertest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Ignore;
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
            public void e1( Context ctx ) {
                ctx.changeFromToState( "e1", this, S1 );
            }

            @Override
            public void e2( Context ctx ) {
                ctx.changeFromToState( "e2", this, S2 );
            }

        }, S1 {
            @Override
            public State getInitialState() {
                return S11;
            }

            @Override
            public void e3( Context ctx ) {
                ctx.changeFromToState( "e3", this, SI );
            }

            @Override
            public void e4( Context ctx ) {
                ctx.innerTransition( "e4", this, S12 );
            }

        }, S2 {
            @Override
            public State getInitialState() {
                return S21;
            }

            @Override
            public void e5( Context ctx ) {
                ctx.changeFromToState( "e5",  this, S1);
            }

            @Override
            public void e6( Context ctx ) {
                ctx.innerTransition( "e6", this, ctx.getFirstChild( this ) == S21 ? S22 : S21 );
            }

        }, S11, S12, S21 {

        }, S22 {

            @Override
            public State getInitialState() {
                return S221;
            }

            @Override
            public boolean isInitialStateHistory() {
                return true;
            }
        }, S221 {
            @Override
            public void e7( Context ctx ) {
                ctx.changeFromToState( "e7", this, S222 );
            }

        }, S222, NULL {
            @Override
            public State getInitialState() {
                return SI; //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void e1( Context ctx ) {
            }

            @Override
            public void e2( Context ctx ) {
            }

            @Override
            public void e3( Context ctx ) {
            }

            @Override
            public void e4( Context ctx ) {
            }

            @Override
            public void e5( Context ctx ) {
            }

            @Override
            public void e6( Context ctx ) {
            }

            @Override
            public void e7( Context ctx ) {
            }

            @Override
            public void exit( Context ctx ) {
            }

            @Override
            public void enter( Context ctx ) {

            }

        };

        @Override
        public State getNullState() {
            return NULL;
        }

        @Override
        public void exit( Context ctx ) {
            System.out.println( "about to exit " + ctx.logicalState() );
        }

        @Override
        public void enter( Context ctx ) {
            System.out.println( "just entered  " + ctx.logicalState() );
        }

    }

    Context ctx;

    @Before
    public void setup() {
        ctx = new Context( new Dev(), S.class );
    }

    @After
    public void tearDown() {
        ctx = null;
    }

    @Test
    public void constructorWorks() {
        System.out.println( "==== constructorWorks" );
        String ss = ctx.logicalState();
        assertEquals( "SI", ss );
    }

    @Test
    public void testE1() {
        System.out.println( "==== e1" );
        ctx.e1();
        assertEquals( "S1.S11", ctx.logicalState() );
    }

    @Test
    public void testE2() {
        System.out.println( "==== e2" );
        ctx.e2();
        assertEquals( "S2.S21", ctx.logicalState() );
    }

    @Test
    public void testE3() {
        System.out.println( "==== e3" );
        ctx.e1();

        ctx.e3();
        assertEquals( "SI", ctx.logicalState() );
    }
    
    @Test
    public void testE4() {
        System.out.println( "==== e4" );
        ctx.e1();

        ctx.e4();
        assertEquals( "S1.S12", ctx.logicalState() );
    }

    @Test
    public void testE5() {
        System.out.println( "==== e5" );
        ctx.e2();

        ctx.e5();
        assertEquals( "S1.S11", ctx.logicalState() );
    }

    @Test
    public void testE6() {
        System.out.println( "==== e6" );
        ctx.e2();
        assertEquals( "S2.S21", ctx.logicalState() );

        ctx.e6();
        assertEquals( "S2.S22.S221", ctx.logicalState() );

        ctx.e6();
        assertEquals( "S2.S21", ctx.logicalState() );
    }
    @Test
    public void testE7() {
        System.out.println( "==== e7" );
        ctx.e2();
        ctx.e6();
        assertEquals( "S2.S22.S221", ctx.logicalState() );

        ctx.e7();
        assertEquals( "S2.S22.S222", ctx.logicalState() );
        
       
    }

    @Test
    public void testHistory() {
        System.out.println( "==== history" );
        ctx.e2();
        ctx.e6();
        ctx.e7();
        assertEquals( "S2.S22.S222", ctx.logicalState() );
        ctx.e6();
        ctx.e6();
        assertEquals( "S2.S22.S222", ctx.logicalState() );
    }
    

    
}
