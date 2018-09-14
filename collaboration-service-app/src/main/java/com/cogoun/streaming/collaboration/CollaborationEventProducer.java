package com.cogoun.streaming.collaboration;

import com.cogoun.streaming.domain.Collaboration;
import com.cogoun.streaming.event.CollaborationEvent;
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
public class CollaborationEventProducer {

    private static Logger LOGGER = LoggerFactory.getLogger(CollaborationEventProducer.class);
    private static final String PRODUCING_TOPIC = Topics.COLLABORATION_EVENT_TOPIC;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void produceStartCollaborationRoundEvent(Collaboration collaboration) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        LOGGER.info("Incoming collaboration is: " + collaboration.toString());

        CollaborationEvent collaborationEvent = CollaborationEvent.Builder.from(collaboration);
        String messageJson = objectMapper.writeValueAsString(collaborationEvent);
        LOGGER.info("message: " + messageJson + " will be sent to topic: " + PRODUCING_TOPIC);
        Future future = kafkaTemplate.send(PRODUCING_TOPIC, messageJson);
        future.get();
    }


}
