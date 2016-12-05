
package radio;

import static radio.RadioStateEnum.*;
/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
enum RadioStateEnum implements RadioState {
    NOT_POWERED {
        @Override
        public void powerOn( RadioContext ctx ) {
            ctx.changeFromToState( "power plugged", this, POWERED );
        }
        // TODO
        
    },
    POWERED {
        @Override
        public void enter( RadioContext ctx ) {
            ctx.getDevice().amplifier( true );
        }
        
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
        public void enter( RadioContext ctx ) {
            System.out.println( "in state  " + ctx.logicalState() + " can not enter NULL." );
        }


        @Override
        public void exit( RadioContext ctx ) {
            System.out.println( "in state  " + ctx.logicalState() + " can not exit NULL." );
        }

        @Override
        public void powerOn( RadioContext ctx ) {
            System.out.println( "in state  " + ctx.logicalState() + " ignored event powerOn." );
        }

        @Override
        public void powerOff( RadioContext ctx ) {
            System.out.println( "in state  " + ctx.logicalState() + " ignored event powerOff." );
        }

        @Override
        public void idleButtonPressed( RadioContext ctx ) {
            System.out.println( "in state  " + ctx.logicalState() + " ignored event idleButtonPressed." );
        }

        @Override
        public void radioButtonPressed( RadioContext ctx ) {
            System.out.println( "in state  " + ctx.logicalState() + " ignored event radioButtonPressed." );
        }

        @Override
        public void alarmButtonPressed( RadioContext ctx ) {
            System.out.println( "in state  " + ctx.logicalState() + " ignored event alarmButtonPressed." );
        }

        @Override
        public void alarmTimeReached( RadioContext ctx ) {
            System.out.println( "in state  " + ctx.logicalState() + " ignored event alarmTimeReached." );
        }

        @Override
        public void stopAlarmButtonPressed( RadioContext ctx ) {
            System.out.println( "in state  " + ctx.logicalState() + " ignored event stopAlarmButtonPressed." );
        }

    };

    @Override
    public RadioState getNullState() {
        return NULL;
    }
}
