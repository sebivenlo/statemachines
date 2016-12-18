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
    public void turnOff(){
        context.powerOff();
        assertEquals( "Powered off", "NOT_POWERED", context.logicalState() );
        
    }
    
    @Test
    public void turnOn(){
        context.powerOff();
        context.alarmButtonPressed();
        assertEquals( "Powered off, did not react to alarmButtonPressed", "NOT_POWERED", context.logicalState() );
        context.powerOn();
        assertEquals( "Powered on", "POWERED.IDLE_MODE", context.logicalState() );
    }
    
    @Test
    public void switchToPlayRadio(){
        context.radioButtonPressed();
        assertEquals( "Playing radio", "POWERED.RADIO_MODE", context.logicalState() );
    }
    
    @Test
    public void playAlarm(){
        context.alarmButtonPressed();
        context.alarmTimeReached();
        assertEquals( "Playing alarm", "POWERED.ALARM_MODE.ALARM_BUZZING", context.logicalState() );
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void forceStateException(){
        context.leaveState( ClockRadioStateEnum.NOT_POWERED);
    }
}
