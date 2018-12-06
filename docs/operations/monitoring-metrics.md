# Monitoring

Monitoring of the platform is of paramount importance. This is due to
the micro-service implementation that relies on a messaging mechanism
for loosely coupled inter-service communication.

## High level concepts
The following concepts need to be considered when implementing an
end-to-end monitoring solution:

- **metric emitters**
- **metric relays**
- **metric databases** that store metrics
- **metric UIs** that display metrics as graphs, text, etc.
- **alerting mechanisms** that identify interesting cases and sent alerts
- **communication channels** that are used to sent the alert recipients

## Technology stack
The streaming platform utilises the following toolset:
- Metric emitter: Jolokia/Actuator
- Metric relay: Telegraf/Actuator
- Metric database: InfluxDB
- Metric UI: Grafana
- Alerting: Grafana
- Communication channel: Email (or Slack)

Monitoring in the streaming platform is implemented using the following
libraries and applications:

- **Spring Boot Actuator** (can be used on a non spring-boot application or war).
This is a library that provides metrics to various time series databases.
Actuator in a Spring context can automatically sent metrics to a
time-series database therefore simplifying the whole process.
- **InfluxDB**. This is the time series database of choice. The database
can be queried in order to access or aggregate metrics.
- **Telegraf**. Telegraf is responsible for relaying metrics from one
system to the time-series database. In this case Telegraf will be used
as a relay for Elasticsearch, Redis, Kafka and Zookeeper. For Spring
applications Telegraf is not necessary since Actuator already takes
care of relaying.
- **Grafana**. Grafana will read metrics from InfluxDB and display
them in various dashboards. Dashboards consist of panels such as
graphs/tables/gauges/single-stats. Each panel can be filled with one
or more metrics coming from the the database.

## Metrics to watch for

The following metrics need to measured:

### Spring Boot applications
- jvm.memory.used (bytes)
- jvm.memory.max (bytes)
- process.cpu.usage (min, max and mean)
- system.load.average.1m (a load of 1.25 means that a system with one
CPU is overloaded by 25%)
- system.cpu.count (the number of CPUs on the system)

### Liferay
TBD

### Kafka
TBD

### Zookeeper
TBD

### Elasticsearch
- elasticsearch_os.cpu_percent
- elasticsearch_os.mem_used_percent
- elasticsearch_jvm.uptime_in_millis
- elasticsearch_indices.docs_count
#### Usage
- elasticsearch_jvm.mem_heap_used_percent
- elasticsearch_process.cpu_percent
- elasticsearch_fs.least_usage_estimate_used_disk_percent
### Redis
- redis.uptime (seconds)
- redis.used_memory (bytes)
- redis_keyspace.keys
#### Clients and connections
- redis.clients
- redis.blocked_clients
- redis.rejected_connections
#### Hits and misses
- redis.keyspace_hitrate
- redis.keyspace_misses
- redis.keyspace_hits
#### Memory usage
- redis.used_memory
- redis.total_system_memory