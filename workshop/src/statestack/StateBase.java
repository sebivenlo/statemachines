package statestack;

/**
 * Primary functions for a state machine
 * 
 * Define a default method that returns void.
 * 
 * Example: default void event(ContextBase<CCState, Device> ctx){ ctx.superState( this ).event( ctx );};
 * 
 * @author Sander
 */
public interface StateBase {
    default void enter(ContextBase ctx){}//System.out.println( "entering state "+this.toString() );};
    default void exit(ContextBase ctx){}//System.out.println( "leaving state "+this.toString() );};
    
}
