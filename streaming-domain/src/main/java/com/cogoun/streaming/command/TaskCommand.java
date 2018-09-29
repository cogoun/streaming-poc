package com.cogoun.streaming.command;

import com.cogoun.streaming.domain.Collaboration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        public static TaskCommand from(Collaboration collaboration, String userId, int taskId) {
            TaskCommand command = new TaskCommand();
            command.setUserId(userId);
            command.setId(String.valueOf(collaboration.getId() + taskId));
            command.setTaskType("Collaboration");
            command.setTitle(collaboration.getTitle());
            command.setUserId(userId);
            return command;
        }

        public static List<TaskCommand> from(Collaboration collaboration) {
            return IntStream
                    .range(0, collaboration.getParticipants().size())
                    .mapToObj(i -> from(collaboration, collaboration.getParticipants().get(i), i))
                    .collect(Collectors.toList());
        }
    }
}
