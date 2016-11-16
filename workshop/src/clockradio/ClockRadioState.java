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
    default void alarmButtonPressed(ContextBase<ClockRadioState, ClockRadioDevice> ctx)       {ctx.superState(this).alarmButtonPressed(ctx);};
    default void alarmTimeReached(ContextBase<ClockRadioState, ClockRadioDevice> ctx)         {ctx.superState(this).alarmTimeReached(ctx);};
    default void stopAlarmButtonPressed(ContextBase<ClockRadioState, ClockRadioDevice> ctx)      {ctx.superState(this).stopAlarmButtonPressed(ctx);};
}
