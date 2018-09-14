package com.cogoun.streaming.command;

import com.cogoun.streaming.domain.Collaboration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NotificationCommand {
    private long id;
    private String userId;
    private String message;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class Builder {

        public static NotificationCommand from(Collaboration collaboration, String userId) {
            NotificationCommand command = new NotificationCommand();
            command.setId(collaboration.getId());
            command.setUserId(userId);
            command.setMessage("The collaboration with title: " + collaboration.getTitle() + " has an updated status: " + collaboration.getCollaborationStatus());
            return command;
        }

        public static List<NotificationCommand> from(Collaboration collaboration) {
            return collaboration.getParticipants().stream()
                    .map(user -> from(collaboration, user))
                    .collect(Collectors.toList());
        }
    }
}
