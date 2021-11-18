#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:8-jdk-alpine
MAINTAINER baeldung.com
COPY target/jumia-exercise-0.0.1-SNAPSHOT.jar jumia-exercise-0.0.1-SNAPSHOT.jar
COPY sample.db sample.db
ENTRYPOINT ["java","-jar","/jumia-exercise-0.0.1-SNAPSHOT.jar"]