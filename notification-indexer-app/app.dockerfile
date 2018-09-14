FROM frolvlad/alpine-oraclejdk8
COPY notification-indexer-app/target/notification-indexer-app-0.0.1-SNAPSHOT.jar /opt/applications/notification-indexer-app/
WORKDIR /opt/applications/notification-indexer-app/
CMD ["java","-XX:+PrintFlagsFinal","-Xmx100m","-jar", "notification-indexer-app-0.0.1-SNAPSHOT.jar"]
