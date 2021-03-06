# Makefile for the PtinyOS domain ncapp2moml utility
#
# @Authors: Christopher Brooks (makefile only)
#
# @Version: $Id$
#
# @Copyright (c) 2005-2009 The Regents of the University of California.
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

ME =		ptolemy/domains/ptinyos/util/ncapp2moml

DIRS =		test

# Root of the Java directory
ROOT =		../../../../..

PTINYOS_DIR =   $(ROOT)/ptolemy/domains/ptinyos

CLASSPATH =	$(ROOT)$(CLASSPATHSEPARATOR)$(PTINYOS_DIR)/lib/jdom.jar$(CLASSPATHSEPARATOR)$(PTINYOS_DIR)/lib/nesc.jar

# Get configuration info
CONFIG =	$(ROOT)/mk/ptII.mk
include $(CONFIG)

# Used to build jar files
PTPACKAGE = 	ncapp2moml
PTCLASSJAR = 	$(PTPACKAGE).jar

JSRCS = \
	NCApp2MoML.java

OTHER_FILES_TO_BE_MADE_EXECUTABLE = \
	ncapp2moml \
	ncapp2moml-get-args \
	ncapp2moml-main \
	ncapp2moml-run-ncc

OTHER_FILES_TO_BE_JARED = \
	$(OTHER_FILES_TO_BE_MADE_EXECUTABLE) \
	index.htm

EXTRA_SRCS =	$(JSRCS) $(OTHER_FILES_TO_BE_JARED) \
	ncapp2moml-tinyos-1.1.15Dec2005cvs-1.tar.gz

# Sources that may or may not be present, but if they are present, we don't
# want make checkjunk to barf on them.
MISC_FILES =	$(DIRS)

# make checkjunk will not report OPTIONAL_FILES as trash
# make realclean removes OPTIONAL_FILES
OPTIONAL_FILES = \
	'NCApp2MoML$$1.class' \
	'NCApp2MoML$$_Link.class' \
	'NCApp2MoML$$_ComponentFile.class' \
	'NCApp2MoML$$_Relations.class' \
	doc

JCLASS = $(JSRCS:%.java=%.class)

all: jclass
	chmod a+x $(OTHER_FILES_TO_BE_MADE_EXECUTABLE)

install: jclass jars

include $(ROOT)/mk/ptcommon.mk

