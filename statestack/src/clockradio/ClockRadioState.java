package clockradio;

import statestack.ContextBase;
import statestack.StateBase;

/**
 *
 * @author Jeroen (GitHub: jbeulen)
 */
interface ClockRadioState extends StateBase<ClockRadioContext, ClockRadioDevice, ClockRadioState> {
    
    default void powerOn(ClockRadioContext ctx)                  {ctx.superState(this).powerOn(ctx);};
    default void powerOff(ClockRadioContext ctx)                 {ctx.superState(this).powerOff(ctx);};
    default void idleButtonPressed(ClockRadioContext ctx)        {ctx.superState(this).idleButtonPressed(ctx);};
    default void radioButtonPressed(ClockRadioContext ctx)       {ctx.superState(this).radioButtonPressed(ctx);};
    default void radioAlarmButtonPressed(ClockRadioContext ctx)  {ctx.superState(this).radioAlarmButtonPressed(ctx);};
    default void buzzerAlarmButtonPressed(ClockRadioContext ctx) {ctx.superState(this).buzzerAlarmButtonPressed(ctx);};
    default void alarmButtonPressed(ClockRadioContext ctx)       {ctx.superState(this).alarmButtonPressed(ctx);};
    default void alarmButtonReleased(ClockRadioContext ctx)      {ctx.superState(this).alarmButtonReleased(ctx);};
    default void alarmTimeReached(ClockRadioContext ctx)         {ctx.superState(this).alarmTimeReached(ctx);};
    default void snoozeButtonPressed(ClockRadioContext ctx)      {ctx.superState(this).snoozeButtonPressed(ctx);};
    default void snoozeTimeout(ClockRadioContext ctx)            {ctx.superState(this).snoozeTimeout(ctx);};
    
    @Override
    default void exit( ClockRadioContext ctx ){}

    
    @Override
    default void enter( ClockRadioContext ctx ){};
}
