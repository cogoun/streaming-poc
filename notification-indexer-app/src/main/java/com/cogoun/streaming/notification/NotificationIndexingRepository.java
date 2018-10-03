package com.cogoun.streaming.notification;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationIndexingRepository extends ElasticsearchRepository<NotificationEntity, String> {
}
