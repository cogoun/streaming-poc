FROM bitnami/zookeeper:latest
COPY servers/zookeeper/jaas.conf /opt/bitnami/zookeeper/
