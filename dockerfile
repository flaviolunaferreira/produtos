# Etapa de Build
FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package

# Etapa de Execução
FROM openjdk:21
EXPOSE 8080
COPY --from=build /target/*.jar app.jar  
ENTRYPOINT ["java", "-jar", "/app.jar"]
