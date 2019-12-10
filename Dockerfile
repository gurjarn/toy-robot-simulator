FROM openjdk:8-alpine

COPY target/toy-robot-simulator.jar /toy-robot-simulator.jar

ENTRYPOINT ["java", "-jar","/toy-robot-simulator.jar"]