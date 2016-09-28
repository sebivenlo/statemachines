package olifantysballs;

import java.io.PrintStream;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static olifantysballs.StateEnum.*;
import org.junit.After;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * The test method names should say it all.
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
@RunWith( MockitoJUnitRunner.class )
public class StateEnumTest1 {

    @Mock // <1>
    Context machine; // <2>
    StringOutput sout = new StringOutput(); // <3>
    PrintStream out = sout.asPrintStream(); // <4>

    @Before
    public void setup() {
        MockitoAnnotations.initMocks( this );// <5>
        when( machine.getOutput() ).thenReturn( out ); // <6>
    }

    @After
    public void teardown() {
        sout.clear();
    }

    /**
     * Test of values method, of class StateEnum.
     */
    @Test
    public void testValues() {
        System.out.println( "test values" );
        StateEnum[] expResult = { NoCoin, HasCoin, SoldOut, Winner };
        StateEnum[] result = StateEnum.values();
        assertArrayEquals( expResult, result );
    }

    /**
     * Test of valueOf method, of class StateEnum.
     */
    @Test
    public void testValueOf() {
        System.out.println( "test valueOf" );
        String name = "SoldOut";
        StateEnum expResult = SoldOut;
        StateEnum result = StateEnum.valueOf( name );
        assertEquals( expResult, result );
    }

    /**
     * In state NoCoin verify that no ball is dispensed and no state change
     * takes place. A state chance would occur through a changeState or changeState
     * call on the machine.
     * A dispense would be a dispense call on the machine.
     */
    @Test
    public void test_no_quarter_draws_nothing() {
        System.out.println( "test no dice" );

        State state = NoCoin;  // <1>
        state.draw( machine );  // <2>
        String response = sout.toString(); // <3>
        // assert response
        System.out.println( "response 1 = " + response );
        assertTrue( response.startsWith( "You must put in a coin" ) ); // <4>
        // verify that machine.changeState is not called 
        verify( machine, never() ).changeState( Matchers.any() ); // <5>
        verify( machine, never() ).changeState( Matchers.any() ); // <6>
        verify( machine, never() ).dispense(); // <7>
    }

    @Test
    public void test_has_coin_dispenses_ball() {
        System.out.println( "test coin for one ball" );
        //Start Solution::replacewith:://TODO 
        when( machine.getBallCount() ).thenReturn( 1 );
        State state = HasCoin;
        state.draw( machine );
        // verify one dispense and state change
        verify( machine, times( 1 ) ).dispense();
        verify( machine ).changeState( NoCoin );
        //End Solution::replacewith::fail("test not implemented");
    }

    @Test
    public void test_winner_dispenses_ball() {
        System.out.println( "test for winner dispenses" );
        // set up for three invocations
        //Start Solution::replacewith:://TODO 
        when( machine.isEmpty() ).thenReturn( true );
        State state = Winner;
        state.draw( machine );
        // verify one dispense and state change
        verify( machine, times( 1 ) ).dispense();
        verify( machine ).changeState( SoldOut );
        //End Solution::replacewith::fail("test not implemented");
    }

    @Test
    public void test_one_coin_draw_to_winner() {
        System.out.println( "draw to winner" );
        //Start Solution::replacewith:://TODO 
        when( machine.isWinner() ).thenReturn( true );
        State state = HasCoin;
        state.draw( machine );
        // verify one dispense and state change
        verify( machine, times( 1 ) ).dispense();
        verify( machine ).changeState( Winner );
        //End Solution::replacewith::fail("test not implemented");
    }

    @Test
    public void test_winner_takes_last_ball() {
        System.out.println( "winner takes all" );
        //Start Solution::replacewith:://TODO 
        when( machine.isEmpty() ).thenReturn( true );
        State state = Winner;
        state.draw( machine );
        // verify one dispense and state change
        verify( machine, times( 1 ) ).dispense();
        verify( machine ).changeState( SoldOut );
        //End Solution::replacewith::fail("test not implemented");
    }

    @Test
    public void test_winner_not_so_gready() {
        System.out.println( "winner leaves a ball" );
        //Start Solution::replacewith:://TODO 
        when( machine.isEmpty() ).thenReturn( false );
        State state = Winner;
        state.draw( machine );
        // verify one dispense and state change
        verify( machine, times( 1 ) ).dispense();
        verify( machine ).changeState( NoCoin );
        //End Solution::replacewith::fail("test not implemented");
    }

    @Test
    public void test_one_ball_to_sold_out() {
        System.out.println( "get last ball" );
        //Start Solution::replacewith:://TODO 
        when( machine.isEmpty() ).thenReturn( true );
        State state = HasCoin;
        state.draw( machine );
        // verify one dispense and state change
        //verify( machine, times( 1 ) ).dispense();
        verify( machine, times( 1 ) ).isEmpty();
        verify( machine ).changeState( SoldOut );
        //End Solution::replacewith::fail("test not implemented");
    }

    @Test
    public void insert_no_coin_to_has_coin() {
        System.out.println( "insert coin to has coin" );
        //Start Solution::replacewith:://TODO 
        State state = NoCoin;
        state.insertCoin( machine );
        verify( machine ).changeState( HasCoin );
        //End Solution::replacewith::fail("test not implemented");
    }

    @Test
    public void eject_has_coin_to_no_coin() {
        System.out.println( "eject to no coin" );
        //Start Solution::replacewith:://TODO 
        State state = HasCoin;
        state.ejectCoin( machine );
        verify( machine ).changeState( NoCoin );
        //End Solution::replacewith::fail("test not implemented");
    }

    @Test
    public void refill_back_to_business() {
        System.out.println( "refill back to business" );
        //Start Solution::replacewith:://TODO 
        State state = SoldOut;
        state.refill( machine, 50 );
        verify( machine ).changeState( NoCoin );
        //End Solution::replacewith::fail("test not implemented");
    }

    @Test
    public void fill_it_up_in_every_state() {
        // This test is brittle, the mock remembers things so the order 
        // of test and verify matter. Better use three separate focused methods.
        String fillResponse = "refilled with 50 balls";
        System.out.println( "fill up in no coin" );
        sout.clear();
        State state = NoCoin;
        state.refill( machine, 50 );
        String response = sout.toString();
        System.out.println( "response hascoin = " + response );
        assertTrue( response.startsWith( fillResponse ) );
        verify( machine, never() ).changeState( Matchers.any() );

        System.out.println( "fill up in no coin" );
        sout.clear();
        state = HasCoin;
        state.refill( machine, 50 );
        response = sout.toString();
        System.out.println( "response hascoin = " + response );
        assertTrue( response.startsWith( fillResponse ) );
        verify( machine, never() ).changeState( Matchers.any() );

        System.out.println( "fill up in winner" );
        sout.clear();
        state = Winner;
        state.refill( machine, 50 );
        response = sout.toString();
        System.out.println( "response winner = " + response );
        assertTrue( response.startsWith( fillResponse ) );
        verify( machine, never() ).changeState( Matchers.any() );

        System.out.println( "fill up in sould out" );
        state = SoldOut;
        sout.clear();
        state.refill( machine, 50 );
        response = sout.toString();
        System.out.println( "response = " + response );
        assertTrue( "no sweat", response.
                startsWith( fillResponse ) );
        verify( machine ).changeState( NoCoin );
    }
}
