FROM openjdk:17
VOLUME /tmp
EXPOSE 8081
ADD target/security_docker-0.0.1-SNAPSHOT.jar crud.jar
ENTRYPOINT ["java","-jar","crud.jar"]