package com.cogoun.streaming.domain;

import com.cogoun.streaming.event.NotificationEvent;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Notification")
public class Notification implements Serializable {
    private long id;
    private String userId;
    private String message;

    public String getUserId() {
        return userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Notification setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Notification setMessage(String message) {
        this.message = message;
        return this;
    }

    public static class Builder {
        public static Notification from(NotificationEvent notificationEvent) {
            Notification notification = new Notification();
            notification.setId(notificationEvent.getId());
            notification.setMessage(notificationEvent.getMessage());
            notification.setUserId(notificationEvent.getUserId());
            return notification;
        }
    }
}
