package com.cogoun.streaming.notification;

import com.cogoun.streaming.command.NotificationCommand;
import com.cogoun.streaming.domain.Collaboration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class NotificationCommandProducer {

    private static Logger LOGGER = LoggerFactory.getLogger(NotificationCommandProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic}")
    private String topic;

    public void produceNotificationCommandFromCollaboration(Collaboration collaboration) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        LOGGER.info("Incoming collaboration class is: " + collaboration.toString());

        NotificationCommand taskCommand = NotificationCommand.Builder.from(collaboration);
        String messageJson;
        messageJson = objectMapper.writeValueAsString(taskCommand);
        LOGGER.info("message: " + messageJson + " is sent from topic: " + topic);
        Future future = kafkaTemplate.send(topic, messageJson);
        future.get();
    }


}
