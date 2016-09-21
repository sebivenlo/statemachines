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
public class StateEnumTest {

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

    @Test
    public void test_no_quarter_draws_nothing() {
        System.out.println( "test no dice" );
        State state = NoCoin;  // <1>
        state.draw( machine );  // <2>
        String response = sout.toString(); // <3>
        // assert response
        System.out.println( "response 1 = " + response );
        assertTrue( response.startsWith( "You must put in a coin" ) ); // <4>
        // verify that machine.setState is not called 
        verify( machine, never() ).setState( Matchers.any() ); // <5>
        verify( machine, never() ).changeState( Matchers.any() ); // <6>
        verify( machine, never() ).dispense(); // <7>
    }

    @Test
    public void test_one_coin_dispenses_ball() {
        System.out.println( "test coin for one ball" );
        when( machine.getBallCount() ).thenReturn( 1 );
        State state = HasCoin;
        state.draw( machine );
        // verify one dispense and state change
        verify( machine, times( 1 ) ).dispense();
        verify( machine ).setState( NoCoin );
    }

    @Test
    public void test_winner_dispenses_ball() {
        System.out.println( "test for winner dispenses" );
        // set up for three invocations
        when( machine.isEmpty() ).thenReturn( true );
        State state = Winner;
        state.draw( machine );
        // verify one dispense and state change
        verify( machine, times( 1 ) ).dispense();
        verify( machine ).setState( SoldOut );
    }

    @Test
    public void test_one_draw_to_winner() {
        System.out.println( "draw to winner" );
        when( machine.isWinner() ).thenReturn( true );
        State state = HasCoin;
        state.draw( machine );
        // verify one dispense and state change
        verify( machine, times( 1 ) ).dispense();
        verify( machine ).changeState( Winner );
    }

    @Test
    public void test_winner_takes_all() {
        System.out.println( "winner takes all" );
        when( machine.isEmpty() ).thenReturn( true );
        State state = Winner;
        state.draw( machine );
        // verify one dispense and state change
        verify( machine, times( 1 ) ).dispense();
        verify( machine ).setState( SoldOut );

    }

    @Test
    public void test_winner_not_so_gready() {
        System.out.println( "winner takes all" );
        when( machine.isEmpty() ).thenReturn( false );
        State state = Winner;
        state.draw( machine );
        // verify one dispense and state change
        verify( machine, times( 1 ) ).dispense();
        verify( machine ).setState( NoCoin );

    }

    @Test
    public void test_one_ball_to_sold_out() {
        System.out.println( "get last ball" );
        when( machine.isEmpty() ).thenReturn( true );
        State state = HasCoin;
        state.draw( machine );
        // verify one dispense and state change
        //verify( machine, times( 1 ) ).dispense();
        verify( machine, times( 1 ) ).isEmpty();
        verify( machine ).setState( SoldOut );
    }

    @Test
    public void insert_no_coin_to_has_coin() {
        System.out.println( "insert coin to has coin" );
        State state = NoCoin;
        state.insertCoin( machine );
        verify( machine ).setState( HasCoin );
    }

    @Test
    public void eject_has_coin_to_no_coin() {
        System.out.println( "eject to no coin" );
        State state = HasCoin;
        state.ejectCoin( machine );
        verify( machine ).setState( NoCoin );
    }

    @Test
    public void refill_back_to_business() {
        System.out.println( "refill back to business" );
        State state = SoldOut;
        state.refill( machine, 50 );
        verify( machine ).setState( NoCoin );
    }

    @Test
    public void fill_it_up_in_every_state() {
        // This test is brittle, the mock remembers things so the order 
        // of test and verify matter. Better use three separate focused methods.
        System.out.println( "fill up in no coin" );
        sout.clear();
        State state = NoCoin;
        state.refill( machine, 50 );
        String response = sout.toString();
        System.out.println( "response = " + response );
        assertTrue( response.startsWith( "You must" ) );
        verify( machine, never() ).setState( Matchers.any() );

        System.out.println( "fill up in has coin" );
        sout.clear();
        state = HasCoin;
        state.refill( machine, 50 );
        response = sout.toString();
        System.out.println( "response = " + response );
        assertTrue( response.startsWith( "Have a coin" ) );
        verify( machine, never() ).setState( Matchers.any() );

        System.out.println( "fill up in sould out" );
        state = SoldOut;
        sout.clear();
        state.refill( machine, 50 );
        response = sout.toString();
        System.out.println( "response = " + response );
        assertEquals( "no sweat", "", sout.toString() );
        verify( machine ).setState( NoCoin );
    }

}
