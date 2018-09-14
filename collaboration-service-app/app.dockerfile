FROM frolvlad/alpine-oraclejdk8
COPY collaboration-service-app/target/collaboration-service-app-0.0.1-SNAPSHOT.jar /opt/applications/collaboration-service-app/
WORKDIR /opt/applications/collaboration-service-app/
CMD ["java", "-XX:+PrintFlagsFinal", "-Xmx100m", "-jar", "collaboration-service-app-0.0.1-SNAPSHOT.jar"]
