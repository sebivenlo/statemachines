package clockradio;

import statestack.ContextBase;

/**
 *
 * @author Jeroen (GitHub: jbeulen)
 */
public enum ClockRadioStateEnum implements ClockRadioState {
    NOT_POWERED {
        @Override
        public void powerOn(ContextBase<ClockRadioState, ClockRadioDevice> ctx) {
            ctx.changeFromToState("Power on", this, POWERED);
        }
       
    },
    POWERED {
        @Override
        public void enter(ContextBase ctx) {
            ctx.addState(IDLE_MODE);
        }

        @Override
        public void idleButtonPressed(ContextBase<ClockRadioState, ClockRadioDevice> ctx) {
            ctx.innerTransition("idleButtonPressed", this, IDLE_MODE);
        }

        @Override
        public void alarmButtonPressed(ContextBase<ClockRadioState, ClockRadioDevice> ctx) {
            ctx.innerTransition("idleButtonPressed", this, ALARM_MODE);
        }

        @Override
        public void radioButtonPressed(ContextBase<ClockRadioState, ClockRadioDevice> ctx) {
            ctx.innerTransition("idleButtonPressed", this, RADIO_MODE);
        }

        @Override
        public void powerOff(ContextBase<ClockRadioState, ClockRadioDevice> ctx) {
            ctx.changeFromToState("Power off", this, NOT_POWERED);
        }
        
    },
    IDLE_MODE {
        // TODO
        
    },
    RADIO_MODE {
        @Override
        public void enter(ContextBase ctx) {
            //TODO device start playing
            ((ClockRadioDevice)ctx.getDevice()).radio(true);
        }
        // TODO

        @Override
        public void exit(ContextBase ctx) {
            //TODO stop the radio
            ((ClockRadioDevice)ctx.getDevice()).radio(false);
        }
        
    },
    ALARM_MODE {
        @Override
        public void enter(ContextBase ctx) {
            ctx.addState(ALARM_IDLE);
        }
        
    },
    ALARM_IDLE {
        @Override
        public void alarmTimeReached(ContextBase<ClockRadioState, ClockRadioDevice> ctx) {
            ctx.changeFromToState("Alarm time", this, ALARM_BUZZING);
        }
    },
    ALARM_BUZZING {
        @Override
        public void enter(ContextBase ctx) {
            ((ClockRadioDevice)ctx.getDevice()).startBuzzing();
        }

        @Override
        public void exit(ContextBase ctx) {
            ((ClockRadioDevice)ctx.getDevice()).stopBuzzing();
        }

        @Override
        public void stopAlarmButtonPressed(ContextBase<ClockRadioState, ClockRadioDevice> ctx) {
            ctx.changeFromToState("Stop buzzing", this, ALARM_IDLE);
        }
        
        
        
        
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
