#!/bin/bash

FILE_NAME="target/benchmarks.jar"
if test -f "$FILE_NAME"
then
	java -jar "$FILE_NAME"
else
	echo "please { mvn clean install } project first !" 
fi
