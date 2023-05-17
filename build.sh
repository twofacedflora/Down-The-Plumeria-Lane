#!/bin/bash

rm -rv output/.data
mkdir -v output/.data
cp -v resources/data/settings.json output/.data
rm -v bin/downtheplumerialane/*.class
javac -verbose -cp .:lib/json.jar:lib/gson-2.10.1.jar -d bin src/downtheplumerialane/*.java
jar -cvfm output/DTPL.jar MANIFEST.mf lib resources -C bin .