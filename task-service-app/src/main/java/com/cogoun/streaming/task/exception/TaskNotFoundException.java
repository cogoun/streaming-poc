package com.cogoun.streaming.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaskNotFoundException extends RuntimeException {

    private long taskId = -1;

    public TaskNotFoundException(long taskId) {
        this.taskId = taskId;
    }

    @Override
    public String getMessage() {
        return "Task with id: " + taskId + " could not be found. " + super.getMessage();
    }
}
