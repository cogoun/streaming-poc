FROM frolvlad/alpine-oraclejdk8
COPY notification-service-app/target/notification-service-app-0.0.1-SNAPSHOT.jar /opt/applications/notification-service-app/
WORKDIR /opt/applications/notification-service-app/
CMD ["java","-XX:+PrintFlagsFinal","-Xmx100m","-jar", "notification-service-app-0.0.1-SNAPSHOT.jar"]
