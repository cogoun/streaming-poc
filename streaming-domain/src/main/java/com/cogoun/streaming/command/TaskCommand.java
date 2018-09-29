package com.cogoun.streaming.command;

import com.cogoun.streaming.domain.Collaboration;

import java.util.List;
import java.util.stream.Collectors;

public class TaskCommand {
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
        public static TaskCommand from(Collaboration collaboration, String userId) {
            TaskCommand command = new TaskCommand();
            command.setUserId(userId);
            command.setId(String.valueOf(collaboration.getId()));
            command.setTaskType("Collaboration");
            command.setTitle(collaboration.getTitle());
            command.setUserId(userId);
            return command;
        }

        public static List<TaskCommand> from(Collaboration collaboration) {
            return collaboration.getParticipants().stream()
                    .map(user -> from(collaboration, user))
                    .collect(Collectors.toList());
        }
    }
}
