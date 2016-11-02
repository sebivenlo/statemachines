/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statestack;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public class Main {
            
    /**
     * @param args the command line arguments
     */
    public static void main( String[] args ) {
        // TODO code application logic here
        System.out.println( "======= new" );
        Context cc = new Context();
        cc.accelerate();
        System.out.println( "======= turn on 1" );
        cc.engineOn();
        cc.accelerate();
        System.out.println( "======= accelerate 1" );
        cc.accelerate();
        System.out.println( "======= engine off 1 " );
        cc.engineOff();
        
        System.out.println( "======= turn on 2" );
        cc.engineOn();
        System.out.println( "======= accelerate 2" );
        cc.accelerate();
        System.out.println( "======= cruise 1" );
        cc.cruise();
        System.out.println( "======= press brake " );
        cc.brake();
        System.out.println( "======= engine off  2" );
        cc.engineOff();
        
        System.out.println( "======= engine on " );
        cc.engineOn();
        System.out.println( "======= accelerate 3" );
        cc.accelerate();
        System.out.println( "======= cruise 2" );
        cc.cruise();
        System.out.println( "======= accelerate in cruise " );
        cc.accelerate();

        System.out.println( "======= accelerate in accelerate" );
        cc.accelerate();
    }
    
}
