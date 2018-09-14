FROM frolvlad/alpine-oraclejdk8
COPY task-ui-app/target/task-ui-app-0.0.1-SNAPSHOT.jar /opt/applications/task-ui-app/
WORKDIR /opt/applications/task-ui-app/
CMD ["java","-XX:+PrintFlagsFinal","-Xmx100m","-jar", "task-ui-app-0.0.1-SNAPSHOT.jar"]
