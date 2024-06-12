FROM eclipse-temurin:17-jre-alpine

WORKDIR /opt/app

COPY --from=builder /opt/app/target/*.jar /opt/app/*.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/opt/app/*.jar"]
