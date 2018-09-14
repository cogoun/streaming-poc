package com.cogoun.streaming.domain;

import com.cogoun.streaming.event.CollaborationEvent;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

@RedisHash("Collaboration")
public class Collaboration implements Serializable {

    private long id;
    private String title;
    private String deadline;
    private String substanceName;
    private String processName;
    private String caseNumber;
    private List<String> participants;
    private String collaborationStatus;
    private boolean supportingDocuments;

    public static class Builder {
        public static Collaboration from(CollaborationEvent collaborationEvent) {
            Collaboration collaboration = new Collaboration();
            collaboration.setTitle(collaborationEvent.getTitle());
            collaboration.setDeadline(collaborationEvent.getDeadline());
            collaboration.setCaseNumber(collaborationEvent.getCaseNumber());
            collaboration.setCollaborationStatus(collaborationEvent.getCollaborationStatus());
            collaboration.setParticipants(collaborationEvent.getParticipants());
            collaboration.setSubstanceName(collaborationEvent.getSubstanceName());
            collaboration.setProcessName(collaborationEvent.getProcessName());
            collaboration.setId(collaborationEvent.getId());
            collaboration.setSupportingDocuments(collaborationEvent.isSupportingDocuments());
            return collaboration;
        }
    }

    public long getId() {
        return id;
    }

    public Collaboration setId(long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Collaboration setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDeadline() {
        return deadline;
    }

    public Collaboration setDeadline(String deadline) {
        this.deadline = deadline;
        return this;
    }

    public String getSubstanceName() {
        return substanceName;
    }

    public Collaboration setSubstanceName(String substanceName) {
        this.substanceName = substanceName;
        return this;
    }

    public String getProcessName() {
        return processName;
    }

    public Collaboration setProcessName(String processName) {
        this.processName = processName;
        return this;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public Collaboration setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
        return this;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public Collaboration setParticipants(List<String> participants) {
        this.participants = participants;
        return this;
    }

    public String getCollaborationStatus() {
        return collaborationStatus;
    }

    public Collaboration setCollaborationStatus(String collaborationStatus) {
        this.collaborationStatus = collaborationStatus;
        return this;
    }

    public boolean isSupportingDocuments() {
        return supportingDocuments;
    }

    public Collaboration setSupportingDocuments(boolean supportingDocuments) {
        this.supportingDocuments = supportingDocuments;
        return this;
    }

    @Override
    public String toString() {
        return "Collaboration{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", deadline='" + deadline + '\'' +
                ", substanceName='" + substanceName + '\'' +
                ", processName='" + processName + '\'' +
                ", caseNumber='" + caseNumber + '\'' +
                ", participants=" + participants +
                ", collaborationStatus='" + collaborationStatus + '\'' +
                ", supportingDocuments=" + supportingDocuments +
                '}';
    }
}
