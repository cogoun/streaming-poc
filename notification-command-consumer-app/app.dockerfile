FROM frolvlad/alpine-oraclejdk8
COPY notification-command-consumer-app/target/notification-command-consumer-app-0.0.1-SNAPSHOT.jar /opt/applications/notification-command-consumer-app/
WORKDIR /opt/applications/notification-command-consumer-app/
CMD ["java","-XX:+PrintFlagsFinal","-Xmx100m","-jar", "notification-command-consumer-app-0.0.1-SNAPSHOT.jar"]
