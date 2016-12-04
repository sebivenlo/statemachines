/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    public void idleButtonPressed() {
        rctx.idleButtonPressed();
    }

    @Override
    public void radioButtonPressed() {
        rctx.radioButtonPressed();
    }

    public void alarmButtonPressed() {
        rctx.alarmButtonPressed();
    }

    public void alarmTimeReached() {
        rctx.alarmTimeReached();
    }

    public void stopAlarmButtonPressed() {
        rctx.stopAlarmButtonPressed();
    }

}
