FROM maven:3-eclipse-temurin-17-alpine AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app
RUN mvn clean install

FROM amazoncorretto:17-alpine-jdk
COPY --from=build /app/target/Employee-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]