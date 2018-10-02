package com.cogoun.streaming.task;

import com.cogoun.streaming.domain.Task;
import com.cogoun.streaming.event.TaskEvent;
import com.cogoun.streaming.topics.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.concurrent.CountDownLatch;
import java.util.stream.StreamSupport;

@Component
public class TaskEventConsumer {

    public static final String CONSUMING_TOPIC = Topics.TASK_EVENT_TOPIC;
    private static final String CONSUMERS_GROUP = "task.event.consumer.group";
    private static Logger LOGGER = LoggerFactory.getLogger(TaskEventConsumer.class);

    private final CountDownLatch latch = new CountDownLatch(1);

    @Autowired private TaskIndexingRepository taskIndexingRepository;
    @Autowired private ElasticsearchOperations elasticsearchOperations;

    @KafkaListener(
            topics = CONSUMING_TOPIC,
            groupId = CONSUMERS_GROUP,
            containerFactory = "concurrentKafkaListenerContainerFactory")
    public void consume(TaskEvent taskEvent) {
        this.latch.countDown();

        try {
            LOGGER.info("Task: [" + taskEvent.toString() + "] was consumed.");
            elasticsearchOperations.createIndex(Task.class);
            Task task = Task.Builder.from(taskEvent);
            taskIndexingRepository.save(task);
            LOGGER.info("Task: [" + task.toString() + "] was indexed.");
        } catch (Exception e) {
            LOGGER.error("Problem indexing a Task event: " + e.getMessage());
        }
    }

    private Task emptyTask() {
        Task task = new Task();
        task.setId("0");
        return task;
    }

}
