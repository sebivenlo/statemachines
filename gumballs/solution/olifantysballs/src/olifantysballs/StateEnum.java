package olifantysballs;

/**
 * Implements State behaviors.
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public enum StateEnum implements State {
    
    NoCoin {
        //Start Solution::replacewith:://TODO 
        @Override
        public void insertCoin( Context gbc ) {
            gbc.getOutput().println( "You inserted a coin" );
            gbc.changeState( HasCoin );
        }
        
        @Override
        public String reason() {
            return "You must put in a coin before you can continue";
        }
        //End Solution::replacewith::
    }, 
    HasCoin {
        //Start Solution::replacewith:://TODO 
        @Override
        public void ejectCoin( Context gbc ) {
            gbc.getOutput().println( "Quarter returned" );
            gbc.changeState( NoCoin );
        }
        
        @Override
        public void draw( Context gbc ) {
            System.out.println( "draw in hasquarter" );
            gbc.dispense();
            if ( gbc.isEmpty() ) {
                gbc.changeState( SoldOut );
            } else if ( gbc.isWinner()) {
                gbc.changeState( Winner );
            } else {
                gbc.changeState( NoCoin );
            }
        }
        
        @Override
        public String reason() {
            return "Have a coin already, please draw to get your ball";
        }
        //End Solution::replacewith::
    }, 
    SoldOut {
        //Start Solution::replacewith:://TODO 
        @Override
        public void refill( Context gbm, int count ) {
            super.refill( gbm, count );
            gbm.changeState( NoCoin );
        }
        
        @Override
        public String reason() {
            return "Machine is empty, waiting for refill";
        }
        //End Solution::replacewith::
    }, 
    Winner {
        //Start Solution::replacewith:://TODO 
        @Override
        public void draw( Context gbc ) {
            gbc.getOutput().println( "YOU\'RE A WINNER! "
                    + "You got two gumballs for your coin" );
            gbc.dispense();
            if ( gbc.isEmpty() ) {
                gbc.changeState( SoldOut );
            } else {
                gbc.changeState( NoCoin );
            }
        }
        
        @Override
        public String reason() {
            return "You should draw once more to get extra ball";
        }
        //End Solution::replacewith::
    };
  
}
