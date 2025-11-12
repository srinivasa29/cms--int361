# Stage 1: Build the JAR
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /workspace
COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY src ./src
RUN mvn -B clean package -DskipTests

# Stage 2: Run the app
FROM eclipse-temurin:21-jdk-jammy

ARG JAR_FILE=target/*.jar
COPY --from=build /workspace/${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
