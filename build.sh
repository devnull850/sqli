#!/bin/bash

BUILD=build/classes
SRC=src/main/java/sqli

javac -d $BUILD `find $SRC -name *.java`
