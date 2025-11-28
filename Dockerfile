# ---------- Stage 1: build the JAR ----------
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom first so Maven can cache dependencies
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline

# Now copy the actual source and build
COPY src ./src
RUN mvn -q -DskipTests package

# ---------- Stage 2: run the JAR ----------
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
