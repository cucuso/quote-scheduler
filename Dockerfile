# Simple runtime image for pre-built JAR
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy pre-built JAR (build with GitHub Actions or locally)
COPY target/*.jar app.jar

# Create directory for H2 database
RUN mkdir -p /app/data

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
