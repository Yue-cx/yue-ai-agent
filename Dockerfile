FROM openjdk:21-slim
WORKDIR /app
# 确保你本地已经运行了 mvn package，且 target 目录下有这个文件
COPY target/yue-ai-agent-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8123
# 推荐使用 -D 参数来激活环境，兼容性更好
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]