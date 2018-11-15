#!/bin/bash

inputFile=kafka-acls.definition
outputFile=create-acls.sh
authorizer="kafka.security.auth.SimpleAclAuthorizer"
authorizerProperties="zookeeper.connect=zookeeper:2181"
kafkaAclCommand="/opt/kafka/bin/kafka-acls.sh"
cluster="kafka-cluster"

rm "$outputFile"
printf "#!/bin/bash\n\n" >> "$outputFile"

while IFS='' read -r line || [[ -n "$line" ]]; do
    IFS=' ' read -ra data <<< "$line"
    user="${data[0]}"
    action="${data[1]}"
    topic="${data[2]}"
    consumerGroup="${data[3]}"
    consumerGroupOption="--group $consumerGroup"
    if [ -z "$consumerGroup" ]
    then
        consumerGroupOption=""
    fi
    printf "$kafkaAclCommand --authorizer $authorizer --authorizer-properties $authorizerProperties  --add --allow-principal User:$user --$action --topic $topic $consumerGroupOption\n" >> "$outputFile"
    printf "$kafkaAclCommand --authorizer $authorizer --authorizer-properties $authorizerProperties  --add --allow-principal User:$user --operation create --cluster\n" >> "$outputFile"
done < "$inputFile"