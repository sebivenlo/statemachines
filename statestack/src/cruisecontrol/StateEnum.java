package cruisecontrol;

import cruisecontrol.CCState;
import statestack.ContextBase;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public enum StateEnum implements CCState {
    IDLE {
        @Override
        public void engineOn( ContextBase ctx ) {
            ctx.changeFromToState( "engineOn", this, ENGINERUNNING );
        }
    },
    ENGINERUNNING {
        @Override
        public void enter( ContextBase ctx ) {
            ((Device)ctx.getDevice()).startEngine();
            ctx.addState( STANDBY );
        }

        @Override
        public void engineOff( ContextBase ctx ) {
            ctx.changeFromToState( "engineOff", this, IDLE );
        }

        @Override
        public void exit( ContextBase ctx ) {
            ((Device)ctx.getDevice()).stopEngine();
        }

    },
    STANDBY {
        @Override
        public void accelerate( ContextBase ctx ) {
            ctx.changeFromToState( "accelerate", this, AUTOMATEDCONTROL );
        }
    },
    AUTOMATEDCONTROL {
        @Override
        public void enter( ContextBase ctx ) {
            ctx.addState( ACCELERATING );
        }

        @Override
        public void brakePressed( ContextBase ctx ) {
            ctx.changeFromToState( "brake", this, CRUISINGOFF );
        }

    },
    ACCELERATING {
        @Override
        public void cruise( ContextBase ctx ) {
            ctx.changeFromToState( "cruise", this, NOTACCELERATING );
        }
    },
    NOTACCELERATING {
        @Override
        public void enter( ContextBase ctx ) {
            ctx.addState( CRUISING );
        }

        @Override
        public void accelerate( ContextBase ctx ) {
            ctx.changeFromToState( "accelerate", this, ACCELERATING );
        }

    },
    RESUMING {
        @Override
        public void cruiseSpeedReached( ContextBase ctx ) {
            ctx.changeFromToState( "cruiseSpeedReached", this, CRUISING );
        }
    },
    CRUISINGOFF {
        @Override
        public void accelerate( ContextBase ctx ) {
            if ( !((Device)ctx.getDevice()).isBraking() ) {
                ctx.changeFromToState( "accelerate", this, AUTOMATEDCONTROL );
            }
        }

        @Override
        public void resume( ContextBase ctx ) {
            ctx.changeFromToState( "resume", this, AUTOMATEDCONTROL, NOTACCELERATING, RESUMING );
        }

    },
    /**
     * CRUISING has one directly outgoing transitions, so does nothing.
     */
    CRUISING,
    /**
     * NULL has an "implementation for all event/methods.
     */
    NULL {

        @Override
        public void cruise( ContextBase ctx ) {
            System.out.println( "in state " + ctx.logicalState()
                    + " ignored event cruise()" );
        }

        @Override
        public void cruiseSpeedReached( ContextBase ctx ) {
            System.out.println( "in state " + ctx.logicalState()
                    + " ignored event cruiseSpeedReached()" );
        }

        @Override
        public void brakePressed( ContextBase ctx ) {
            System.out.println( "in state " + ctx.logicalState()
                    + " ignored event brakePressed()" );
        }

        @Override
        public void engineOn( ContextBase ctx ) {
            System.out.println( "in state " + ctx.logicalState()
                    + " ignored event engineOn" );
        }

        @Override
        public void engineOff( ContextBase ctx ) {
            System.out.println( "in state " + ctx.logicalState()
                    + " ignored event engineOff" );
        }

        @Override
        public void resume( ContextBase ctx ) {
            System.out.println( "in state " + ctx.logicalState()
                    + " ignored event resume" );
        }

        @Override
        public void accelerate( ContextBase ctx ) {
            System.out.println( "in state  " + ctx.logicalState()
                    + " ignored event accelerate" );
        }
    };
}
