/**
 * A enum bases state machine implementation of the <strong>GoF State</strong>
 * pattern.
 *
 * The package is designed with ease of use and type safety in mind, and
 * therefor can not completely avoid the complexity that goes with it. The
 * complexity left for the user is declaring the application specific Context,
 * Device, and State as a trisome in the generic declaration. To even further
 * reduce the complexity, the user is advised to declare State as an
 * intermediate interface that adds the application specific methods.
 *
 * In the example, app, the declarations of AppContext, AppDevice, and AppState
 * could then be as follows:
 * <pre>
 * {@code
 * class AppContext extends ContextBase<AppContext, AppDevice, State>
 * class AppDevice implements Device<AppContext, AppDevice, State>
 * interface State extends StateBase<AppContext, AppDevice, State>
 * enum AppState implements State
 *
 * }
 * </pre>
 *
 * <p>
 * When declaring your classes in this way you will by definition have some
 * cyclic dependencies, which cannot be avoided really easily, so it might take
 * some effort to convince the compiler it is okay. It may help to temporarily
 * turn off the IDE feature to compile on save.</p>
 *
 * <p>
 * The standard use is also shown in the class diagram below, where the
 * app-package is the users application package. The {@code <C,D,S,>} generic
 * tokens should be understood as alphabetic mnemonics for Context, Device and
 * State.</p>
 *
 * <img src='doc-files/statewalker.svg' alt='statewalker usage class diagram'>
 * <br>
 *
 * <p>
 * <b>Implementation detail.</b> The classes have been designed to reduce
 * complexity as much as possible, in particular using the generic annotation
 * needed.
 *
 * This applies more to the use of the package then to the package it self,
 * which cannot avoid some unchecked warnings in its implementation. In the
 * implementation some use of {@code @SuppresWarnings("unchecked")} cannot be
 * avoided, because we use arrays as internal storage and the varargs parameters
 * list is an array too. We consider using varargs to be applicable here. If the
 * user considers that wasteful in the sense of array object creation, he is
 * advised to declare static constants of type array of state and use these
 * values as parameter in the transition implementing methods. This will work
 * exactly as intended, and has the benefit that the array allocation is done
 * only once, at class loading time.
 * </p>
 *
 * State walker provides the following transition types:
 *
 * <h3>simple transition</h3>
 * <div style='float: left'>
 * <img src='doc-files/_1.svg'  alt='simple transistion' >
 *
 * <pre>
 * <code>
 *   A {
 *      {@literal @}Override
 *      public void e( Context ctx ) {
 *          ctx.changeFromToState( "e1", this, B );
 *      }
 *   }
 * </code>
 * </pre>
 *
 * </div>
 *
 * <div>
 * <img src='doc-files/_2.svg'  alt='simple transistion' >
 *
 * <pre>
 * <code>
 *   A {
 *      {@literal @}Override
 *      public void e( Context ctx ) {
 *          ctx.changeFromToState( "e1", this, B1, B2 );
 *      }
 *   }
 * </code>
 * </pre>
 *
 * </div>
 *
 * <div style='float: left'>
 * <img src='doc-files/_3a.svg'  alt='simple transistion' >
 *
 * <pre>
 * <code>
 *   A2 {
 *      {@literal @}Override
 *      public void e( Context ctx ) {
 *          ctx.changeFromToState( "e1", this, B);
 *      }
 *   }
 * </code>
 * </pre>
 * </div>
 *
 * <div>
 * <img src='doc-files/_3b.svg'  alt='simple transistion' >
 *
 * <pre>
 * <code>
 *   A1 {
 *      {@literal @}Override
 *      public void e( Context ctx ) {
 *          ctx.changeFromToState( "e1", this, B );
 *      }
 *   }
 * </code>
 * </pre>
 * </div>
 *
 * <h4>Simple transition between sub states</h4>
 * <img src='doc-files/_4.svg'  alt='simple transistion' >
 *
 * <pre>
 * <code>
 *   B21 {
 *      {@literal @}Override
 *      public void e( Context ctx ) {
 *          ctx.changeFromToState( "e1", this, B22 );
 *      }
 *   }
 * </code>
 * </pre>
 *
 * <h4>Inner transition</h4>
 * <p>When this transition is used the entry and exit methods will not be executed.</p>
 * <img src='doc-files/_5.svg'  alt='simple transistion' >
 *
 * <pre>
 * <code>
 *   B1 {
 *      {@literal @}Override
 *      public void e( Context ctx ) {
 *          ctx.changeFromToState( "e1", this, B22 );
 *      }
 *   }
 * </code>
 * </pre>
 *
 * <h4>Initial pseudo state</h4>
 * <p>By implementing the optional getInitailState we can define what the default state should be.</p>
 * <img src='doc-files/_6.svg'  alt='simple transistion' >
 *
 * <pre>
 * <code>
 *   A {
 *      {@literal @}Override
 *      public void e( Context ctx ) {
 *          ctx.changeFromToState( "e1", this, B1, B2 );
 *      }
 *   },
 *   B1 {
 *      {@literal @}Override
 *      public StateImplentation getInitialState() {
            return B22;
        }
 *   }
 * </code>
 * </pre>
 *
 * <h4>Shallow history</h4>
 * <p>By implementing the getInitailState and the isInitialStateHistory 
 * function we can define what the initial state should be. And that the 
 * framework will make sure that history is applied when needed.</p>
 * <img src='doc-files/_7.svg'  alt='simple transistion' >
 *
 * <pre>
 * <code>
 *   A {
 *      {@literal @}Override
 *      public void e( Context ctx ) {
 *          ctx.changeFromToState( "e1", this, B1, B2 );
 *      }
 *   },
 *   B1 {
 *      {@literal @}Override
 *      public boolean isInitialStateHistory() {
            return true;
        }
        
        {@literal @}Override
 *      public StateImplentation getInitialState() {
            return B22;
        }
 *   }
 * </code>
 * </pre>
 *
 * <h4>Deep history</h4>
 * <p>By implementing the getInitailState and the isInitialStateDeepHistory 
 * function we can define what the initial state should be. And that the 
 * framework will make sure that history is applied when needed.</p>
 * <img src='doc-files/_8.svg'  alt='simple transistion' >
 *
 * <pre>
 * <code>
 *   A {
 *      {@literal @}Override
 *      public void e( Context ctx ) {
 *          ctx.changeFromToState( "e1", this, B1, B2 );
 *      }
 *   },
 *   B1 {
 *      {@literal @}Override
 *      public boolean isInitialStateDeepHistory() {
            return true;
        }
        
        {@literal @}Override
 *      public StateImplentation getInitialState() {
            return B22;
        }
 *   }
 * </code>
 * </pre>
 *
 * @author Pieter van den Hombergh
 * @author Jeroen Beulen
 * @author Sander Lemans
 *
 */
package statewalker;
