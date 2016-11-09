package clockradio;

import statestack.ContextBase;

/**
 *
 * @author Jeroen (GitHub: jbeulen)
 */
public enum ClockRadioStateEnum implements ClockRadioState{
    NOT_POWERED {
        @Override
        public void powerOn( ContextBase ctx ) {
            ctx.changeFromToState( "powerOn", this, POWERED, IDLE_MODE );
        }
    },
    POWERED {
        @Override
        public void enter( ContextBase ctx ) {
            ctx.addState( IDLE_MODE );
            //ctx.changeFromToState( "enter", this, POWERED, IDLE_MODE );
        }
        @Override
        public void idleButtonPressed( ContextBase ctx ) {
            ctx.changeFromToState( "idleButtonPressed", this, POWERED, IDLE_MODE );
        }
        @Override
        public void radioButtonPressed( ContextBase ctx ) {
            ctx.changeFromToState( "radioButtonPressed", this, POWERED,  RADIO_MODE );
        }
        @Override
        public void alarmButtonPressed( ContextBase ctx ) {
            ctx.changeFromToState( "alarmButtonPressed", this, POWERED, RADIO_ALARM );
        }
        @Override
        public void buzzerAlarmButtonPressed( ContextBase ctx ) {
            ctx.changeFromToState( "buzzerAlarmButtonPressed", this, POWERED, BUZZER_ALARM );
        }
        @Override
        public void powerOff( ContextBase ctx ) {
            ctx.changeFromToState( "powerOff", this, NOT_POWERED );
        }
    },
    IDLE_MODE,
    RADIO_MODE,
    RADIO_ALARM {
        @Override
        public void enter( ContextBase ctx ) {
            ctx.addState( RADIO_ALARM_IDLE );
        }
    },
    BUZZER_ALARM {
        @Override
        public void enter( ContextBase ctx ) {
            ctx.addState( BUZZER_ALARM_IDLE );
        }
    },
    RADIO_ALARM_IDLE {
        @Override
        public void alarmTimeReached( ContextBase ctx ) {
            ctx.changeFromToState( "alarmTimeReached", this, RADIO_ALARM_PLAYING_RADIO );
        }
    },
    RADIO_ALARM_PLAYING_RADIO {
        @Override
        public void snoozeButtonPressed( ContextBase ctx ) {
            ctx.changeFromToState( "snoozeButtonPressed", this, RADIO_ALARM_SNOOZING );
        }
    },
    RADIO_ALARM_SNOOZING {
        @Override
        public void snoozeTimeout( ContextBase ctx ) {
            ctx.changeFromToState( "snoozeTimeout", this, RADIO_ALARM_PLAYING_RADIO );
        }
    },
    BUZZER_ALARM_IDLE {
        @Override
        public void alarmTimeReached( ContextBase ctx ) {
            ctx.changeFromToState( "alarmTimeReached", this, BUZZER_ALARM_BUZZING );
        }
    },
    BUZZER_ALARM_BUZZING {
        @Override
        public void snoozeButtonPressed( ContextBase ctx ) {
            ctx.changeFromToState( "snoozeButtonPressed", this, RADIO_ALARM_SNOOZING );
        }
    },
    BUZZER_ALARM_SNOOZING {
        @Override
        public void snoozeTimeout( ContextBase ctx ) {
            ctx.changeFromToState( "snoozeTimeout", this, BUZZER_ALARM_BUZZING );
        }
    },
    NULL
}

//SHOWING_CURRENT_TIME,
//SHOWING_ALARM_TIME,