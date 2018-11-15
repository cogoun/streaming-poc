package com.cogoun.streaming.notification;

import com.cogoun.streaming.command.NotificationCommand;
import com.cogoun.streaming.topics.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class NotificationCommandConsumer {

    public static final String CONSUMING_TOPIC = Topics.NOTIFICATION_COMMAND_TOPIC;
    private static final String CONSUMERS_GROUP = "notification.command.consumer.group";
    private static Logger LOGGER = LoggerFactory.getLogger(NotificationCommandConsumer.class);

    private final CountDownLatch latch = new CountDownLatch(1);

    @Autowired private NotificationEventProducer notificationEventProducer;

    @KafkaListener(
            topics = CONSUMING_TOPIC,
            groupId = CONSUMERS_GROUP,
            containerFactory = "concurrentKafkaListenerContainerFactory")
    public void consume(NotificationCommand notificationCommand) {
        this.latch.countDown();

        try {
            notificationEventProducer.produceNotificationEventFromCommand(notificationCommand);
        } catch (Exception e) {
            LOGGER.error("Problem producing a Notification or Task Command");
        }
    }
}
