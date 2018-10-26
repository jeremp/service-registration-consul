#!/usr/bin/env bash
# This will run the order service on a given port (1st argument) and using a given firstname (2nd agument)
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=${1},--my.firstname=${2}

