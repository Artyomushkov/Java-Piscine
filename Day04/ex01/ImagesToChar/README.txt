#!/bin/bash
# Instructions for creating a distribution package of the application—a JAR archive

# Making directory
rm -rf target
mkdir target

# Copying resources to target
cp -rf ./src/resources ./target

# Compiling sourcecode to target
javac  ./src/java/edu/school21/printer/*/*.java  -d ./target

# Making JAR-archive
jar cfm ./target/images-to-chars-printer.jar ./src/manifest.txt -C ./target .
chmod 777 ./target/images-to-chars-printer.jar

# Compiling java-program
java -jar ./target/images-to-chars-printer.jar /Users/jhizdahr/java/Day04/it.bmp