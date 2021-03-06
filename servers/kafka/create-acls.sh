#!/bin/bash

/opt/kafka/bin/kafka-acls.sh --authorizer kafka.security.auth.SimpleAclAuthorizer --authorizer-properties zookeeper.connect=zookeeper:2181  --add --allow-principal User:collaborationTransformerApp --producer --topic notificationCommandTopic
 
/opt/kafka/bin/kafka-acls.sh --authorizer kafka.security.auth.SimpleAclAuthorizer --authorizer-properties zookeeper.connect=zookeeper:2181  --add --allow-principal User:collaborationTransformerApp --operation create --cluster
/opt/kafka/bin/kafka-acls.sh --authorizer kafka.security.auth.SimpleAclAuthorizer --authorizer-properties zookeeper.connect=zookeeper:2181  --add --allow-principal User:collaborationTransformerApp --producer --topic taskCommandTopic
 
/opt/kafka/bin/kafka-acls.sh --authorizer kafka.security.auth.SimpleAclAuthorizer --authorizer-properties zookeeper.connect=zookeeper:2181  --add --allow-principal User:collaborationTransformerApp --operation create --cluster
/opt/kafka/bin/kafka-acls.sh --authorizer kafka.security.auth.SimpleAclAuthorizer --authorizer-properties zookeeper.connect=zookeeper:2181  --add --allow-principal User:collaborationTransformerApp --consumer --topic collaborationEventTopic --group collaboration.consumer.group
/opt/kafka/bin/kafka-acls.sh --authorizer kafka.security.auth.SimpleAclAuthorizer --authorizer-properties zookeeper.connect=zookeeper:2181  --add --allow-principal User:collaborationTransformerApp --operation create --cluster
/opt/kafka/bin/kafka-acls.sh --authorizer kafka.security.auth.SimpleAclAuthorizer --authorizer-properties zookeeper.connect=zookeeper:2181  --add --allow-principal User:collaborationToolApp --producer --topic collaborationEventTopic
 
/opt/kafka/bin/kafka-acls.sh --authorizer kafka.security.auth.SimpleAclAuthorizer --authorizer-properties zookeeper.connect=zookeeper:2181  --add --allow-principal User:collaborationToolApp --operation create --cluster
/opt/kafka/bin/kafka-acls.sh --authorizer kafka.security.auth.SimpleAclAuthorizer --authorizer-properties zookeeper.connect=zookeeper:2181  --add --allow-principal User:dynamicCaseConsumerApp --consumer --topic collaborationEventTopic --group dynamic.case.collaboration.consumer.group
/opt/kafka/bin/kafka-acls.sh --authorizer kafka.security.auth.SimpleAclAuthorizer --authorizer-properties zookeeper.connect=zookeeper:2181  --add --allow-principal User:dynamicCaseConsumerApp --operation create --cluster
/opt/kafka/bin/kafka-acls.sh --authorizer kafka.security.auth.SimpleAclAuthorizer --authorizer-properties zookeeper.connect=zookeeper:2181  --add --allow-principal User:taskCommandConsumerApp --consumer --topic taskCommandTopic --group task.command.consumer.group
/opt/kafka/bin/kafka-acls.sh --authorizer kafka.security.auth.SimpleAclAuthorizer --authorizer-properties zookeeper.connect=zookeeper:2181  --add --allow-principal User:taskCommandConsumerApp --operation create --cluster
/opt/kafka/bin/kafka-acls.sh --authorizer kafka.security.auth.SimpleAclAuthorizer --authorizer-properties zookeeper.connect=zookeeper:2181  --add --allow-principal User:taskCommandConsumerApp --producer --topic taskEventTopic
 
/opt/kafka/bin/kafka-acls.sh --authorizer kafka.security.auth.SimpleAclAuthorizer --authorizer-properties zookeeper.connect=zookeeper:2181  --add --allow-principal User:taskCommandConsumerApp --operation create --cluster
/opt/kafka/bin/kafka-acls.sh --authorizer kafka.security.auth.SimpleAclAuthorizer --authorizer-properties zookeeper.connect=zookeeper:2181  --add --allow-principal User:taskIndexerApp --consumer --topic taskEventTopic --group task.event.consumer.group
/opt/kafka/bin/kafka-acls.sh --authorizer kafka.security.auth.SimpleAclAuthorizer --authorizer-properties zookeeper.connect=zookeeper:2181  --add --allow-principal User:taskIndexerApp --operation create --cluster
/opt/kafka/bin/kafka-acls.sh --authorizer kafka.security.auth.SimpleAclAuthorizer --authorizer-properties zookeeper.connect=zookeeper:2181  --add --allow-principal User:notificationCommandConsumerApp --consumer --topic notificationCommandTopic --group notification.command.consumer.group
/opt/kafka/bin/kafka-acls.sh --authorizer kafka.security.auth.SimpleAclAuthorizer --authorizer-properties zookeeper.connect=zookeeper:2181  --add --allow-principal User:notificationCommandConsumerApp --operation create --cluster
/opt/kafka/bin/kafka-acls.sh --authorizer kafka.security.auth.SimpleAclAuthorizer --authorizer-properties zookeeper.connect=zookeeper:2181  --add --allow-principal User:notificationCommandConsumerApp --producer --topic notificationEventTopic
 
/opt/kafka/bin/kafka-acls.sh --authorizer kafka.security.auth.SimpleAclAuthorizer --authorizer-properties zookeeper.connect=zookeeper:2181  --add --allow-principal User:notificationCommandConsumerApp --operation create --cluster
/opt/kafka/bin/kafka-acls.sh --authorizer kafka.security.auth.SimpleAclAuthorizer --authorizer-properties zookeeper.connect=zookeeper:2181  --add --allow-principal User:notificationIndexerApp --consumer --topic notificationEventTopic --group notification.event.consumer.group
/opt/kafka/bin/kafka-acls.sh --authorizer kafka.security.auth.SimpleAclAuthorizer --authorizer-properties zookeeper.connect=zookeeper:2181  --add --allow-principal User:notificationIndexerApp --operation create --cluster
