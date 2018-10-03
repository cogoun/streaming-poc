package com.cogoun.streaming.notification;

import com.cogoun.streaming.domain.Notification;
import com.cogoun.streaming.event.NotificationEvent;
import com.cogoun.streaming.topics.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class NotificationEventConsumer {

    public static final String CONSUMING_TOPIC = Topics.NOTIFICATION_EVENT_TOPIC;
    private static final String CONSUMERS_GROUP = "notification.event.consumer.group";
    private static Logger LOGGER = LoggerFactory.getLogger(NotificationEventConsumer.class);

    private final CountDownLatch latch = new CountDownLatch(1);

    @Autowired private NotificationIndexingRepository notificationIndexingRepository;
    @Autowired private ElasticsearchOperations elasticsearchOperations;

    @KafkaListener(
            topics = CONSUMING_TOPIC,
            groupId = CONSUMERS_GROUP,
            containerFactory = "concurrentKafkaListenerContainerFactory")
    public void consume(NotificationEvent notificationEvent) {
        this.latch.countDown();

        try {
            LOGGER.info("Notification: [" + notificationEvent.toString() + "] was consumed.");
            elasticsearchOperations.createIndex(Notification.class);
            Notification notification = Notification.Builder.from(notificationEvent);
            notificationIndexingRepository.save((NotificationEntity) notification);
            LOGGER.info("Notification: [" + notification.toString() + "] was indexed.");
        } catch (Exception e) {
            LOGGER.error("Problem indexing a Notification event: " + e.getMessage());
        }
    }

    private Notification emptyNotification() {
        Notification notification = new Notification();
        notification.setId("0");
        return notification;
    }

}
