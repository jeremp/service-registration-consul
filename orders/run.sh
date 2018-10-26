#!/usr/bin/env bash
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=${1},--my.firstname=${2}

