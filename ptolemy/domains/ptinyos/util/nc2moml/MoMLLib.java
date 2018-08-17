/*
 @Copyright (c) 2005-2015 The Regents of the University of California.
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
package ptolemy.domains.ptinyos.util.nc2moml;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.jdom.Comment;
import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.ProcessingInstruction;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import ptolemy.util.FileUtilities;

/**
 Traverse a directory tree and generate .moml files.
 Search the directory tree for files that
 <i>input suffix</i> (e.g., *.moml) and create files with the name
 <i>output filename</i> (e.g., index.moml) in each directory
 that contains an file with the <i>input suffix</i> and in all the parent
 directories.
 Existing <i>input suffix</i> files are overwritten.

 <p>Usage:
 <pre>
 java -classpath $PTII:$PTII/ptolemy/domains/ptinyos/lib/jdom.jar \
 ptolemy.domains.ptinyos.util.nc2moml.MoMLLib \
 <i>input suffix</i> <i>top-level output filename</i> \
 <i>output filename</i> <i>root dir of input files</i>
 </pre>

 <p>Example:
 <pre>
 java -classpath $PTII:$PTII/ptolemy/domains/ptinyos/lib/jdom.jar \
 ptolemy.domains.ptinyos.util.nc2moml.MoMLLib \
 .moml _TOSIndex.moml _index.moml $PTII/vendors/ptinyos/moml
 </pre>



 @author Elaine Cheong, contributor: Christopher Brooks
 @version $Id$
 @since Ptolemy II 5.1
 @Pt.ProposedRating Red (celaine)
 @Pt.AcceptedRating Red (celaine)
 */
