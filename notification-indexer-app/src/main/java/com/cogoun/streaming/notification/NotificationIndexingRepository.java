package com.cogoun.streaming.notification;

import com.cogoun.streaming.domain.Notification;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationIndexingRepository extends ElasticsearchRepository<Notification, String> {
}
