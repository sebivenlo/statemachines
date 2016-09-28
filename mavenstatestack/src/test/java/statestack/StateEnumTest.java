package statestack;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;

import static statestack.StateEnum.*;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
@RunWith( MockitoJUnitRunner.class )
public class StateEnumTest {

    @Mock
    CCState testNull;

    @Mock
    Context context;
    
    @Mock 
    Device testDevice;

    @Before
    public void setup() {
        when( context.superState( any() ) ).thenReturn( testNull );
        when(context.getDevice()).thenReturn( testDevice);
        
    }

    /**
     * Test. Ensure that superState is called. That accelerate is called on
     * context. That no state change occurs.
     *
     */
    @Test
    public void testCruising_accelerate() {
        CCState cruising = CRUISING;
        cruising.accelerate( context );
        verify( context, times( 1 ) ).superState( cruising );
        verify( testNull, times( 1 ) ).accelerate( context );
        verify( context, times( 0 ) ).changeFromToState( "accelerate", cruising );
    }

    @Test
    public void testStandBy_accel() {
        CCState standby = STANDBY;
        //when( testDevice.isBraking() ).thenReturn( false );
        standby.accelerate( context );
        verify( context, times( 1 ) ).changeFromToState( "accelerate", standby, AUTOMATEDCONTROL );
    }

    @Test
    public void testNotAccelerating_accel() {
        CCState state = NOTACCELERATING;
       // when( testDevice.isBraking() ).thenReturn( false );
        state.accelerate( context );
        verify( context, times( 1 ) ).changeFromToState( "accelerate", state, ACCELERATING );
    }

    @Test
    public void testRunningToOff() {
        CCState state = ENGINERUNNING;
        state.engineOff( context );
        verify( context, times( 1 ) ).changeFromToState( "engineOff", state, IDLE );
    }

    @Test
    public void testCruisingBraking() {
        CCState state = CRUISINGOFF;
        when( testDevice.isBraking() ).thenReturn( true );
        state.accelerate(context );
        verify( context, never() ).changeFromToState( "accelerate", state, AUTOMATEDCONTROL );
    }

    @Test
    public void speedReached() {
        CCState state = RESUMING;
        state.cruiseSpeedReached( context );
        verify( context, times( 1 ) ).changeFromToState( "cruiseSpeedReached", state, CRUISING );

    }

    @Test
    public void cruisingOffResume() {
        CCState state = CRUISINGOFF;
        state.resume( context );
        verify( context, times( 1 ) ).changeFromToState( "resume", state, AUTOMATEDCONTROL, NOTACCELERATING, RESUMING );

    }

    @Test
    public void cruisingOffAccel() {
        CCState state = CRUISINGOFF;
        state.accelerate( context );
        verify( context, times( 1 ) ).changeFromToState( "accelerate", state, AUTOMATEDCONTROL );

    }

    @Test
    public void nullCoverage() {
        CCState state = NULL;
        state.cruise( context );
        state.cruiseSpeedReached( context );
        state.brakePressed( context );
        state.engineOn( context );
        state.engineOff( context );
        state.resume( context );
        state.accelerate( context );
    }

    @Test
    public void testValues() {
        assertEquals( 10, StateEnum.values().length );
        assertEquals( NULL, StateEnum.valueOf( "NULL" ) );
    }
}
