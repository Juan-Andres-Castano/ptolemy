/* An adapter class for ptolemy.actor.lib.jjs.JavaScript

 @Copyright (c) 2015-2016 The Regents of the University of California.
 All rights reserved.
 Permission is hereby granted, without written agreement and without
 license or royalty fees, to use, copy, modify, and distribute this
 software and its documentation for any purpose, provided that the
 above copyright notice and the following two paragraphs appear in all
 copies of this software.

 IN NO EVENT SHALL THE UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY
 FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES
 ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
 THE UNIVERSITY OF CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF
 SUCH DAMAGE.

 THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY WARRANTIES,
 INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
 PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND THE UNIVERSITY OF
 CALIFORNIA HAS NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES,
 ENHANCEMENTS, OR MODIFICATIONS.

 PT_COPYRIGHT_VERSION_2
 COPYRIGHTENDKEY

 */
package ptolemy.cg.adapter.generic.program.procedural.adapters.ptolemy.actor.lib.jjs;

import ptolemy.cg.kernel.generic.program.NamedProgramCodeGeneratorAdapter;
import ptolemy.kernel.util.IllegalActionException;

/**
 *  An adapter class for ptolemy.actor.lib.JavaScript.
 *
 * @author Bert Rodiers
 * @version $Id$
 * @since Ptolemy II 11.0
 * @Pt.ProposedRating Red (rodiers)
 * @Pt.AcceptedRating Red (rodiers)
 */
public class JavaScript extends NamedProgramCodeGeneratorAdapter {

    /**
     * Construct a JavaScript adapter.
     * @param actor The associated actor.
     */
    public JavaScript(ptolemy.actor.lib.jjs.JavaScript actor) {
        super(actor);
    }

    /**
     * Generate fire code.
     * The method reads in <code>printInt</code>, <code>printArray</code>,
     * <code>printString</code>, or <code>printDouble</code> from JavaScript.j,
     * replaces macros with their values and returns the results.
     * @return The generated code.
     * @exception IllegalActionException If the code stream encounters an
     *  error in processing the specified code block(s).
     */
    @Override
    protected String _generateFireCode() throws IllegalActionException {
        StringBuffer code = new StringBuffer();
        code.append(super._generateFireCode());

        //ptolemy.actor.lib.jjs.JavaScript actor = (ptolemy.actor.lib.jjs.JavaScript) getComponent();

        return code.toString();
    }
}