public class MoMLLib {
    /** Generate the .moml index file for the given arguments.
     *  Example:
     <pre>
     &lt;?xml version="1.0"?&gt;
     &lt;!DOCTYPE plot PUBLIC "-//UC Berkeley//DTD MoML 1//EN" "http://ptolemy.eecs.berkeley.edu/xml/dtd/MoML_1.dtd"&gt;

     &lt;!--DO NOT EDIT.  This file was generated by ptolemy.domains.ptinyos.util.nc2moml.MoMLLib.  The filename was chosen so that it does not conflict with .nc files of the same name and any other ptII-referenced .moml file in the classpath.--&gt;
     &lt;entity name="Counters" class="ptolemy.moml.EntityLibrary"&gt;
     &lt;configure&gt;
     &lt;?moml
     &lt;group&gt;
     &lt;entity name="Counter" class="tos.lib.Counters.Counter" /&gt;
     &lt;entity name="IntToLeds" class="tos.lib.Counters.IntToLeds" /&gt;
     &lt;entity name="IntToLedsM" class="tos.lib.Counters.IntToLedsM" /&gt;
     &lt;entity name="IntToRfm" class="tos.lib.Counters.IntToRfm" /&gt;
     &lt;entity name="IntToRfmM" class="tos.lib.Counters.IntToRfmM" /&gt;
     &lt;entity name="RfmToInt" class="tos.lib.Counters.RfmToInt" /&gt;
     &lt;entity name="RfmToIntM" class="tos.lib.Counters.RfmToIntM" /&gt;
     &lt;entity name="SenseToInt" class="tos.lib.Counters.SenseToInt" /&gt;
     &lt;/group&gt;
     ?&gt;
     &lt;/configure&gt;
     &lt;/entity&gt;
     </pre>
     *
     *  @param components Array containing the components in short
     *  path format relative to the root.
     *    Example: tos/lib/Counters/Counter
     *  @param libraryName Name of this directory.
     *  @param indexFiles Array containing the sub-index files in
     *  short path format relative to the outputFile directory.
     *    Example: subdir/_TOSIndex.moml
     *  @param outputFile The file to generate in long path format.
     *    Example: /home/celaine/ptII/vendors/ptinyos/moml/tos/lib/Counters/Counter/index.moml
     *  @exception IOException If there is a problem writing files.
     */
    public static void generateIndex(String[] components, String[] indexFiles,
            String libraryName, String outputFile) throws IOException {
        Element root = new Element("entity");
        root.setAttribute("name", libraryName);
        root.setAttribute("class", "ptolemy.moml.EntityLibrary");

        Element configure = new Element("configure");
        root.addContent(configure);

        DocType plot = new DocType("plot", "-//UC Berkeley//DTD MoML 1//EN",
                "http://ptolemy.eecs.berkeley.edu/xml/dtd/MoML_1.dtd");
        Document doc = new Document(); //root, plot);

        String comment = "DO NOT EDIT.  This file was generated by "
                + "ptolemy.domains.ptinyos.util.nc2moml.MoMLLib.  "
                + "The filename was chosen so that it does not "
                + "conflict with .nc files of the same name and any "
                + "other ptII-referenced .moml file in the classpath.";
        doc.addContent(new Comment(comment));
        doc.setRootElement(root);
        doc.setDocType(plot);

        Element group = new Element("group");

        // Make entries for index files.
        // Example:
        //     <input source="Counters/index.moml"/>
        for (int i = 0; i < indexFiles.length; i++) {
            Element input = new Element("input");

            try {
                input.setAttribute("source", FileUtilities.nameToURL(
                        indexFiles[i], null, null).toString());
            } catch (MalformedURLException e) {
                input.setAttribute("source", indexFiles[i]);
            }

            group.addContent(input);
        }

        // Make entries for component files.
        for (int i = 0; i < components.length; i++) {
            String c = components[i];

            if (File.separator.equals("\\")) {
                c = c.replace('\\', '/');
            }

            String[] subNames = c.split("/");
            String componentName = subNames[subNames.length - 1];
            String className = c.replace('/', '.');

            Element entity = new Element("entity");
            entity.setAttribute("name", componentName);
            entity.setAttribute("class", className);
            group.addContent(entity);
        }

        // Setup format for xml serializer.
        XMLOutputter serializer = new XMLOutputter(Format.getPrettyFormat());
        Format format = serializer.getFormat();
        format.setOmitEncoding(true);
        format.setLineSeparator("\n");
        serializer.setFormat(format);

        // Create "?moml" processing instruction.
        ProcessingInstruction moml = new ProcessingInstruction("moml", "\n"
                + serializer.outputString(group) + "\n");
        configure.addContent(moml);

        // Generate index file.
        FileOutputStream out = null;

        try {
            if (outputFile != null) {
                out = new FileOutputStream(outputFile);
            }

            if (out != null) {
                serializer.output(doc, out);
            } else {
                serializer.output(doc, System.out);
            }
        } catch (IOException ex) {
            IOException ioException = new IOException(
                    "Error writing index file \"" + outputFile + "\"");
            ioException.initCause(ex);
            throw ioException;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    System.err.println("Failed to close " + outputFile + ": "
                            + ex);
                }
            }
        }
    }

    /** Traverse the directory tree and generate .moml files.
     *  @param args An array of Strings.
     *   <ol>
     *   <li> The input suffix.
     *   <li> The top-level output file name.
     *   <li> The output file name
     *   <li> the root directory of the input files.
     *   </ol>
     */
    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.println("Usage: java MoMLLib <input suffix> "
                    + "<top-level output filename> <output filename> "
                    + "<root dir of input files>");
            return;
        }

        int i = 0;
        String inputSuffix = args[i++].trim();
        String toplevelOutputFilename = args[i++].trim();
        String outputFilename = args[i++].trim();
        String rootDir = args[i++].trim();

        try {
            MoMLLib.proc(inputSuffix, outputFilename, toplevelOutputFilename,
                    true, rootDir, rootDir);
        } catch (Throwable throwable) {
            System.err.println("Command failed: " + throwable);
            throwable.printStackTrace();
        }
    }

    /** Traverse the directory tree recursively and generate .moml index files.
     *
     *  @param inputSuffix Suffix for the input files to look for.
     *  @param indexFilename Name of the index file to look for and
     *  generate for non-top level.
     *  @param indexFilenameTopLevel Name of the top-level index file
     *  to generate.
     *  @param toplevel True if this is the top-level call to proc().
     *  @param root Root dir of the input files.
     *  @param currentDir The current directory in this call to proc().
     *  @exception Exception If internal error (duplicate file found
     *  or invalid currentDir name.
     */
    public static void proc(final String inputSuffix,
            final String indexFilename, final String indexFilenameTopLevel,
            boolean toplevel, String root, String currentDir) throws Exception {
        if (File.separator.equals("\\")) {
            currentDir = currentDir.replace('\\', '/');
        }

        File dir = new File(currentDir);

        // Filter for directories only.
        FileFilter filterForDirs = new FileFilter() {
            public boolean accept(File file) {
                return file.isDirectory();
            }
        };

        File[] children = dir.listFiles(filterForDirs);

        // Coverity Scan: Dereference null value returns.  children could be null.
        if (children == null) {
            return;
        }

        // Recursive call.
        for (int i = 0; i < children.length; i++) {
            // Output filename same as index filename for non-top level.
            proc(inputSuffix, indexFilename, indexFilenameTopLevel, false,
                    root, children[i].toString());
        }

        // Filter for files with name == indexFilename.
        FilenameFilter filterForIndexFilename = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.equals(indexFilename);
            }
        };

        ArrayList indexFiles = new ArrayList();

        // Look for indexFilename in children directories.
        for (int i = 0; i < children.length; i++) {
            File[] grandchildren = children[i]
                    .listFiles(filterForIndexFilename);

            if (grandchildren.length == 1) {
                String indexFile = grandchildren[0].toString();

                if (File.separator.equals("\\")) {
                    indexFile = indexFile.replace('\\', '/');
                }

                // Transform into relative path.
                String[] indexFileSubNames = indexFile.split("/");

                if (indexFileSubNames.length < 2) {
                    throw new Exception("Invalid index file path.");
                } else {
                    String newIndexFileName = indexFileSubNames[indexFileSubNames.length - 2]
                            + "/"
                            + indexFileSubNames[indexFileSubNames.length - 1];
                    indexFiles.add(newIndexFileName);
                }
            } else {
                if (grandchildren.length > 1) {
                    throw new Exception("Duplicate file " + indexFilename
                            + " in " + children[i]);
                }
            }
        }

        // Filter for files with name ending in inputSuffix and not
        // indexFilename or indexFilenameTopLevel.
        FilenameFilter filterForInputSuffix = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(inputSuffix)
                        && !name.equals(indexFilename)
                        && !name.equals(indexFilenameTopLevel);
            }
        };

        String[] ncFiles = dir.list(filterForInputSuffix);
        String[] components = {};

        if (ncFiles.length > 0) {
            components = new String[ncFiles.length];

            for (int i = 0; i < ncFiles.length; i++) {
                // Make short path format for component name, w/o suffix
                String shortpath = currentDir.replaceFirst("^" + root, "");
                shortpath = shortpath.replaceFirst("^" + "/", "");

                if (shortpath.equals("")) {
                    // Don't add beginning slash if this is the toplevel.
                    shortpath = ncFiles[i];
                } else {
                    shortpath = shortpath + "/" + ncFiles[i];
                }

                shortpath = shortpath.replaceFirst(inputSuffix + "$", "");
                components[i] = shortpath;
            }
        }

        // Create libraryName
        String[] currentDirSubnames = null;

        try {
            currentDirSubnames = currentDir.split("/");

            if (currentDirSubnames.length < 1) {
                throw new Exception("Problem with currentDir name: "
                        + currentDir);
            }
        } catch (java.util.regex.PatternSyntaxException ex) {
            throw new Exception("Failed to split \"" + currentDir + "\" on '"
                    + "/" + "'", ex);
        }

        String libraryName = currentDirSubnames[currentDirSubnames.length - 1];

        // Create full output file name.
        String fullOutputFilename;

        if (toplevel) {
            fullOutputFilename = currentDir + "/" + indexFilenameTopLevel;
        } else {
            fullOutputFilename = currentDir + "/" + indexFilename;
        }

        // Create index file.
        String[] stringArrayType = {};
        MoMLLib.generateIndex(components, (String[]) indexFiles
                .toArray(stringArrayType), libraryName, fullOutputFilename);
    }
}
