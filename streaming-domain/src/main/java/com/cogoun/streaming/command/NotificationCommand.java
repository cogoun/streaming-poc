package com.cogoun.streaming.command;

import com.cogoun.streaming.domain.Collaboration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NotificationCommand {
    private String id;
    private String userId;
    private String message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

        private static NotificationCommand from(Collaboration collaboration, String userId, int notificationId) {
            NotificationCommand command = new NotificationCommand();
            command.setId(String.valueOf(collaboration.getId()) + "-" + String.valueOf(notificationId));
            command.setUserId(userId);
            command.setMessage("The collaboration with title: " + collaboration.getTitle() + " has an updated status: " + collaboration.getCollaborationStatus());
            return command;
        }

        public static List<NotificationCommand> from(Collaboration collaboration) {
            return IntStream
                    .range(0, collaboration.getParticipants().size())
                    .mapToObj(i -> from(collaboration, collaboration.getParticipants().get(i), i))
                    .collect(Collectors.toList());
        }
    }
}
