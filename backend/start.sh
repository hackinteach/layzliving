#!/bin/bash

mvn clean package -DskipTests=true -f ./backend/
docker-compose up -d --build