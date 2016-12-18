/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clockradio;

import statestack.DeviceBase;

/**
 *
 * @author Jeroen (GitHub: jbeulen)
 */
public class ClockRadioDevice implements DeviceBase {

    public void startBuzzing() {
        System.out.println("BUZZZ!");
    }

    public void stopBuzzing() {
        System.out.println("I will be quiet now....");
    }
    
    public void radio(boolean state){}
}
