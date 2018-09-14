package com.cogoun.streaming.notification.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotificationNotFoundException extends RuntimeException {

    private long notificationId = -1;

    public NotificationNotFoundException(long notificationId) {
        this.notificationId = notificationId;
    }

    @Override
    public String getMessage() {
        return "Notification with id: " + notificationId + " could not be found. " + super.getMessage();
    }
}
