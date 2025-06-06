# Etapa de build
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Etapa final
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app
EXPOSE 8080

COPY --from=build /app/target/pet-adoption-api-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
