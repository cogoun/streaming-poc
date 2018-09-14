package com.cogoun.streaming.collaboration;

import com.cogoun.streaming.domain.Collaboration;
import com.cogoun.streaming.event.CollaborationEvent;
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
public class CollaborationConsumer {

    public static final String CONSUMING_TOPIC = Topics.COLLABORATION_EVENT_TOPIC;
    public static final String CONSUMERS_GROUP = "collaboration.consumers.group";
    private static Logger LOGGER = LoggerFactory.getLogger(CollaborationConsumer.class);

    private final CountDownLatch latch = new CountDownLatch(1);

    @Autowired private CollaborationRepository collaborationRepository;
    @Autowired private TaskCommandProducer taskCommandProducer;
    @Autowired private NotificationCommandProducer notificationCommandProducer;

    @KafkaListener(
            topics = CONSUMING_TOPIC,
            groupId = CONSUMERS_GROUP,
            containerFactory = "concurrentKafkaListenerContainerFactory")
    public void consume(CollaborationEvent collaborationEvent) {
        this.latch.countDown();
        Collaboration collaboration = Collaboration.Builder.from(collaborationEvent);
        Collaboration latest =  StreamSupport.stream(collaborationRepository.findAll().spliterator(), false)
            .max(Comparator.comparing(Collaboration::getId))
            .orElse(emptyCollaboration());
        collaboration.setId(latest.getId()+1);
        Collaboration savedCollaboration = collaborationRepository.save(collaboration);
        LOGGER.info("Collaboration: [" + collaboration.toString() + "] was saved in the database");

        try {
            notificationCommandProducer.produceNotificationCommandFromCollaboration(collaboration);
            taskCommandProducer.produceTaskCommandFromCollaboration(collaboration);
        } catch (Exception e) {
            LOGGER.error("Problem producing a Notification or Task Command");
        }
    }

    private Collaboration emptyCollaboration() {
        Collaboration collaboration = new Collaboration();
        collaboration.setId(0);
        return collaboration;
    }
}
