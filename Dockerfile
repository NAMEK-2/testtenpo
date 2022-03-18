FROM adoptopenjdk/openjdk8
COPY "./build/libs/daniel.leon-0.0.1-SNAPSHOT.jar" "app-java.jar"
EXPOSE 8070
ENTRYPOINT ["java", "-jar", "app-java.jar"]