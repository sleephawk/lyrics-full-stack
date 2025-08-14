# Use a Java 21 base image
FROM eclipse-temurin:21-jdk-alpine

# Install bash, Maven, and any necessary tools
RUN apk add --no-cache bash maven git

# Set working directory
WORKDIR /app

# Copy pom and source files
COPY pom.xml .
COPY src ./src

# Build the Spring Boot app
RUN mvn clean package -DskipTests

# Expose Spring Boot port
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java","-jar","target/lyrics-api-springboot-0.0.1-SNAPSHOT.jar"]
