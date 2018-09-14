FROM frolvlad/alpine-oraclejdk8
COPY collaboration-ui-app/target/collaboration-ui-app-0.0.1-SNAPSHOT.jar /opt/applications/collaboration-ui-app/
WORKDIR /opt/applications/collaboration-ui-app/
CMD ["java","-jar", "collaboration-ui-app-0.0.1-SNAPSHOT.jar"]
