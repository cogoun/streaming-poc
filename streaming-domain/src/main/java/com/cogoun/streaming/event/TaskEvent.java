package com.cogoun.streaming.event;

import com.cogoun.streaming.command.TaskCommand;

import java.io.Serializable;

public class TaskEvent implements Serializable {
    private String id;
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
        public static TaskEvent from(TaskCommand taskCommand) {
            TaskEvent event = new TaskEvent();
            event.setId(taskCommand.getId());
            event.setTaskType(taskCommand.getTaskType());
            event.setTitle(taskCommand.getTitle());
            event.setUserId(taskCommand.getUserId());
            return event;
        }
    }

    @Override
    public String toString() {
        return "TaskEvent{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", taskType='" + taskType + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
