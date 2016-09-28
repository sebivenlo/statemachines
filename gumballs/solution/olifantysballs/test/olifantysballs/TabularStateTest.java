package olifantysballs;

import java.util.function.BiConsumer;
import static olifantysballs.StateEnum.*;
import static olifantysballs.TabularStateTest.TestData.td;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Matchers;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Example of using Parameterized tests with mockito.
 *
 * In this Junit test example we combine Mockito with a Parameretized test. The
 * advantages and disadvantages are:
 * <ul>
 * <li>
 * The state transition closely matches the one given on the web site. This
 * allows easy verification of completeness, in particular for those triggers
 * that should not result in a state change.
 * </li>
 * <li>
 * The test method is more complex because it must deal with all combinations of
 * triggers, target states and guard values. These can however easily be
 * expressed
 * </li>
 * <li> Because we want a parameterized test ran with
 * {@code @RunWith(Parameterized.class)}, we must mock 'by hand' instead of with
 * a {@code @mock} annotation. In this case w do that in the constructor of the
 * test class which is needed anyway. It also makes the {@code @Before} method
 * obsolete.
 * </li>
 * </ul>
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
@RunWith( Parameterized.class )
public class TabularStateTest {

    /**
     * Context of the state. Mocked by Mockito in the constructor.
     */
    final Context ctx;

    /**
     * The system under test, the state instances.
     */
    final State state;

    /**
     * The all important test data. Each row is a test for one test. The meaning
     * of the data elements can be read in the {@link TestData#TestData(olifantysballs.State, olifantysballs.State, java.util.function.BiConsumer, boolean, boolean, int, int) constructor.
     * td(...) is shorthand for new TestData(...)
     */
    //<editor-fold preserve-formatting='true'>
    static TestData[] testData = {
        //  init  , end    , trigger                      , empty, winner , dispense, addBalls
        td( NoCoin, HasCoin, ( c, s ) -> s.insertCoin( c ), false, false, 0, 0 ),
        td( HasCoin, NoCoin, ( c, s ) -> s.ejectCoin( c ), false, false, 0, 0 ),
        td( HasCoin, NoCoin, ( c, s ) -> s.draw( c ), false, false, 1, 0 ),
        td( HasCoin, SoldOut, ( c, s ) -> s.draw( c ), true, false, 1, 0 ),
        td( HasCoin, Winner, ( c, s ) -> s.draw( c ), false, true, 1, 0 ),
        td( Winner, NoCoin, ( c, s ) -> s.draw( c ), false, false, 1, 0 ),
        td( Winner, SoldOut, ( c, s ) -> s.draw( c ), true, true, 1, 0 ),
        td( SoldOut, NoCoin, ( c, s ) -> s.refill( c, 5 ), false, false, 0, 1 ),
        //td no state change: refill in 3 states
        td( Winner, null, ( c, s ) -> s.refill( c, 5 ), false, false, 0, 1 ),
        td( HasCoin, null, ( c, s ) -> s.refill( c, 5 ), false, true, 0, 1 ),
        td( NoCoin, null, ( c, s ) -> s.refill( c, 5 ), false, true, 0, 1 ),
        //td n no state change in 3 states
        td( Winner, null, ( c, s ) -> s.insertCoin( c ), false, false, 0, 0 ),
        td( SoldOut, null, ( c, s ) -> s.insertCoin( c ), false, false, 0, 0 ),
        td( HasCoin, null, ( c, s ) -> s.insertCoin( c ), false, false, 0, 0 ),
        //td no state change in 3 states
        td( Winner, null, ( c, s ) -> s.ejectCoin( c ), false, false, 0, 0 ),
        td( SoldOut, null, ( c, s ) -> s.ejectCoin( c ), false, false, 0, 0 ),
        td( NoCoin, null, ( c, s ) -> s.ejectCoin( c ), false, false, 0, 0 ),
    
    };
    
    BiConsumer<Context, State> mrX = new BiConsumer<Context, State>() {
        @Override
        public void accept( Context c, State s ) {
            s.insertCoin( c );
        }
    };
    //</editor-fold>
    final TestData myData;

    /**
     * Constructor setting up the data for the test and creating and setting up
     * the mocked context.
     *
     * @param data for one test.
     */
    public TabularStateTest( TestData data ) {
        this.myData = data;
        state = data.initial;
        ctx = Mockito.mock( Context.class );
        when( ctx.getOutput() ).thenReturn( System.out );
    }

    /**
     * The only test method. Continues with mock training and then does the
     * interaction with the sut once by invoking trigger.accept. After the
     * invocation it verifies the invocation counts of refills and drawcount. If
     * the end state is not null, the state change is tested as well.
     */
    @Test
    public void testState() {
        // prime collaborator
        when( ctx.isEmpty() ).thenReturn( myData.empty );
        when( ctx.isWinner() ).thenReturn( myData.winner );
        // do the interaction.
        myData.triggerAction.accept( ctx, myData.initial );
        verify( ctx, times( myData.addBallsCount ) ).addBalls( Matchers.anyInt() );
        verify( ctx, times( myData.drawCount ) ).dispense();
        if ( myData.end != null ) {
            verify( ctx, times( 1 ) ).changeState( myData.end );

        } else {
            verify( ctx, times( 0 ) ).changeState( any() );
        }
    }

    /**
     * Hand the test data to the runner, which passes them back through the sole
     * constructor of this test class.
     *
     * @return all test data.
     */
    @Parameters
    public static TestData[] getTestData() {
        return testData;

    }

    /**
     * Data set for one test.
     */
    static class TestData {

        /**
         * Starting state.
         */
        final State initial;
        /**
         * End state, if not null, Null signal that state change should not be
         * called.
         */
        final State end;
        /**
         * Trigger. Specified as a {@link java.util.function.BiConsumer<T,U>} to
         * allow us to pass in both context and state. The body of the method
         * returns nothing, so all in all, the lambda expression has the shape
         * of a BiConsumer.
         */
        final BiConsumer<Context, State> triggerAction;
        /**
         * The value for the guard expression [empty], trained to the context.
         */
        final boolean empty;
        /**
         * The value for the guard expression [winner], trained to the context.
         */
        final boolean winner;
        /**
         * The count for the draw method on the context. Used in the verify
         * steps.
         */
        final int drawCount;
        /**
         * The count for the addBalls method on the context. Used in the verify
         * steps.
         */
        final int addBallsCount;

        /**
         * Ctor taking all
         *
         * @param initial see field doc
         * @param end see field doc
         * @param triggerAction see field doc
         * @param empty see field doc
         * @param winner see field doc
         * @param drawCount see field doc
         * @param refillsCount see field doc
         */
        public TestData( State initial, State end, BiConsumer<Context, State> triggerAction,
                boolean empty, boolean winner, int drawCount, int refillsCount ) {
            this.initial = initial;
            this.end = end;
            this.triggerAction = triggerAction;
            this.winner = winner;
            this.empty = empty;
            this.drawCount = drawCount;
            this.addBallsCount = refillsCount;
        }

        static TestData td( State initial, State end, BiConsumer<Context, State> triggerAction,
                boolean empty, boolean winner, int drawCount, int refillsCount ) {
            return new TestData( initial, end, triggerAction,
                    empty, winner, drawCount, refillsCount );
        }
    }
}
