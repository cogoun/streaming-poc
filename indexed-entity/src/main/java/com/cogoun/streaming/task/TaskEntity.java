package com.cogoun.streaming.task;

import com.cogoun.streaming.domain.Task;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Document(indexName = "task-index", type = "task")
public class TaskEntity extends Task implements Serializable {
    @Id
    public String getId() {
        return super.getId();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static class Builder {

        public static TaskEntity from(Task task) {
            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setId(task.getId());
            taskEntity.setTaskType(task.getTaskType());
            taskEntity.setTitle(task.getTitle());
            taskEntity.setUserId(task.getUserId());
            return taskEntity;
        }
    }
}
