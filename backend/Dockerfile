#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src/main /home/app/src/main
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY sample.db sample.db
COPY --from=build /home/app/target/jumia-exercise-0.0.1-SNAPSHOT.jar jumia-exercise.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/jumia-exercise.jar"]