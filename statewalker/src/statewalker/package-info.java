/**
 * A enum bases state machine implementation of the GoF state pattern.
 *
 * The package is designed with ease of use and type safety in mind, 
 * and therefor can not completely avoid the complexity that goes with it. 
 * The complexity left for the user is declaring the application specific 
 * Context, Device, and State as
 * a trisome in the generic declaration. To even further reduce the complexity, 
 * the user is advised to declare State as an intermediate interface that adds 
 * the application specific methods.
 * 
 * In the example, app, the declarations of AppContext, AppDevice, and AppState could then be as follows:
 * <pre>
 * {@code 
 * class AppContext extends ContextBase<AppContext, AppDevice, State>
 * class AppDevice implements Device<AppContext, AppDevice, State>
 * interface State extends StateBase<AppContext, AppDevice, State>
 * enum AppState implements State
 * 
 * }
 *  </pre>
 * 
 *
 *
 *
 * Implementation detail.
 * This applies for the users of the package more then to the package it self,
 * which cannot avoid some unchecked warnings in its implementation.
 *
 */
package statewalker;
