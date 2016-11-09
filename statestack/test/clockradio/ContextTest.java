/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clockradio;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jeroen (GitHub: jbeulen)
 */
public class ContextTest {

    ClockRadioContext context;

    @Before
    public void setup() {
        context = new ClockRadioContext();
    }

    @Test
    public void initialContext() {
        assertEquals( "check initial", "POWERED.IDLE_MODE", context.logicalState() );

    }

    @Test
    public void cruisingBrake(){
        context.radioAlarmButtonPressed();
        context.alarmTimeReached();
        context.idleButtonPressed();
        System.out.println("____ " + context.logicalState());
        assertEquals( "cruising", "ENGINERUNNING.AUTOMATEDCONTROL.NOTACCELERATING.CRUISING", context.logicalState() );
        assertEquals( "not cruising", "ENGINERUNNING.CRUISINGOFF", context.logicalState() );
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void forceStateException(){
        context.leaveState( ClockRadioStateEnum.NOT_POWERED);
    }
}
