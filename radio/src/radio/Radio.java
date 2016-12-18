package radio;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public class Radio implements RadioOps{

    private final RadioContext rctx = new RadioContext();

    @Override
    public void powerOn() {
        rctx.powerOn();
    }

    @Override
    public void powerOff() {
        rctx.powerOff();
    }

    @Override
    public void idleButtonAction() {
        rctx.idleButtonAction();
    }

    @Override
    public void radioButtonAction() {
        rctx.radioButtonAction();
    }

    @Override
    public void alarmButtonAction() {
        rctx.alarmButtonAction();
    }

    @Override
    public void alarmTimeReached() {
        rctx.alarmTimeReached();
    }

    @Override
    public void stopAlarmButtonAction() {
        rctx.stopAlarmButtonAction();
    }

}
