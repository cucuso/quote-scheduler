# ---------- Stage 1: Build Native Image with GraalVM ----------
FROM ghcr.io/graalvm/native-image-community:21-ol9 AS build
WORKDIR /app

# Install Maven
RUN microdnf install -y wget tar gzip && \
    wget -q https://archive.apache.org/dist/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz && \
    tar xzf apache-maven-3.9.6-bin.tar.gz && \
    mv apache-maven-3.9.6 /opt/maven && \
    rm apache-maven-3.9.6-bin.tar.gz && \
    microdnf clean all

ENV PATH="/opt/maven/bin:${PATH}"

# Copy pom first so Maven can cache dependencies
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline

# Copy source and build native image
COPY src ./src
RUN mvn -Pnative -DskipTests package

# ---------- Stage 2: Runtime (minimal base image) ----------
FROM oraclelinux:9-slim
WORKDIR /app

# Copy the native executable from build stage
COPY --from=build /app/target/scheduler /app/scheduler

# Create directory for H2 database
RUN mkdir -p /app/data

EXPOSE 8080

ENTRYPOINT ["/app/scheduler"]
