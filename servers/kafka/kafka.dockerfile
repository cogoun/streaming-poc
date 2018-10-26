FROM wurstmeister/kafka:latest
COPY servers/kafka/jaas.conf /opt/kafka/
# COPY servers/kafka/create-acls.sh /opt/kafka/
# COPY servers/kafka/set-kafka-opts.sh /opt/kafka/
# COPY servers/kafka/start-kafka.sh /opt/kafka/
