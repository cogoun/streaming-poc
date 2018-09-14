package com.cogoun.streaming.event;

import com.cogoun.streaming.domain.Collaboration;

import java.io.Serializable;
import java.util.List;

public class CollaborationEvent implements Serializable {

    private CollaborationEventType type;
    private long id;
    private String title;
    private String deadline;
    private String substanceName;
    private String processName;
    private String caseNumber;
    private List<String> participants;
    private String collaborationStatus;
    private boolean supportingDocuments;

    public CollaborationEventType getType() {
        return type;
    }

    public void setType(CollaborationEventType type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getSubstanceName() {
        return substanceName;
    }

    public void setSubstanceName(String substanceName) {
        this.substanceName = substanceName;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public String getCollaborationStatus() {
        return collaborationStatus;
    }

    public void setCollaborationStatus(String collaborationStatus) {
        this.collaborationStatus = collaborationStatus;
    }

    public boolean isSupportingDocuments() {
        return supportingDocuments;
    }

    public void setSupportingDocuments(boolean supportingDocuments) {
        this.supportingDocuments = supportingDocuments;
    }

    public static class Builder {
        public static CollaborationEvent from(Collaboration collaboration) {
            CollaborationEvent collaborationEvent = new CollaborationEvent();
            collaborationEvent.setDeadline(collaboration.getDeadline());
            collaborationEvent.setTitle(collaboration.getTitle());
            collaborationEvent.setCaseNumber(collaboration.getCaseNumber());
            collaborationEvent.setCollaborationStatus(collaboration.getCollaborationStatus());
            collaborationEvent.setParticipants(collaboration.getParticipants());
            collaborationEvent.setSubstanceName(collaboration.getSubstanceName());
            collaborationEvent.setProcessName(collaboration.getProcessName());
            collaborationEvent.setId(collaboration.getId());
            collaborationEvent.setSupportingDocuments(collaboration.isSupportingDocuments());
            return collaborationEvent;
        }
    }

    public enum CollaborationEventType {
        STARTED, COMPLETED, UPDATED;
    }
}
