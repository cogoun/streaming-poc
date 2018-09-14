package com.cogoun.streaming.notification;

import com.cogoun.streaming.command.NotificationCommand;
import com.cogoun.streaming.event.NotificationEvent;
import com.cogoun.streaming.topics.Topics;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class NotificationEventProducer {

    private static Logger LOGGER = LoggerFactory.getLogger(NotificationEventProducer.class);

    public static final String PRODUCING_TOPIC = Topics.NOTIFICATION_EVENT_TOPIC;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void produceNotificationEventFromCommand(NotificationCommand notificationCommand) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        LOGGER.info("Incoming collaboration class is: " + notificationCommand.toString());

        NotificationEvent notificationEvent = NotificationEvent.Builder.from(notificationCommand);
        String messageJson;
        messageJson = objectMapper.writeValueAsString(notificationEvent);
        LOGGER.info("message: " + messageJson + " is sent to topic: " + PRODUCING_TOPIC);
        Future future = kafkaTemplate.send(PRODUCING_TOPIC, messageJson);
        future.get();
    }

}
