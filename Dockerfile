FROM openjdk:17-jdk

WORKDIR /app

COPY target/RSCHIR-1.0.jar ./app.jar

CMD ["java", "-jar", "app.jar"]