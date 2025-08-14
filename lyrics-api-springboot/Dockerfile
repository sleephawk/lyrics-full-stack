# Use Java 21 JDK
FROM eclipse-temurin:21-jdk-alpine

# Set working directory inside container
WORKDIR /app

# Copy Maven pom.xml first (for dependency caching)
COPY pom.xml .

# Copy source code
COPY src ./src

# Build Spring Boot app
RUN mvn clean package -DskipTests

# Expose default port (Railway maps dynamically)
EXPOSE 8080

# Run Spring Boot
ENTRYPOINT ["java", "-jar", "target/lyrics-api-springboot-0.0.1-SNAPSHOT.jar"]
