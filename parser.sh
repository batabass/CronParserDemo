#!/bin/bash

echo "Running mvn clean install..."
mvn clean install

echo "Running mvn compile..."
mvn compile

echo "Running com.cronparserdemo.CronParser with arguments: $@"
mvn exec:java -Dexec.mainClass="com.cronparserdemo.CronParser" -Dexec.args="\"$*\""