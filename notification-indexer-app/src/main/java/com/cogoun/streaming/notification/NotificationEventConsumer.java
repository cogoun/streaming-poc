package com.cogoun.streaming.notification;

import com.cogoun.streaming.domain.Notification;
import com.cogoun.streaming.domain.Task;
import com.cogoun.streaming.event.NotificationEvent;
import com.cogoun.streaming.topics.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.concurrent.CountDownLatch;
import java.util.stream.StreamSupport;

@Component
public class NotificationEventConsumer {

    public static final String CONSUMING_TOPIC = Topics.NOTIFICATION_EVENT_TOPIC;
    private static final String CONSUMERS_GROUP = "notification.event.consumer.group";
    private static Logger LOGGER = LoggerFactory.getLogger(NotificationEventConsumer.class);

    private final CountDownLatch latch = new CountDownLatch(1);

    @Autowired private NotificationIndexingRepository notificationIndexingRepository;

    @KafkaListener(
            topics = CONSUMING_TOPIC,
            groupId = CONSUMERS_GROUP,
            containerFactory = "concurrentKafkaListenerContainerFactory")
    public void consume(NotificationEvent notificationEvent) {
        this.latch.countDown();

        try {
            Notification task = Notification.Builder.from(notificationEvent);
            Notification latest = StreamSupport.stream(notificationIndexingRepository.findAll().spliterator(), false)
                    .max(Comparator.comparing(Notification::getId))
                    .orElse(emptyNotification());
            task.setId(latest.getId()+1);
            notificationIndexingRepository.save(task);
        } catch (Exception e) {
            LOGGER.error("Problem indexing a Notification event");
        }
    }

    private Notification emptyNotification() {
        Notification notification = new Notification();
        notification.setId(0);
        return notification;
    }

}
