package com.cogoun.streaming.event;

import com.cogoun.streaming.command.NotificationCommand;

import java.io.Serializable;

public class NotificationEvent implements Serializable {
    private long id;
    private String userId;
    private String message;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static class Builder {

        public static NotificationEvent from(NotificationCommand notificationCommand) {
            NotificationEvent event = new NotificationEvent();
            event.setId(notificationCommand.getId());
            event.setMessage(notificationCommand.getMessage());
            event.setUserId(notificationCommand.getUserId());
            return event;
        }
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
}
