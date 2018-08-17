/* Exception thrown on an attempt to perform an action that would result in an
 inconsistent or contradictory data structure if it were allowed to
 complete.

 */
//package ptolemy.kernel.util;

//////////////////////////////////////////////////////////////////////////
//// IllegalActionException

/**
 Thrown on an attempt to perform an action that would result in an
 inconsistent or contradictory data structure if it were allowed to
 complete.
 <p>Examples include:
 <menu>
 <li> An attempt to remove a port from an entity when the
 port does not belong to the entity.
 <li> An attempt to add an item with no name to a named list.
 </menu>

 @author Edward A. Lee, Christopher Hylands
 @version $Id$
 @since Ptolemy II 0.2
 @Pt.ProposedRating Green (cxh)
 @Pt.AcceptedRating Green (cxh)
 */
@SuppressWarnings("serial")
public class IllegalActionException extends KernelException {//  ptolemy.kernel.util.KernelException { //ptolemy.kernel.util.KernelException {
    /** Construct an exception with a detail message.
     *  @param detail The message.
     */
    public IllegalActionException(String detail) {
        this(null, null, null, detail);
    }

    /** Construct an exception with a detail message that is only the
     *  name of the argument.
     *  @param object The object.
     */
    public IllegalActionException(INameable object) {
        this(null, object, null, null);
    }

    /** Construct an exception with a detail message that includes the
     *  name of the first argument.
     *  @param object The object.
     *  @param detail The message.
     */
    public IllegalActionException(INameable object, String detail) {
        this(object, null, null, detail);
    }

    /** Construct an exception with a detail message that includes the
     *  name of the first argument.
     *  @param object The object.
     *  @param cause The cause of this exception, or null if the cause
     *  is not known or nonexistent.
     *  @param detail The message.
     */
    public IllegalActionException(INameable object, Throwable cause,
                                  String detail) {
        this(object, null, cause, detail);
    }

    /** Construct an exception with a detail message that consists of
     *  only the names of the object1 and object2 arguments.  If one
     *  or more of the parameters are null, then the message of the
     *  exception is adjusted accordingly.
     *  @param object1 The first object.
     *  @param object2 The second object.
     */
    public IllegalActionException(INameable object1, INameable object2) {
        this(object1, object2, null, null);
    }

    /** Construct an exception with a detail message that includes the
     *  names of the first two arguments plus the third argument
     *  string.  If one or more of the parameters are null, then the
     *  message of the exception is adjusted accordingly.
     *  @param object1 The first object.
     *  @param object2 The second object.
     *  @param detail The message.
     */
    public IllegalActionException(INameable object1, INameable object2,
                                  String detail) {
        this(object1, object2, null, detail);
    }

    /** Construct an exception with a detail message that includes the
     *  names of the first two arguments plus the third argument
     *  string.  If the cause argument is non-null, then the message
     *  of this exception will include the message of the cause
     *  argument.  The stack trace of the cause argument is used when
     *  we print the stack trace of this exception.  If one or more of
     *  the parameters are null, then the message of the exception is
     *  adjusted accordingly.
     *
     *  @param object1 The first object.
     *  @param object2 The second object.
     *  @param cause The cause of this exception, or null if the cause
     *  is not known or nonexistent.
     *  @param detail The message.
     */
    public IllegalActionException(INameable object1, INameable object2,
                                  Throwable cause, String detail) {
        super(object1, object2, cause, detail);
    }
}
