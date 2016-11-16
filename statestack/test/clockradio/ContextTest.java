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
    public void switchToRadioAlarm(){
        context.radioAlarmButtonPressed();
        assertEquals( "Playing radio", "POWERED.RADIO_ALARM.RADIO_ALARM_IDLE", context.logicalState() );
    }
    
    @Test
    public void switchToBuzzerAlarm(){
        context.buzzerAlarmButtonPressed();
        assertEquals( "Playing radio", "POWERED.BUZZER_ALARM.BUZZER_ALARM_IDLE", context.logicalState() );
    }

    @Test
    public void turnRadioAlarmOff(){
        context.radioAlarmButtonPressed();
        context.alarmTimeReached();
        context.idleButtonPressed();
        assertEquals( "idleing", "POWERED.IDLE_MODE", context.logicalState() );
    }

    @Test(expected = IllegalArgumentException.class)
    public void forceStateException(){
        context.leaveState( ClockRadioStateEnum.NOT_POWERED);
    }
}
