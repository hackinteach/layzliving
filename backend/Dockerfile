FROM openjdk:8
ADD ./application.properties /app/application.properties
ADD ./target/backend-0.0.1-SNAPSHOT.jar /app/backend-0.0.1-SNAPSHOT.jar
#EXPOSE 8080
WORKDIR /app
CMD ["java", "-jar", "./backend-0.0.1-SNAPSHOT.jar" ]
