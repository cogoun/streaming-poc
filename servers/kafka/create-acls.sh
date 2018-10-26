#!/bin/bash

echo "Create ACLs"

if [[ -z "$KAFKA_CREATE_ACLS" ]]; then
    exit 0
fi

if [[ -z "$START_TIMEOUT" ]]; then
    START_TIMEOUT=600
fi

start_timeout_exceeded=false
count=0
step=10
while netstat -lnt | awk '$4 ~ /:'"$KAFKA_PORT"'$/ {exit 1}'; do
    echo "waiting for kafka to be ready"
    sleep $step;
    count=$((count + step))
    if [ $count -gt $START_TIMEOUT ]; then
        start_timeout_exceeded=true
        break
    fi
done

if $start_timeout_exceeded; then
    echo "Not able to auto-create ACLs (waited for $START_TIMEOUT sec)"
    exit 1
fi

# introduced in 0.9. In earlier versions, acls were not available
source "/usr/bin/versions.sh"
if [[ "$MAJOR_VERSION" == "0" && "$MINOR_VERSION" -gt "8" ]] || [[ "$MAJOR_VERSION" -gt "0" ]]; then
    KAFKA_0_9_OPTS="--if-not-exists"
fi

# Expected format:
#   topicName:principal
IFS="${KAFKA_CREATE_TOPICS_SEPARATOR-,}"; for aclToCreate in $KAFKA_CREATE_ACLS; do
    echo "creating ACL: $aclToCreate"
    IFS=':' read -r -a aclConfig <<< "$aclToCreate"

    COMMAND="JMX_PORT='' ${KAFKA_HOME}/bin/kafka-acls.sh \\
		--authorizer-properties \\
		zookeeper.connect=${KAFKA_ZOOKEEPER_CONNECT} \\
		--add \\
		--allow-principal User:${aclConfig[1]} \\
		--operation Read --operation Write \\
		--topic ${aclConfig[0]} \\
		${KAFKA_0_9_OPTS} &"
    eval "${COMMAND}"
done

wait