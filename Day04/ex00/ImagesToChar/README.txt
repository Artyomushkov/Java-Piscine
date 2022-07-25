#!/bin/bash
# Instructions for compiling and starting sourcecode from the console (non-IDE).
# Instruction is written for the state where the console is opened in the project’s root folder.

# Making directory
rm -rf target
mkdir target

# Compiling sourcecode to target
javac  ./src/java/edu/school21/printer/*/*.java  -d ./target

# Run program with parameter ARG - path to img-file
ARG="/Users/jhizdahr/java/Day04/it.bmp"
java -classpath ./target edu.school21.printer.app.Program $ARG