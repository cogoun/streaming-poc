package com.cogoun.streaming.collaboration;

import com.cogoun.streaming.command.NotificationCommand;
import com.cogoun.streaming.domain.Collaboration;
import com.cogoun.streaming.topics.Topics;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

@Service
public class NotificationCommandProducer {

    private static Logger LOGGER = LoggerFactory.getLogger(NotificationCommandProducer.class);
    private static final String PRODUCING_TOPIC = Topics.NOTIFICATION_COMMAND_TOPIC;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void produceNotificationCommandFromCollaboration(Collaboration collaboration) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        LOGGER.info("Incoming collaboration class is: " + collaboration.toString());

        List<NotificationCommand> notificationCommands = NotificationCommand.Builder.from(collaboration);
        for (NotificationCommand notificationCommand : notificationCommands) {
            String messageJson;
            messageJson = objectMapper.writeValueAsString(notificationCommand);
            LOGGER.info("message: " + messageJson + " is sent to topic: " + PRODUCING_TOPIC);
            Future future = kafkaTemplate.send(PRODUCING_TOPIC, messageJson);
            future.get();
        }
    }


}
