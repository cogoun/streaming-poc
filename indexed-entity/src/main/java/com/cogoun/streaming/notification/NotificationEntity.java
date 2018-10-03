package com.cogoun.streaming.notification;

import com.cogoun.streaming.domain.Notification;
import com.cogoun.streaming.event.NotificationEvent;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Document(indexName = "notification-index", type = "notification")
public class NotificationEntity extends Notification implements Serializable {

    @Id
    public String getId() {
        return super.getId();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
