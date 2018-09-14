package com.cogoun.streaming.task;

import com.cogoun.streaming.command.TaskCommand;
import com.cogoun.streaming.event.TaskEvent;
import com.cogoun.streaming.topics.Topics;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class TaskEventProducer {

    private static Logger LOGGER = LoggerFactory.getLogger(TaskEventProducer.class);

    public static final String PRODUCING_TOPIC = Topics.TASK_EVENT_TOPIC;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void produceTaskEventFromCommand(TaskCommand taskCommand) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        LOGGER.info("Incoming task command is: " + taskCommand.toString());

        TaskEvent taskEvent = TaskEvent.Builder.from(taskCommand);
        String messageJson;
        messageJson = objectMapper.writeValueAsString(taskEvent);
        LOGGER.info("message: " + messageJson + " is sent to topic: " + PRODUCING_TOPIC);
        Future future = kafkaTemplate.send(PRODUCING_TOPIC, messageJson);
        future.get();
    }

}
