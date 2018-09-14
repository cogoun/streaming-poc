FROM frolvlad/alpine-oraclejdk8
COPY notification-ui-app/target/notification-ui-app-0.0.1-SNAPSHOT.jar /opt/applications/notification-ui-app/
WORKDIR /opt/applications/notification-ui-app/
CMD ["java","-jar", "notification-ui-app-0.0.1-SNAPSHOT.jar"]
