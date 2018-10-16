package com.cogoun.streaming.collaboration;

import com.cogoun.streaming.domain.Collaboration;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Collaboration")
public class CollaborationEntity extends Collaboration implements Serializable {

    @Override
    public String toString() {
        return super.toString();
    }

    public static class Builder {
        public static CollaborationEntity from(Collaboration collaboration) {
            CollaborationEntity collaborationEntity = new CollaborationEntity();
            collaborationEntity.setTitle(collaboration.getTitle());
            collaborationEntity.setDeadline(collaboration.getDeadline());
            collaborationEntity.setCaseNumber(collaboration.getCaseNumber());
            collaborationEntity.setCollaborationStatus(collaboration.getCollaborationStatus());
            collaborationEntity.setParticipants(collaboration.getParticipants());
            collaborationEntity.setSubstanceName(collaboration.getSubstanceName());
            collaborationEntity.setProcessName(collaboration.getProcessName());
            collaborationEntity.setId(collaboration.getId());
            collaborationEntity.setSupportingDocuments(collaboration.isSupportingDocuments());
            return collaborationEntity;
        }
    }
}
