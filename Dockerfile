FROM openjdk:11

EXPOSE 8082

ADD target/demo-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
