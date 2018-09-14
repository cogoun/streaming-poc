FROM frolvlad/alpine-oraclejdk8
COPY task-service-app/target/task-service-app-0.0.1-SNAPSHOT.jar /opt/applications/task-service-app/
WORKDIR /opt/applications/task-service-app/
CMD ["java","-jar", "task-service-app-0.0.1-SNAPSHOT.jar"]
