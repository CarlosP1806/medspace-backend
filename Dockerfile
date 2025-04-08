# Use OpenJDK 21 as the base image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy dependencies first to leverage caching
COPY mvnw mvnw
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies - this layer will be cached unless pom.xml changes
RUN ./mvnw dependency:go-offline -B || true

# Now copy application source
COPY src ./src

EXPOSE 8080

# Set the default command
CMD ["./mvnw", "quarkus:dev"]