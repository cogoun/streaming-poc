package com.cogoun.streaming.domain;

import com.cogoun.streaming.event.TaskEvent;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Document(indexName = "task-index", type = "task")
public class Task implements Serializable {
    @Id private String id;
    private String title;
    private String taskType;
    private String userId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static class Builder {
        public static Task from(TaskEvent taskEvent) {
            Task task = new Task();
            task.setId(taskEvent.getId());
            task.setTaskType(taskEvent.getTaskType());
            task.setTitle(taskEvent.getTitle());
            task.setUserId(taskEvent.getUserId());
            return task;
        }
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", taskType='" + taskType + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
