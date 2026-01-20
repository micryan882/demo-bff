FROM maven:3.9.9-eclipse-temurin-11 AS build
WORKDIR /build
# copia TUTTO (pom.xml + src + config)
COPY . .
RUN mvn -B -DskipTests package

FROM eclipse-temurin:11-jdk
WORKDIR /app
COPY --from=build /build/target/quarkus-app /app/quarkus-app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "quarkus-app/quarkus-run.jar"]
