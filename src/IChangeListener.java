


/* A ChangeListener is an interface implemented by objects that are
 interested in being kept informed about mutations.
 */

//package ptolemy.kernel.util;

///////////////////////////////////////////////////////////////////
//// ChangeListener

/**
 A ChangeListener is an interface implemented by objects that are
 interested in being kept informed about changes in a model as they
 are executed. These listeners are informed when each change is successfully
 executed, or when an attempt to execute it results in an exception.

 */
public interface IChangeListener {
    ///////////////////////////////////////////////////////////////////
    ////                         public methods                    ////

    /** React to a change request has been successfully executed.
     *  This method is called after a change request
     *  has been executed successfully.
     *  @param change The change that has been executed, or null if
     *   the change was not done via a ChangeRequest.
     */
    public void changeExecuted(ChangeRequest change);

    /** React to a change request has resulted in an exception.
     *  This method is called after a change request was executed,
     *  but during the execution an exception was thrown.
     *  @param change The change that was attempted or null if
     *   the change was not done via a ChangeRequest.
     *  @param exception The exception that resulted.
     */
    public void changeFailed(ChangeRequest change, Exception exception);
}

