package statestack;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public enum StateEnum implements CCState {
    IDLE {
        @Override
        public void engineOn( Context ctx ) {
            ctx.changeFromToState( "engineOn", this, ENGINERUNNING );
        }
    },
    ENGINERUNNING {
        @Override
        public void enter( Context ctx ) {
            ctx.getDevice().startEngine();
            ctx.addState( STANDBY );
        }

        @Override
        public void engineOff( Context ctx ) {
            ctx.changeFromToState( "engineOff", this, IDLE );
        }

        @Override
        public void exit( Context ctx ) {
            ctx.getDevice().stopEngine();
        }

    },
    STANDBY {
        @Override
        public void accelerate( Context ctx ) {
            ctx.changeFromToState( "accelerate", this, AUTOMATEDCONTROL );
        }
    },
    AUTOMATEDCONTROL {
        @Override
        public void enter( Context ctx ) {
            ctx.addState( ACCELERATING );
        }

        @Override
        public void brakePressed( Context ctx ) {
            ctx.changeFromToState( "brake", this, CRUISINGOFF );
        }

    },
    ACCELERATING {
        @Override
        public void cruise( Context ctx ) {
            ctx.changeFromToState( "cruise", this, NOTACCELERATING );
        }
    },
    NOTACCELERATING {
        @Override
        public void enter( Context ctx ) {
            ctx.addState( CRUISING );
        }

        @Override
        public void accelerate( Context ctx ) {
            ctx.changeFromToState( "accelerate", this, ACCELERATING );
        }

    },
    RESUMING {
        @Override
        public void cruiseSpeedReached( Context ctx ) {
            ctx.changeFromToState( "cruiseSpeedReached", this, CRUISING );
        }
    },
    CRUISINGOFF {
        @Override
        public void accelerate( Context ctx ) {
            if ( !ctx.getDevice().isBraking() ) {
                ctx.changeFromToState( "accelerate", this, AUTOMATEDCONTROL );
            }
        }

        @Override
        public void resume( Context ctx ) {
            ctx.changeFromToState( "resume", this, AUTOMATEDCONTROL, NOTACCELERATING, RESUMING );
        }

    },
    /**
     * CRUISING has on directly outgoing transitions, so does nothing.
     */
    CRUISING,
    /**
     * NULL has an "implementation for all event/methods.
     */
    NULL {

        @Override
        public void cruise( Context ctx ) {
            System.out.println( "in state " + ctx.logicalState()
                    + " ignored event cruise()" );
        }

        @Override
        public void cruiseSpeedReached( Context ctx ) {
            System.out.println( "in state " + ctx.logicalState()
                    + " ignored event cruiseSpeedReached()" );
        }

        @Override
        public void brakePressed( Context ctx ) {
            System.out.println( "in state " + ctx.logicalState()
                    + " ignored event brakePressed()" );
        }

        @Override
        public void engineOn( Context ctx ) {
            System.out.println( "in state " + ctx.logicalState()
                    + " ignored event engineOn" );
        }

        @Override
        public void engineOff( Context ctx ) {
            System.out.println( "in state " + ctx.logicalState()
                    + " ignored event engineOff" );
        }

        @Override
        public void resume( Context ctx ) {
            System.out.println( "in state " + ctx.logicalState()
                    + " ignored event resume" );
        }

        @Override
        public void accelerate( Context ctx ) {
            System.out.println( "in state  " + ctx.logicalState()
                    + " ignored event accelerate" );
        }
    };
}
