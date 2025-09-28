# syntax=docker/dockerfile:1

# Stage 1: build the application
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

# Copy Maven descriptor and resolve dependencies
COPY pom.xml ./
RUN mvn -B dependency:go-offline

# Copy source code and build the JAR
COPY src ./src
RUN mvn -B clean package -DskipTests

# Stage 2: create lightweight runtime image
FROM eclipse-temurin:17-jre
WORKDIR /app

# Expose the default Spring Boot port
EXPOSE 8080

# Copy the built jar from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
