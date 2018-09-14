package com.cogoun.streaming.notification;

import com.cogoun.streaming.command.TaskCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class TaskCommandConsumer {

    public static final String CONSUMING_TOPIC = "taskCommandTopic";
    private static Logger LOGGER = LoggerFactory.getLogger(TaskCommandConsumer.class);

    private final CountDownLatch latch = new CountDownLatch(1);

    @Autowired private TaskEventProducer taskEventProducer;

    @KafkaListener(
            topics = CONSUMING_TOPIC,
            groupId = "collaboration.consumers.group",
            containerFactory = "collaborationEventConcurrentKafkaListenerContainerFactory")
    public void consume(TaskCommand taskCommand) {
        this.latch.countDown();

        try {
            taskEventProducer.produceTaskEventFromCommand(taskCommand);
        } catch (Exception e) {
            LOGGER.error("Problem producing a Notification or Task Command");
        }
    }

}
