package com.cogoun.streaming.task;

import com.cogoun.streaming.command.TaskCommand;
import com.cogoun.streaming.topics.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class TaskCommandConsumer {

    public static final String CONSUMING_TOPIC = Topics.TASK_COMMAND_TOPIC;
    private static final String CONSUMERS_GROUP = "task.command.consumer.group";
    private static Logger LOGGER = LoggerFactory.getLogger(TaskCommandConsumer.class);

    private final CountDownLatch latch = new CountDownLatch(1);

    @Autowired private TaskEventProducer taskEventProducer;

    @KafkaListener(
            topics = CONSUMING_TOPIC,
            groupId = CONSUMERS_GROUP,
            containerFactory = "concurrentKafkaListenerContainerFactory")
    public void consume(TaskCommand taskCommand) {
        this.latch.countDown();

        try {
            taskEventProducer.produceTaskEventFromCommand(taskCommand);
        } catch (Exception e) {
            LOGGER.error("Problem producing a Notification or Task Command");
        }
    }

}
