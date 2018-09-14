package com.cogoun.streaming.event;

import com.cogoun.streaming.command.TaskCommand;

import java.io.Serializable;

public class TaskEvent implements Serializable {
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
        public static TaskEvent from(TaskCommand taskCommand) {
            TaskEvent event = new TaskEvent();
            event.setId(taskCommand.getId());
            event.setTaskType(taskCommand.getTaskType());
            event.setTitle(taskCommand.getTitle());
            event.setUserId(taskCommand.getUserId());
            return event;
        }
    }
}
