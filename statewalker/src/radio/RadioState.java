
package radio;

import statewalker.ContextBase;
import statewalker.StateBase;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public interface RadioState extends StateBase<RadioContext,RadioDevice,RadioState>{
    default void powerOn(RadioContext ctx) {ctx.superState(this).powerOn(ctx);};
    default void powerOff(RadioContext ctx) {ctx.superState(this).powerOff(ctx);};
    default void idleButtonPressed(RadioContext ctx)  {ctx.superState(this).idleButtonPressed(ctx);};
    default void radioButtonPressed(RadioContext ctx) {ctx.superState(this).radioButtonPressed(ctx);};
    default void alarmButtonPressed(RadioContext ctx) {ctx.superState(this).alarmButtonPressed(ctx);};
    default void alarmTimeReached(RadioContext ctx)   {ctx.superState(this).alarmTimeReached(ctx);};
    default void stopAlarmButtonPressed(RadioContext ctx) {ctx.superState(this).stopAlarmButtonPressed(ctx);};

    
    default void exit( RadioContext ctx ){}

    
    default void enter( RadioContext ctx ){};
    
}
