package com.cogoun.streaming.collaboration;

import com.cogoun.streaming.command.TaskCommand;
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
public class TaskCommandProducer {

    private static Logger LOGGER = LoggerFactory.getLogger(TaskCommandProducer.class);
    private static final String PRODUCING_TOPIC = Topics.TASK_COMMAND_TOPIC;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void produceTaskCommandFromCollaboration(Collaboration collaboration) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        LOGGER.info("Incoming collaboration class is: " + collaboration.toString());

        List<TaskCommand> taskCommands = TaskCommand.Builder.from(collaboration);
        for (TaskCommand taskCommand : taskCommands) {
            String messageJson;
            messageJson = objectMapper.writeValueAsString(taskCommand);
            LOGGER.info("message: " + messageJson + " will be sent to topic: " + PRODUCING_TOPIC);
            Future future = kafkaTemplate.send(PRODUCING_TOPIC, messageJson);
            future.get();
        }
    }


}
