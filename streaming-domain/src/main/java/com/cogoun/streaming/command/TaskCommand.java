package com.cogoun.streaming.command;

import com.cogoun.streaming.domain.Collaboration;
import com.cogoun.streaming.event.CollaborationEvent;

public class TaskCommand {

    public static class Builder {
        public static TaskCommand from(Collaboration collaboration) {
            TaskCommand command = new TaskCommand();
            return command;
        }
    }
}
