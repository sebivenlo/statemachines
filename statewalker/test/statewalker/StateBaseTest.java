package statewalker;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public class StateBaseTest {
    interface S extends StateBase<TestContext, Dev, S> {
    }
     enum TestState implements  S{
         X;

         @Override
        public TestState getNullState() {
            return null;
        }
    }
    
    static class TestContext extends ContextBase<TestContext,Dev,S>{

        public TestContext( Dev device, Class stateClass ) {
            super( device, stateClass );
        }
    }
    
    static class Dev implements Device<TestContext,Dev,S>{}
    
    S sb = TestState.X;
    TestContext cb = new TestContext(new Dev(), TestState.class);
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
