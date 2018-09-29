#!/usr/bin/env bash

docker exec kafka ./opt/kafka/bin/kafka-topics.sh --zookeeper zookeeper:2181 --delete --topic collaborationEventTopic
docker exec kafka ./opt/kafka/bin/kafka-topics.sh --zookeeper zookeeper:2181 --delete --topic notificationCommandTopic
docker exec kafka ./opt/kafka/bin/kafka-topics.sh --zookeeper zookeeper:2181 --delete --topic notificationEventTopic
docker exec kafka ./opt/kafka/bin/kafka-topics.sh --zookeeper zookeeper:2181 --delete --topic taskCommandTopic
docker exec kafka ./opt/kafka/bin/kafka-topics.sh --zookeeper zookeeper:2181 --delete --topic taskEventTopic

docker exec kafka ./opt/kafka/bin/kafka-topics.sh --zookeeper zookeeper:2181 --create --topic notificationCommandTopic --partitions 1 --replication-factor 1
docker exec kafka ./opt/kafka/bin/kafka-topics.sh --zookeeper zookeeper:2181 --create --topic notificationEventTopic --partitions 1 --replication-factor 1
docker exec kafka ./opt/kafka/bin/kafka-topics.sh --zookeeper zookeeper:2181 --create --topic taskCommandTopic --partitions 1 --replication-factor 1
docker exec kafka ./opt/kafka/bin/kafka-topics.sh --zookeeper zookeeper:2181 --create --topic taskEventTopic --partitions 1 --replication-factor 1
docker exec kafka ./opt/kafka/bin/kafka-topics.sh --zookeeper zookeeper:2181 --create --topic collaborationEventTopic --partitions 1 --replication-factor 1

curl -X DELETE "localhost:9200/task-index"
curl -X DELETE "localhost:9200/notification-index"

docker exec redis ./opt/bitnami/redis/bin/redis-cli flushall
