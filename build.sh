#!/bin/bash

# create output directory
mkdir output
# create data directory
mkdir output/.data
# add settings file to data directory
cp resources/data/settings.json output/.data
# build class files to bin directory
javac -cp .:lib/json.jar:lib/gson-2.10.1.jar -d bin src/downtheplumerialane/*.java
# build JAR archive to output directory
jar -cfm output/DTPL.jar MANIFEST.mf lib resources -C bin .