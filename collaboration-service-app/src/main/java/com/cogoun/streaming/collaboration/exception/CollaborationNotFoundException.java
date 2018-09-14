package com.cogoun.streaming.collaboration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CollaborationNotFoundException extends RuntimeException {

    private long collaborationId = -1;

    public CollaborationNotFoundException(long collaborationId) {
        this.collaborationId = collaborationId;
    }

    @Override
    public String getMessage() {
        return "Collaboration with id: " + collaborationId + " could not be found. " + super.getMessage();
    }
}
