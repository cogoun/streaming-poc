package com.cogoun.streaming.command;

import com.cogoun.streaming.domain.Collaboration;

public class NotificationCommand {

    public static class Builder {
        public static NotificationCommand from(Collaboration collaboration) {
            NotificationCommand command = new NotificationCommand();
            return command;
        }
    }
}
