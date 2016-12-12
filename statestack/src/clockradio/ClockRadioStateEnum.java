package clockradio;

import statestack.ContextBase;

/**
 *
 * @author Jeroen (GitHub: jbeulen)
 */
public enum ClockRadioStateEnum implements ClockRadioState {
    NOT_POWERED {
        @Override
        public void powerOn(ClockRadioContext ctx) {
            ctx.changeFromToState("powerOn", this, POWERED);
            
        }
    },
    POWERED {
        @Override
        public void enter(ClockRadioContext ctx) {
            ctx.addState(ctx.getHistoryStateFrom(this, IDLE_MODE));
        }

        @Override
        public void idleButtonPressed(ClockRadioContext ctx) {
            ctx.innerTransition("idleButtonPressed", this, IDLE_MODE);
        }

        @Override
        public void radioButtonPressed(ClockRadioContext ctx) {
            ctx.innerTransition("radioButtonPressed", this, RADIO_MODE);
        }

        @Override
        public void radioAlarmButtonPressed(ClockRadioContext ctx) {
            ctx.innerTransition("alarmButtonPressed", POWERED, RADIO_ALARM);
        }

        @Override
        public void buzzerAlarmButtonPressed(ClockRadioContext ctx) {
            ctx.innerTransition("buzzerAlarmButtonPressed", this, BUZZER_ALARM);
        }

        @Override
        public void powerOff(ClockRadioContext ctx) {
            ctx.changeFromToState("powerOff", this, NOT_POWERED);
        }
    },
    IDLE_MODE,
    RADIO_MODE,
    RADIO_ALARM {
        @Override
        public void enter(ClockRadioContext ctx) {
            ctx.addState(ctx.getHistoryStateFrom(this, RADIO_ALARM_IDLE));
        }
    },
    BUZZER_ALARM {
        @Override
        public void enter(ClockRadioContext ctx) {
            ctx.addState(ctx.getHistoryStateFrom(this, BUZZER_ALARM_IDLE));
        }
    },
    RADIO_ALARM_IDLE {
        @Override
        public void alarmTimeReached(ClockRadioContext ctx) {
            ctx.changeFromToState("alarmTimeReached", this, RADIO_ALARM_PLAYING_RADIO);
        }
    },
    RADIO_ALARM_PLAYING_RADIO {
        @Override
        public void snoozeButtonPressed(ClockRadioContext ctx) {
            ctx.changeFromToState("snoozeButtonPressed", this, RADIO_ALARM_SNOOZING);
        }
    },
    RADIO_ALARM_SNOOZING {
        @Override
        public void snoozeTimeout(ClockRadioContext ctx) {
            ctx.changeFromToState("snoozeTimeout", this, RADIO_ALARM_PLAYING_RADIO);
        }
    },
    BUZZER_ALARM_IDLE {
        @Override
        public void alarmTimeReached(ClockRadioContext ctx) {
            ctx.changeFromToState("alarmTimeReached", this, BUZZER_ALARM_BUZZING);
        }
    },
    BUZZER_ALARM_BUZZING {
        @Override
        public void snoozeButtonPressed(ClockRadioContext ctx) {
            ctx.changeFromToState("snoozeButtonPressed", this, RADIO_ALARM_SNOOZING);
        }
    },
    BUZZER_ALARM_SNOOZING {
        @Override
        public void snoozeTimeout(ClockRadioContext ctx) {
            ctx.changeFromToState("snoozeTimeout", this, BUZZER_ALARM_BUZZING);
        }
    },
    NULL {
        @Override
        public void powerOn(ClockRadioContext ctx) {
            System.out.println("in state  " + ctx.logicalState() + " ignored event powerOn.");
        }

        @Override
        public void powerOff(ClockRadioContext ctx) {
            System.out.println("in state  " + ctx.logicalState() + " ignored event powerOff.");
        }

        @Override
        public void idleButtonPressed(ClockRadioContext ctx) {
            System.out.println("in state  " + ctx.logicalState() + " ignored event idleButtonPressed.");
        }

        @Override
        public void radioButtonPressed(ClockRadioContext ctx) {
            System.out.println("in state  " + ctx.logicalState() + " ignored event radioButtonPressed.");
        }

        @Override
        public void radioAlarmButtonPressed(ClockRadioContext ctx) {
            System.out.println("in state  " + ctx.logicalState() + " ignored event radioAlarmButtonPressed.");
        }

        @Override
        public void buzzerAlarmButtonPressed(ClockRadioContext ctx) {
            System.out.println("in state  " + ctx.logicalState() + " ignored event buzzerAlarmButtonPressed.");
        }

        @Override
        public void alarmButtonPressed(ClockRadioContext ctx) {
            System.out.println("in state  " + ctx.logicalState() + " ignored event alarmButtonPressed.");
        }

        @Override
        public void alarmButtonReleased(ClockRadioContext ctx) {
            System.out.println("in state  " + ctx.logicalState() + " ignored event alarmButtonReleased.");
        }

        @Override
        public void alarmTimeReached(ClockRadioContext ctx) {
            System.out.println("in state  " + ctx.logicalState() + " ignored event alarmTimeReached.");
        }

        @Override
        public void snoozeButtonPressed(ClockRadioContext ctx) {
            System.out.println("in state  " + ctx.logicalState() + " ignored event snoozeButtonPressed.");
        }

        @Override
        public void snoozeTimeout(ClockRadioContext ctx) {
            System.out.println("in state  " + ctx.logicalState() + " ignored event snoozeTimeout.");
        }
    }
}

//SHOWING_CURRENT_TIME,
//SHOWING_ALARM_TIME,
