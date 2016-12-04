/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statewalkertest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static statewalkertest.S.*;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public class ContextBaseTest {

    Context ctx;

    @Before
    public void setup() {
        ctx = new Context( new Dev(), S.class ).setDebug( true );
    }

    @After
    public void tearDown() {
        ctx = null;
    }

    @Test
    public void constructorWorks() {
        System.out.println( "==== constructorWorks" );
        String ss = ctx.logicalState();
        System.out.println( "initial state = " + ss );
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

    @Test
    public void testGetFirstChild() {
        ctx.e1();
        assertEquals( S11, ctx.getFirstChild( S1 ) );
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void leavNonExistingSubStates(){
        ctx.e1();
        ctx.leaveSubStates(S21);
    }

    private void leaveSubStates( S s ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }
}
