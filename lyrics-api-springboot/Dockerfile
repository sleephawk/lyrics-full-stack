# Use Java 21 JDK
FROM eclipse-temurin:21-jdk-alpine

# Set working directory inside container
WORKDIR /app

# Copy Maven wrapper and pom.xml first (for caching)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Copy source code
COPY src ./src

# Make Maven wrapper executable (if using it)
RUN chmod +x mvnw

# Build Spring Boot app
RUN ./mvnw clean package -DskipTests || mvn clean package -DskipTests

# Expose default port (Railway maps dynamically)
EXPOSE 8080

# Run Spring Boot
ENTRYPOINT ["java", "-jar", "target/lyrics-api-springboot-0.0.1-SNAPSHOT.jar"]
