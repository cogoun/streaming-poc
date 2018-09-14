FROM frolvlad/alpine-oraclejdk8
COPY collaboration-transformer-app/target/collaboration-transformer-app-0.0.1-SNAPSHOT.jar /opt/applications/collaboration-transformer-app/
WORKDIR /opt/applications/collaboration-transformer-app/
CMD ["java", "-XX:+PrintFlagsFinal", "-Xmx100m","-jar", "collaboration-transformer-app-0.0.1-SNAPSHOT.jar"]
