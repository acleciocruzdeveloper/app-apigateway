FROM maven:3.9-amazoncorretto-17 as build
WORKDIR /app

COPY . .

RUN ["sh", "-c", "mvn clean package -DskipTests || true", "-X", "-e"]

FROM amazoncorretto:17-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# CONFIG EUREKA
ARG EUREKA_SERVER=localhost
ARG USER_EUREKA=user
ARG PASSWORD_EUREKA=1234567890

# CONFIG KEYCLOAK
ARG KEYCLOAK_SERVER=localhost
ARG KEYCLOAK_PORT=8081


ENTRYPOINT ["java", "-jar", "app.jar"]