package com.cogoun.streaming.domain;

import com.cogoun.streaming.event.NotificationEvent;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Document(indexName = "notification-index", type = "notification")
public class Notification implements Serializable {

    @Id private String id;
    private String userId;
    private String message;

    public String getUserId() {
        return userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    @Override
    public String toString() {
        return "Notification{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
