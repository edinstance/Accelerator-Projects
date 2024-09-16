

# Build the docker image
FROM maven:3.9.7-eclipse-temurin-22-alpine AS BUILD

ARG DATABASE_URL
ARG DATABASE_NAME
ARG DATABASE_USER
ARG DATABASE_PASS

ENV DATABASE_URL=${DATABASE_URL}
ENV DATABASE_NAME=${DATABASE_NAME}
ENV DATABASE_USER=${DATABASE_USER}
ENV DATABASE_PASS=${DATABASE_PASS}

# Set the working directory
WORKDIR /app

# Copy the project files
COPY pom.xml .
COPY config/ config/
COPY src ./src

# Run the maven lifecycles to build the application
RUN mvn clean package

# Stage 2: Create the run time image
FROM eclipse-temurin:22-jdk-alpine AS run

# Set the working directory
WORKDIR /app

# Copy the JAR file built in the previous stage
COPY --from=BUILD /app/target/*.jar ./app.jar

# Expose the applications port
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
