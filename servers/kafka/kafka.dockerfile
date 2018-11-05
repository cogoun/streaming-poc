FROM wurstmeister/kafka:latest
COPY servers/kafka/jaas.conf /opt/kafka/
COPY servers/kafka/create-acls.sh /usr/bin/
RUN chmod +x /usr/bin/create-acls.sh

