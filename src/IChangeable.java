/* Interface for objects that support deferrable change requests.


package ptolemy.kernel.util;

///////////////////////////////////////////////////////////////////
//// Changeable

/**
 This is an interface for objects that support change requests that can
 be deferred.  A change request is any
 modification to a model that might be performed during execution of the
 model, but where there might only be certain phases of execution during
 which it is safe to make the modification.  Such changes are also called
 <i>mutations</i>.
 <p>
 A change request is typically made by instantiating a subclass of
 ChangeRequest (possibly using an anonymous inner class) and then passing
 it to the requestChange() method of an object implementing this interface.
 That object may delegate the request (for example, it might consolidate
 all such requests at the top level of the hierarchy by passing the
 request to its container).  If it does delegate, then it is expected
 to consistently delegate all commands to the same object.
 <p>
 When a change request is made, if it is safe to do so, then an
 implementor of this interface is free to immediately execute
 the request, unless setDeferringChangeRequests(true) has been called.
 It is never safe to execute a change request if the implementor
 is already in the middle of executing a change request (that
 execution may have triggered the request).

 @author Edward A. Lee
 @version $Id$
 @since Ptolemy II 4.0
 @Pt.ProposedRating Green (eal)
 @Pt.AcceptedRating Green (neuendor)
 @see ChangeRequest
 */
public interface IChangeable {
    ///////////////////////////////////////////////////////////////////
    ////                         public methods                    ////

    /** Add a change listener. Each listener
     *  will be notified of the execution (or failure) of each
     *  change request that is executed via the requestChange() method.
     *  Implementors are free to delegate both the request and the
     *  listener to other objects (e.g. the container), so the listener
     *  may be notified of more changes than those requested through
     *  the requestChange() method of this object. Implementors are
     *  required to not add a listener that is already on the list
     *  of listeners (i.e., objects should not appear more than once
     *  on the list).
     *  @param listener The listener to add.
     *  @see #removeChangeListener(IChangeListener)
     *  @see #requestChange(ChangeRequest)
     */
    public void addChangeListener(IChangeListener listener);

    /** Execute requested changes. An implementor is free to delegate
     *  this to another implementor of Changeable. An implementor
     *  is expected to execute all pending change requests (even
     *  if setDeferringChangeRequests() has been
     *  called with a true argument).  Listeners will be notified
     *  of success or failure.
     *  @see #addChangeListener(IChangeListener)
     *  @see #requestChange(ChangeRequest)
     *  @see #setDeferringChangeRequests(boolean)
     */
    public void executeChangeRequests();

    /** Return true if setDeferringChangeRequests(true) has been called
     *  to specify that change requests should be deferred.
     *  @return True if change requests are being deferred.
     *  @see #setDeferringChangeRequests(boolean)
     */
    public boolean isDeferringChangeRequests();

    /** Remove a change listener, if it is present, and otherwise
     *  do nothing.
     *  @param listener The listener to remove.
     *  @see #addChangeListener(IChangeListener)
     */
    public void removeChangeListener(IChangeListener listener);

    /** Request that the given change be executed. An implementor is free
     *  to delegate this request to another object (e.g. the container).
     *  It may also execute the request immediately,
     *  unless this object is deferring change requests. If
     *  setDeferChangeRequests() has been called with a true argument,
     *  then an implementor is expected to queue the request until
     *  either setDeferChangeRequests() is called with a false
     *  argument or executeChangeRequests() is called.
     *  If an implementor is already in the middle of executing a change
     *  request, then the implementor is expected to finish that
     *  execution before executing this one.
     *  Change listeners will be notified of success (or failure) of the
     *  request when it is executed.
     *  @param change The requested change.
     *  @see #executeChangeRequests()
     *  @see #setDeferringChangeRequests(boolean)
     */
    public void requestChange(ChangeRequest change);

    /** Specify whether change requests made by calls to requestChange()
     *  should be executed immediately. An implementor is free to delegate
     *  this to another object implementing Changeable (e.g. the container).
     *  If the argument is true, then an implementor is expected to
     *  queue requests until either this method is called again
     *  with argument false, or until executeChangeRequests() is called.
     *  If the argument is false, then execute any pending change requests
     *  and set a flag requesting that future requests be executed
     *  immediately.
     *  @param isDeferring If true, defer change requests.
     *  @see #addChangeListener(IChangeListener)
     *  @see #executeChangeRequests()
     *  @see #isDeferringChangeRequests()
     *  @see #requestChange(ChangeRequest)
     */
    public void setDeferringChangeRequests(boolean isDeferring);
}
