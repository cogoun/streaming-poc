FROM frolvlad/alpine-oraclejdk8
COPY task-indexer-app/target/task-indexer-app-0.0.1-SNAPSHOT.jar /opt/applications/task-indexer-app/
WORKDIR /opt/applications/task-indexer-app/
CMD ["java","-jar", "-XX:+PrintFlagsFinal","-Xmx100m", "task-indexer-app-0.0.1-SNAPSHOT.jar"]
