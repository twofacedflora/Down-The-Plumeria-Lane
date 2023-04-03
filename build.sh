#!/bin/bash

rm -v bin/downtheplumerialane/*.class
javac -verbose -cp .:lib/json.jar -d bin src/downtheplumerialane/*.java
jar -cvfm DTPL.jar manifest.txt lib resources -C bin .