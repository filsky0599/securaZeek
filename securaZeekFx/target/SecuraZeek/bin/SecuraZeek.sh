#!/bin/sh
JLINK_VM_OPTIONS=
DIR=`dirname $0`
$DIR/java $JLINK_VM_OPTIONS -m com.example.securazeek/com.example.securazeek.SecuraZeek "$@"
