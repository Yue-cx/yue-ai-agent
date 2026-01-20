FROM openjdk:21-slim


WORKDIR /app


COPY target/yue-ai-agent-0.0.1-SNAPSHOT.jar app.jar


EXPOSE 8123


CMD ["java", "-jar", "app.jar", "--spring.profiles.active=prod"]