//package cruisecontrol;
//
//import statestack.ContextBase;
//import static cruisecontrol.StateEnum.*;
//
///**
// *
// * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
// */
//public class Context extends ContextBase<CCState, Device>{
//
//    public Context() {
//        super(new Device());
//        stateStack.push(NULL);
//        addState(IDLE);
//        System.out.println("current state is: " + stateStack.peek().toString());
//    }
//    
//    void engineOn() {
//        stateStack.peek().engineOn( this );
//    }
//
//    void accelerate() {
//        stateStack.peek().accelerate( this );
//    }
//
//    void engineOff() {
//        stateStack.peek().engineOff( this );
//    }
//
//    void cruise() {
//        stateStack.peek().cruise( this );
//    }
//
//    void brake() {
//        stateStack.peek().brakePressed( this );
//    }
//
//}
