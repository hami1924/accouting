FROM openjdk:17
MAINTAINER PERSONAL-ACCOUNTING
COPY target/docker-java-jar-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]