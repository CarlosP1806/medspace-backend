# Use OpenJDK 21 as the base image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy dependencies first to leverage caching
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Fix line endings and make the mvnw script executable
RUN apt-get update && apt-get install -y dos2unix && \
    dos2unix mvnw && \
    chmod +x mvnw && \
    apt-get --purge remove -y dos2unix && \
    rm -rf /var/lib/apt/lists/*

# Download dependencies - this layer will be cached unless pom.xml changes
RUN ./mvnw dependency:go-offline -B || true

# Now copy application source
COPY src ./src

EXPOSE 8080

# Set the default command to build and run the app
CMD ["./mvnw", "quarkus:dev"]