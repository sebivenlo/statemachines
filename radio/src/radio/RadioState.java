
package radio;

import statewalker.StateBase;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
interface RadioState extends StateBase<RadioContext,RadioDevice,RadioState>{
    default void powerOn(RadioContext ctx) {ctx.superState(this).powerOn(ctx);};
    default void powerOff(RadioContext ctx) {ctx.superState(this).powerOff(ctx);};
    default void idleButtonAction(RadioContext ctx)  {ctx.superState(this).idleButtonAction(ctx);};
    default void radioButtonAction(RadioContext ctx) {ctx.superState(this).radioButtonAction(ctx);};
    default void alarmButtonAction(RadioContext ctx) {ctx.superState(this).alarmButtonAction(ctx);};
    default void alarmTimeReached(RadioContext ctx)   {ctx.superState(this).alarmTimeReached(ctx);};
    default void stopAlarmButtonAction(RadioContext ctx) {ctx.superState(this).stopAlarmButtonAction(ctx);};
  
    @Override
    default void exit( RadioContext ctx ){}
    @Override
    default void enter( RadioContext ctx ){};
    
}
