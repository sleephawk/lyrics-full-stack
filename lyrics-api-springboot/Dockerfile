# Use Java 21 JDK
FROM eclipse-temurin:21-jdk-alpine

# Install bash, Maven, git for building
RUN apk add --no-cache bash maven git

# Set working directory
WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src
# If using Maven wrapper, uncomment these:
# COPY mvnw .
# COPY .mvn .mvn

# Debug: list files before building
RUN echo "=== Project files before build ===" && ls -R

# Build Spring Boot app
RUN mvn clean package -DskipTests

# Debug: list files after build
RUN echo "=== Target directory contents ===" && ls -l target

# Set environment variables for debugging
ENV MYSQLHOST=${MYSQLHOST}
ENV MYSQLPORT=${MYSQLPORT}
ENV MYSQLPASSWORD=${MYSQLPASSWORD}
ENV PORT=${PORT}

# Expose default port
EXPOSE 8080

# Debug: show environment variables
RUN echo "=== Environment Variables ===" && env

# Run the JAR with wildcard (no hardcoding version)
ENTRYPOINT ["sh", "-c", "java -jar target/*.jar"]
