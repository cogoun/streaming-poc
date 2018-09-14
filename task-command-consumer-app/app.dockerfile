FROM frolvlad/alpine-oraclejdk8
COPY task-command-consumer-app/target/task-command-consumer-app-0.0.1-SNAPSHOT.jar /opt/applications/task-command-consumer-app/
WORKDIR /opt/applications/task-command-consumer-app/
CMD ["java","-jar", "task-command-consumer-app-0.0.1-SNAPSHOT.jar"]
