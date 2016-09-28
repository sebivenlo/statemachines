package statestack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author hom
 */
public class StateStackTest extends StackTestBase {

    @Override
    StateStack<String> createInstance() {
        return new StateStack<>();
    }

    @Test
    public void watch_it_grow() {

        StateStack<String> stack = ( StateStack<String> ) createInstance();
        int ic = stack.capacity();
        System.out.println( "cap=" + stack.capacity() );
        String[] testData = { "A", "B", "C", "D", "E" };
        for ( String s : testData ) {
            stack.push( s );
        }

        System.out.println( "size=" + stack.size() );
        System.out.println( "cap=" + stack.capacity() );
        assertEquals( "grow by factor 2", 2 * ic, stack.capacity() );
    }

    /**
     * This the testers way of ensuring proper exception throwing. If the
     * exception is not thrown or the wrong exception is thrown, the test will
     * fail (red).
     */
    @Test( expected = ArrayIndexOutOfBoundsException.class )
    @Override
    public void empty_peek_should_bark() {
        super.empty_peek_should_bark();
    }

    @Test
    public void after_pop_elements_are_nulled_out() {
        StateStack<String> stack = ( StateStack<String> ) createInstance();
        String[] testData = { "A", "B", "C", "D", "E" };
        for ( String s : testData ) {
            stack.push( s );
        }

        // remove all elements
        for ( int i = testData.length - 1; i >= 0; i-- ) {
            String x = testData[ i ];
            assertEquals( "Datum off stack", x, stack.pop() );
            assertFalse( "and gone", stack.contains( x ) );
        }
    }

    @Test
    public void cover_contains() {
        StateStack<String> stack = ( StateStack<String> ) createInstance();
        String[] testData = { "A", "B", "C", "D", "E" };
        for ( String s : testData ) {
            stack.push( s );
        }
        assertFalse( "Not found", stack.contains( "Goner" ) );
        assertTrue( "In StateStack", stack.contains( testData[ 2 ] ) );

    }

    @Test
    public void peekDownEmpty() {
        StateStack<String> stack = ( StateStack<String> ) createInstance();
        String testDatum="A";
        assertFalse(stack.has( testDatum ));
        assertNull(stack.peekDownFrom( testDatum, 0 ));
    }
}
