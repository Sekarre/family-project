FROM maven:3.8.3-openjdk-17 AS builder
WORKDIR source
COPY . .
RUN mvn -q clean package -DskipTests
RUN cp target/family-app-0.0.1-SNAPSHOT.jar ./family-app.jar
RUN java -Djarmode=layertools -jar family-app.jar extract

FROM openjdk:17-jdk-slim
WORKDIR application
COPY --from=builder source/dependencies/ ./
COPY --from=builder source/spring-boot-loader/ ./
COPY --from=builder source/application/ ./
ENTRYPOINT [ "java", "org.springframework.boot.loader.JarLauncher"]