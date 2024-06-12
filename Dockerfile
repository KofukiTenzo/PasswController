FROM openjdk:17

WORKDIR /app

COPY target/passwc.jar /app/passwc.jar

EXPOSE 8081

CMD ["java", "-jar", "/app/passwc.jar"]
