FROM openjdk:17-oracle
MAINTAINER RECLOY
COPY target/accouting-0.0.1-SNAPSHOT.jar /accouting-0.0.1-SNAPSHOT.jar
RUN #apt-get update && apt-get install librrds-perl rrdtool -y
# update sources
#RUN apt-get update
## install some packages
#RUN apt-get install -y curl
#RUN mkdir -p ${MONGO_DATA_HOST_PATH}
#RUN mkdir -p ${MONGO_LOG_HOST_PATH}
#RUN mkdir -p ${MONGO_INITDB_SCRIPTS_HOST_PATH}
EXPOSE 8080
#ENTRYPOINT [ "java","-jar","/accouting-0.0.1-SNAPSHOT.jar" ]
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://api-database4:27017/vista","-Djava.security.egd=file:/dev/./urandom","-jar","/accouting-0.0.1-SNAPSHOT.jar"]