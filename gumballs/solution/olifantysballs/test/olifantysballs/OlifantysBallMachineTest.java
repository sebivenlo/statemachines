package olifantysballs;

import static olifantysballs.StateEnum.HasCoin;
import static olifantysballs.StateEnum.NoCoin;
import static olifantysballs.StateEnum.SoldOut;
import static olifantysballs.StateEnum.Winner;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verify;

/**
 * Test class for Ball Machine. Method names should say it all.
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public class OlifantysBallMachineTest {

    OlifantysBallMachine instance = new OlifantysBallMachine();

    /**
     * Fill with enough balls, insert coin and draw repeat.
     */
    @Test
    public void get_some_balls() {
        //Start Solution::replacewith:://TODO 
        instance.refill( 2 );
        assertSame( "start state", NoCoin, instance.getState() );
        System.out.println( "get a tasty ball" );
        instance.insertCoin();
        assertSame( "start state", HasCoin, instance.getState() );
        instance.draw();
        instance.insertCoin();
        instance.draw();
        assertTrue( instance.isEmpty() );
        //End Solution::replacewith::fail("test not implemented");
    }

    @Test
    public void gimme_my_money_back() {
        //Start Solution::replacewith:://TODO 
        System.out.println( "I want my money back" );
        instance.ejectCoin(); // impatient bugger, helps coverage.
        instance.refill( 10 );
        instance.insertCoin();
        assertSame( "start state", HasCoin, instance.getState() );
        instance.ejectCoin();
        assertSame( "start state", NoCoin, instance.getState() );
        instance.ejectCoin();
        assertSame( "start state", NoCoin, instance.getState() );
        //End Solution::replacewith::fail("test not implemented");
    }

    /**
     * fill in three portions. should go from SoldOut to NoCoin and stay there.
     */
    @Test
    public void fillup() {
        assertSame( "Starts in Sold out", SoldOut, instance.getState() );
        instance.refill( 2 );
        assertSame( "No in waiting for coin", NoCoin, instance.getState() );
        instance.insertCoin(); // refill in HasCoin state for coverage
        assertSame( "Should be in HasCoin", HasCoin, instance.getState() );
        instance.refill( 2 );
        assertSame( "Should stay in HasCoin", HasCoin, instance.getState() );
        instance.refill( 2 );
        assertEquals( "Enough for three winners ", 6, instance.getBallCount() );
    }

    /**
     * Use a spy to monitor what happens in a instance and if indeed the proper
     * methods a are being called. We call the instance buggedInstance to show
     * that it is being watched.
     */
    @Test
    public void make_me_rich_aka_try_insert_coin_in_every_state() {
        OlifantysBallMachine m = new OlifantysBallMachine();
        OlifantysBallMachine buggedInstance = Mockito.spy( m ); // <1>
        buggedInstance.refill( 5 );
        buggedInstance.insertCoin(); // <2>
        buggedInstance.insertCoin(); // <3>
        verify( buggedInstance, times( 1 ) ).setState( HasCoin ); // <4>
        assertSame( "should be stubbornly staying in HasCoin", HasCoin,
                buggedInstance.getState() ); // <5>
    }

    /**
     * For coverage, test with zero and one ball(s), to see the plural s coming
     * and going. For coverage.
     */
    @Test
    public void tostring_of_empty_machine() {
        // for coverage
        OlifantysBallMachine m = new OlifantysBallMachine();
        assertTrue( m.toString().contains( "gumballs" ) );
        m.refill( 1 );
        assertFalse( m.toString().contains( "gumballs" ) );
    }

    /**
     * isWinner uses a random generator with a 10% lucky chance. Try to become a
     * winner for some acceptable testing time.
     */
    @Test( timeout = 200 )
    public void wait_for_winner() {
        OlifantysBallMachine m = new OlifantysBallMachine();
        m.refill( 1 );
        boolean succes = false;
        while ( true ) {
            if ( succes |= m.isWinner() ) {
                break;
            }
        }
        assertTrue( "perseverance wins", succes );
    }

    @Test
    public void empty_machine_has_no_winner() {
        OlifantysBallMachine m = new OlifantysBallMachine();
        assertFalse( "no winners here", m.isWinner() );
    }

    /**
     * Test that methods that should not result in a state change indeed do not.
     * Use a helper method to invoke the methods as Runnable.
     */
    @Test
    public void ignored_events_in_has_coin() {
        instance.setState( HasCoin );
        OlifantysBallMachine spy = Mockito.spy( this.instance );
        assertNoStateChange( spy,
                spy::insertCoin, // void()
                () -> {
                    spy.refill( 12 );
                } // wrapped in runnable
        );
    }

    /**
     * Test that methods that should not result in a state change indeed do not.
     * Use a helper method to invoke the methods as Runnable.
     */
    @Test
    public void ignored_events_in_soldout() {
        //Start Solution::replacewith:://TODO 
        instance.setState( SoldOut );
        OlifantysBallMachine spy = Mockito.spy( this.instance );
        assertNoStateChange( spy,
                spy::insertCoin,
                spy::draw,
                spy::ejectCoin );
        //End Solution::replacewith::fail("test not implemented");
    }

    /**
     * Test that methods that should not result in a state change indeed do not.
     * Use a helper method to invoke the methods as Runnable.
     */
    @Test
    public void ignored_events_in_nocoin() {
        //Start Solution::replacewith:://TODO 
        this.instance.setState( NoCoin );
        OlifantysBallMachine spy = Mockito.spy( this.instance );
        assertNoStateChange( spy,
                spy::ejectCoin,
                spy::draw,
                () -> {
                    spy.refill( 2 );
                } );
        //End Solution::replacewith::fail("test not implemented");
    }

    /**
     * Test that methods that should not result in a state change indeed do not.
     * Use a helper method to invoke the methods as Runnable.
     */
    @Test
    public void ignored_events_in_winner() {
        //Start Solution::replacewith:://TODO 
        instance.setState( Winner );
        OlifantysBallMachine spy = Mockito.spy( this.instance );
        System.out.println( "sintance= " + spy.getClass().
                getCanonicalName() );
        assertNoStateChangeSpied( spy,
                spy::insertCoin,
                spy::ejectCoin,
                () -> {
                    spy.refill( 2 );
                }
        //                ,
        //                spy::draw // fails
        );
        //End Solution::replacewith::fail("test not implemented");
    }

    /**
     * Helper to invoke methods on context that should not change from its
     * initial state. This method only tests if the state before and after are
     * the same, which is is does not ensure that the machine changed to through
     * the actions of the runnable.
     *
     * @param ctx context of the state
     * @param operations list of runnables to be invoked on the instance.
     *
     */
    static void assertNoStateChange( Context ctx, Runnable... operations ) {
        State initial = ctx.getState();
        for ( Runnable op : operations ) {
            op.run();
            assertSame( "invocation effected state change", initial, ctx.
                    getState() );
        }
    }

    /**
     * Helper to invoke methods on context that should not change from its
     * initial state.
     *
     * When using this method, make sure you start spying on the context AFTER
     * you moved it to the proper state. Otherwise the spy will rightfully fail
     * because a set or change state did happen.
     *
     * @param ctx spied (mocked) upon context
     * @param operations list of runnables to be invoked on the instance.
     *
     * @throws org.mockito.exceptions.misusing.NotAMockException when ctx is not
     * a mock as in not spied upon. Consider this a error.
     */
    static void assertNoStateChangeSpied( Context ctx, Runnable... operations ) {
        State initial = ctx.getState();
        for ( Runnable op : operations ) {
            op.run();
            verify( ctx, never() ).setState( any() );
            verify( ctx, never() ).changeState( any() );
            assertSame( "invocation effected state change", initial, ctx.
                    getState() );
        }
    }
}
