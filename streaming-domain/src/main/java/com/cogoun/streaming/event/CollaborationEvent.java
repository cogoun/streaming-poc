package com.cogoun.streaming.event;

import com.cogoun.streaming.domain.Collaboration;

public class CollaborationEvent {

    private CollaborationEventType type;
    private long id;
    private String subject;

    public CollaborationEventType getType() {
        return type;
    }

    public CollaborationEvent setType(CollaborationEventType type) {
        this.type = type;
        return this;
    }

    public long getId() {
        return id;
    }

    public CollaborationEvent setId(long id) {
        this.id = id;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public CollaborationEvent setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    @Override
    public String toString() {
        return "CollaborationEvent{" +
                "type=" + type +
                ", id=" + id +
                ", subject='" + subject + '\'' +
                '}';
    }

    public static class Builder {
        public static CollaborationEvent from(Collaboration collaboration, CollaborationEventType eventType) {
            CollaborationEvent event = new CollaborationEvent();
            event.setType(eventType);
            event.setSubject(collaboration.getSubject());
            event.setId(collaboration.getId());
            return event;
        }

        public static Collaboration from(CollaborationEvent collaborationEvent) {
            Collaboration collaboration = new Collaboration();
            collaboration.setSubject(collaborationEvent.getSubject());
            collaboration.setId(collaboration.getId());
            return collaboration;
        }
    }

    public enum CollaborationEventType {
        STARTED, COMPLETED, UPDATED;
    }
}
