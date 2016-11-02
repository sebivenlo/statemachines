package statestack;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public class ContextTest {

    Context context;

    @Before
    public void setup() {
        context = new Context();
    }

    @Test
    public void initialContext() {
        assertEquals( "check initial", "IDLE", context.logicalState() );

    }

    @Test
    public void engineOnToSBY() {
        context.engineOn();
        assertEquals( "after engine on", "ENGINERUNNING.STANDBY", context.logicalState() );

    }

    @Test
    public void stdbyAccel() {
        context.engineOn();
        context.accelerate();
        assertEquals( "after accelerate", "ENGINERUNNING.AUTOMATEDCONTROL.ACCELERATING", context.logicalState() );
    }

    @Test
    public void cruisingBrake(){
        context.engineOn();
        context.accelerate();
        context.cruise();
        assertEquals( "cruising", "ENGINERUNNING.AUTOMATEDCONTROL.NOTACCELERATING.CRUISING", context.logicalState() );
        context.brake();
        assertEquals( "not cruising", "ENGINERUNNING.CRUISINGOFF", context.logicalState() );
        
    }

    @Test
    public void stdndByToEngineOff(){
        context.engineOn();
        context.engineOff();
        assertEquals( "engineOff", "IDLE", context.logicalState() );
    }
    
    @Test
    public void cruisingOffAccel(){
        context.engineOn();
        context.accelerate();
        context.brake();
        assertEquals( "brake ", "ENGINERUNNING.CRUISINGOFF", context.logicalState() );
        context.accelerate();
        assertEquals( "after accelerate", "ENGINERUNNING.AUTOMATEDCONTROL.ACCELERATING", context.logicalState() );
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void forceStateException(){
        context.leaveState( StateEnum.ENGINERUNNING);
    }
}
