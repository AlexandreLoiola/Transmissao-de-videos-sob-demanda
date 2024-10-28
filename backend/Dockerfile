# Etapa 1: Compilar o projeto
FROM maven:3.9.4-eclipse-temurin-21 as builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Executar o projeto
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/OnDemandVideoStreaming-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]