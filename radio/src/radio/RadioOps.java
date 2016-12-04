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
public interface RadioOps {
     void powerOn() ;
    void powerOff();
     void idleButtonPressed();
     void radioButtonPressed();
     void alarmButtonPressed();
     void alarmTimeReached();
     void stopAlarmButtonPressed();
}
