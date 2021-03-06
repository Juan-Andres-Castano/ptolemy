# Makefile for the Vergil package
#
# @Authors: Christopher Hylands, based on a file by Thomas M. Parks
#
# @Version: $Id$
#
# @Copyright (c) 1998-2014 The Regents of the University of California.
# All rights reserved.
#
# Permission is hereby granted, without written agreement and without
# license or royalty fees, to use, copy, modify, and distribute this
# software and its documentation for any purpose, provided that the
# above copyright notice and the following two paragraphs appear in all
# copies of this software.
#
# IN NO EVENT SHALL THE UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY
# FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES
# ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
# THE UNIVERSITY OF CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF
# SUCH DAMAGE.
#
# THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY WARRANTIES,
# INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
# MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
# PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND THE UNIVERSITY OF
# CALIFORNIA HAS NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES,
# ENHANCEMENTS, OR MODIFICATIONS.
#
# 						PT_COPYRIGHT_VERSION_2
# 						COPYRIGHTENDKEY

ME =		ptolemy/vergil

# PTPDFRENDERER_DIR is set to pdfrenderer by configure
DIRS = 	toolbox basic actor kernel \
	gt \
	debugger \
	test \
	icon modal $(PTPDFRENDERER_DIR) \
	ontologies \
	ptera \
	scr \
	tree \
	tdl \
	unit

# Root of the Java directory
ROOT =		../..

CLASSPATH =	$(ROOT)$(CLASSPATHSEPARATOR)$(DIVA_JAR)

# Get configuration info
CONFIG =	$(ROOT)/mk/ptII.mk
include $(CONFIG)

# Used to build jar files
PTPACKAGE = 	vergil
PTCLASSJAR =

# Include these jars in PTAUXALLJAR
PTAUXALLJARS = \
		actor/actor.jar \
		basic/basic.jar \
		basic/export/export.jar \
		icon/icon.jar \
		kernel/kernel.jar \
		debugger/debugger.jar \
		modal/modal.jar \
		toolbox/toolbox.jar \
		tree/tree.jar \
		unit/unit.jar

# Packages that are not part of the ptiny version of Vergil
#		ptera/ptera.jar
#		scr/scr.jar
#		gt/gt.jar
#		tdl/tdl.jar 
#		properties/properties.jar

# All the .class files necessary to run a Vergil applet
PTAUXALLJAR =	vergilApplet.jar

# Include the .class files from these jars in PTCLASSALLJAR
PTCLASSALLJARS = \
		$(PTAUXALLJARS)

PTCLASSALLJAR = $(PTPACKAGE).jar

JSRCS = \
	MoMLViewerApplet.java  \
	TypeAnimatorApplet.java \
	VergilApplet.java \
	VergilApplication.java \
	VergilErrorHandler.java \
	VergilGraphicalMessageHandler.java \
	VergilInitializer.java

OTHER_FILES_TO_BE_JARED =

EXTRA_SRCS =	$(JSRCS) \
	$(OTHER_FILES_TO_BE_JARED) \
	TODO.txt \
	Vergil.htm \
	Vergil.properties \
	vergilAppletDemo.htm \
	vergilAppletDemo.xml

# Sources that may or may not be present, but if they are present, we don't
# want make checkjunk to barf on them.
MISC_FILES =	$(DIRS)


# make checkjunk will not report OPTIONAL_FILES as trash
# make realclean removes OPTIONAL_FILES
OPTIONAL_FILES = \
	doc \
	lib \
	test \
	$(PTCLASSJAR) \
	'MoMLViewerApplet$$GetDocumentationAction.class' \
	'MoMLViewerApplet$$ObjectContextMenuFactory.class' \
	'MoMLViewerApplet$$PortContextMenuFactory$$PortDescriptionFactory.class' \
	'MoMLViewerApplet$$PortContextMenuFactory.class' \
	'MoMLViewerApplet$$StateContextMenuFactory.class' \
	'MoMLViewerApplet$$TransitionContextMenuFactory.class' \
	'TypeAnimatorApplet$$1.class' \
	'TypeAnimatorApplet$$PortTypeListener.class' \
	'VergilApplication$$1.class' \
	'VergilApplication$$2.class' \
	'VergilApplication$$3.class' \
	'VergilApplication$$4.class' \
	'VergilApplication$$ClassReloadingService.class' \
	'VergilApplication$$MouseFocusMover.class' \
	'VergilApplication$$TitleChanger.class'

JCLASS = $(JSRCS:%.java=%.class)

all: jclass
install: jclass jars

# Get the rest of the rules
include $(ROOT)/mk/ptcommon.mk
