package clockradio;

import statestack.ContextBase;
import statestack.StateBase;

/**
 *
 * @author Jeroen (GitHub: jbeulen)
 */
interface ClockRadioState extends StateBase {
    
    default void powerOn(ContextBase<ClockRadioState, ClockRadioDevice> ctx)                  {ctx.superState(this).powerOn(ctx);};
    default void powerOff(ContextBase<ClockRadioState, ClockRadioDevice> ctx)                 {ctx.superState(this).powerOff(ctx);};
    default void idleButtonPressed(ContextBase<ClockRadioState, ClockRadioDevice> ctx)        {ctx.superState(this).idleButtonPressed(ctx);};
    default void radioButtonPressed(ContextBase<ClockRadioState, ClockRadioDevice> ctx)       {ctx.superState(this).radioButtonPressed(ctx);};
    default void radioAlarmButtonPressed(ContextBase<ClockRadioState, ClockRadioDevice> ctx)  {ctx.superState(this).radioAlarmButtonPressed(ctx);};
    default void buzzerAlarmButtonPressed(ContextBase<ClockRadioState, ClockRadioDevice> ctx) {ctx.superState(this).buzzerAlarmButtonPressed(ctx);};
    default void alarmButtonPressed(ContextBase<ClockRadioState, ClockRadioDevice> ctx)       {ctx.superState(this).alarmButtonPressed(ctx);};
    default void alarmButtonReleased(ContextBase<ClockRadioState, ClockRadioDevice> ctx)      {ctx.superState(this).alarmButtonReleased(ctx);};
    default void alarmTimeReached(ContextBase<ClockRadioState, ClockRadioDevice> ctx)         {ctx.superState(this).alarmTimeReached(ctx);};
    default void snoozeButtonPressed(ContextBase<ClockRadioState, ClockRadioDevice> ctx)      {ctx.superState(this).snoozeButtonPressed(ctx);};
    default void snoozeTimeout(ContextBase<ClockRadioState, ClockRadioDevice> ctx)            {ctx.superState(this).snoozeTimeout(ctx);};
}
