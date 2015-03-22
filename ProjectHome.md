# Parsley #

## About ##

Parsley is a toy project to develop an interpreter for a simple
command language, written in Java.

It's based around a simple text-based interpreter of assembly-style
commands, uses a simple stack machine, supports procedures, strings
and integer datatypes, has a basic form of interrupt handling, symbol
tables, and has a set of pluggable commands which can be extended.

It was written over a weekend as a toy project with no real application.

The source code is licensed under the MIT license, which is included
in the LICENSE file, and is also available at:

http://www.opensource.org/licenses/mit-license.php

## Compiling ##

An Ant build script is supplied with some targets for building and
packaging the compiled sources into a JAR file.

## Running an Example ##

There is an Ant target called "test" that runs a test class with
an example program. The test class shows how to embed the intepreter
inside an existing Java program, and how to trigger an interrupt
handler.

The example program sets a global flag, does some calculations,
calls some procedures, does some counting in a loop and then finishes
after an interrupt handler in the script is triggered from within the
Java code.