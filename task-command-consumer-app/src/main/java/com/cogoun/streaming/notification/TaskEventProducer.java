package com.cogoun.streaming.notification;

import com.cogoun.streaming.command.TaskCommand;
import com.cogoun.streaming.event.TaskEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class TaskEventProducer {

    private static Logger LOGGER = LoggerFactory.getLogger(TaskEventProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic}")
    private String topic;

    public void produceTaskEventFromCommand(TaskCommand taskCommand) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        LOGGER.info("Incoming collaboration class is: " + taskCommand.toString());

        TaskEvent taskEvent = TaskEvent.Builder.from(taskCommand);
        String messageJson;
        messageJson = objectMapper.writeValueAsString(taskEvent);
        LOGGER.info("message: " + messageJson + " is sent from topic: " + topic);
        Future future = kafkaTemplate.send(topic, messageJson);
        future.get();
    }

}
