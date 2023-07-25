FROM openjdk:11-jre-slim
EXPOSE 8080
ADD target/best5.jar best5.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar", "/best5.jar"]