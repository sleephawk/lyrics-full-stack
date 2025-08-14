FROM eclipse-temurin:21-jdk-alpine

RUN apk add --no-cache bash maven git

WORKDIR /app

COPY pom.xml .
COPY src ./src
# If you use Maven wrapper:
# COPY mvnw .
# COPY .mvn .mvn

RUN mvn clean package -DskipTests

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -jar target/*.jar"]
