#!/bin/sh
date >> server_start_time.log
mvn clean install
cd target
java -jar exchange-0.0.1-SNAPSHOT.jar &

exit 0