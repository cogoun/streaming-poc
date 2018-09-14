FROM frolvlad/alpine-oraclejdk8
COPY collaboration-ui-app/target/collaboration-ui-app-0.0.1-SNAPSHOT.jar /opt/applications/collaboration-ui-app/
WORKDIR /opt/applications/collaboration-ui-app/
CMD ["java", "-XX:+PrintFlagsFinal", "-Xmx100m", "-jar", "collaboration-ui-app-0.0.1-SNAPSHOT.jar"]
