# Stage 1: Build the application
FROM maven:3.9.4-eclipse-temurin-17 as builder

WORKDIR /app

# Copy all source code
COPY . .

# Package the application and skip tests
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/splitify-0.0.1-SNAPSHOT.jar app.jar

# Expose your application's port (9090)
EXPOSE 9090

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
