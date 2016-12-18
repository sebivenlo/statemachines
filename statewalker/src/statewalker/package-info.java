/**
 * A enum bases state machine implementation of the <strong>GoF State</strong> pattern.
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
 * </pre>
 *
 * <p>When declaring your classes in this way you will by definition have some
 * cyclic dependencies, which cannot be avoided really easily, so it might take
 * some effort to convince the compiler it is okay. It may help to temporarily
 * turn off the IDE feature to compile on save.</p>
 *
 * <p>The standard use is also shown in the class diagram below, where the
 * app-package is the users application package. The {@code <C,D,S,>} generic
 * tokens should be understood as alphabetic mnemonics for Context, Device and
 * State.</p>
 *
 * <img src='doc-files/statewalker.svg' alt='statewalker usage class diagram'>
 * <br>
 *
 * <p><b>Implementation detail.</b> The classes have been designed to reduce complexity as
 * much as possible, in particular using the generic annotation needed.
 *
 * This applies more to the use of the package then to the package it self,
 * which cannot avoid some unchecked warnings in its implementation. In the
 * implementation some use of {@code @SuppresWarnings("unchecked")} cannot be
 * avoided, because we use arrays as internal storage and the varargs parameters
 * list is an array too. We consider using varargs to be applicable here.</p>
 */
package statewalker;
