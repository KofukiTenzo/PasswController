FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/*.jar /app/passwc.jar

EXPOSE 8000

CMD ["java", "-jar", "/app/passwc.jar"]