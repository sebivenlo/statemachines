package cruisecontrol;

import statestack.ContextBase;
import statestack.StateBase;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public interface CCState extends StateBase{
    
    default void accelerate(ContextBase<CCState, Device> ctx){ctx.superState( this ).accelerate( ctx );};
    default void resume(ContextBase<CCState, Device> ctx){ctx.superState( this ).resume( ctx );};
    default void engineOff(ContextBase<CCState, Device> ctx){ctx.superState( this ).engineOff( ctx );};
    default void engineOn(ContextBase<CCState, Device> ctx){ctx.superState( this ).engineOn( ctx );};
    default void brakePressed(ContextBase<CCState, Device> ctx){ctx.superState( this ).brakePressed( ctx );};
    default void cruiseSpeedReached(ContextBase<CCState, Device> ctx){ctx.superState( this ).cruiseSpeedReached( ctx );};
    default void cruise(ContextBase<CCState, Device> ctx){ctx.superState( this ).cruise( ctx );};
}
