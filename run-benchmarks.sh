#!/bin/bash
function run {
	export JAVA_HOME=$1
	./gradlew clean jmh
}

run /Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home
run /Library/Java/JavaVirtualMachines/jdk1.8.0_74.jdk/Contents/Home
run /Library/Java/JavaVirtualMachines/jdk-9.jdk/Contents/Home