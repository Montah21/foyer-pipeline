FROM openjdk:17
EXPOSE 8089
ADD target/tp-foyer-1.0.0-SNAPSHOT.jar tp-foyer-1.0.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "tp-foyer-1.0.0-SNAPSHOT.jar"]