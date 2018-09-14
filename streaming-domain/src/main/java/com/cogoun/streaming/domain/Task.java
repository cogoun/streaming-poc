package com.cogoun.streaming.domain;

import com.cogoun.streaming.event.TaskEvent;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Task")
public class Task implements Serializable {
    private long id;
    private String title;
    private String taskType;
    private String userId;


    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
