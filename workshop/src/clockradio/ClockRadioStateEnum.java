package clockradio;

import statestack.ContextBase;

/**
 *
 * @author Jeroen (GitHub: jbeulen)
 */
public enum ClockRadioStateEnum implements ClockRadioState {
    NOT_POWERED {
        // TODO
    },
    POWERED {
        // TODO
    },
    IDLE_MODE {
        // TODO
    },
    RADIO_MODE {
        // TODO
    },
    ALARM_MODE {
        // TODO
    },
    ALARM_IDLE {
        // TODO

    },
    ALARM_BUZZING {
        // TODO
    },
    NULL {
        @Override
        public void enter(ContextBase ctx) {
            System.out.println("in state  " + ctx.logicalState() + " can not enter NULL.");
        }

        @Override
        public void exit(ContextBase ctx) {
            System.out.println("in state  " + ctx.logicalState() + " can not exit NULL.");
        }

        @Override
        public void powerOn(ContextBase ctx) {
            System.out.println("in state  " + ctx.logicalState() + " ignored event powerOn.");
        }

        @Override
        public void powerOff(ContextBase ctx) {
            System.out.println("in state  " + ctx.logicalState() + " ignored event powerOff.");
        }

        @Override
        public void idleButtonPressed(ContextBase ctx) {
            System.out.println("in state  " + ctx.logicalState() + " ignored event idleButtonPressed.");
        }

        @Override
        public void radioButtonPressed(ContextBase ctx) {
            System.out.println("in state  " + ctx.logicalState() + " ignored event radioButtonPressed.");
        }

        @Override
        public void alarmButtonPressed(ContextBase ctx) {
            System.out.println("in state  " + ctx.logicalState() + " ignored event alarmButtonPressed.");
        }

        @Override
        public void alarmTimeReached(ContextBase<ClockRadioState, ClockRadioDevice> ctx) {
            System.out.println("in state  " + ctx.logicalState() + " ignored event alarmTimeReached.");
        }

        @Override
        public void stopAlarmButtonPressed(ContextBase<ClockRadioState, ClockRadioDevice> ctx) {
            System.out.println("in state  " + ctx.logicalState() + " ignored event stopAlarmButtonPressed.");
        }

    }
}

//SHOWING_CURRENT_TIME,
//SHOWING_ALARM_TIME,
