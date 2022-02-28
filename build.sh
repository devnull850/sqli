#!/bin/bash

BUILD=build/classes
LIB=lib/lombok-1.18.22.jar:lib/sqlite-jdbc-3.36.0.3.jar
SRC=src/main/java/sqli

javac -d $BUILD -cp $LIB `find $SRC -name *.java`
