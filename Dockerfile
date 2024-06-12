FROM eclipse-temurin

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} passwc.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/passwc.jar"]
