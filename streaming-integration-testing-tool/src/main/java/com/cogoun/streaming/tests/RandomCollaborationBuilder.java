package com.cogoun.streaming.tests;

import com.cogoun.streaming.domain.Collaboration;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomCollaborationBuilder {

    private static final int MIN_NUMBER_OF_PARTICIPANTS = 3;
    private static final int MAX_NUMBER_OF_PARTICIPANTS = 10;

    public static Collaboration build(long id) {
        Collaboration collaboration = new Collaboration();

        collaboration.setProcessName(RandomStringUtils.random(10, true, true));
        collaboration.setSubstanceName(RandomStringUtils.random(10, true, true));
        collaboration.setCollaborationStatus(RandomStringUtils.random(10, true, true));
        collaboration.setCaseNumber(RandomStringUtils.random(10, true, true));
        collaboration.setDeadline(RandomStringUtils.random(10, true, true));
        collaboration.setId(id);
        collaboration.setTitle(RandomStringUtils.random(30, true, true));
        collaboration.setParticipants(buildParticipants());
        collaboration.setSupportingDocuments(false);

        return collaboration;
    }

    private static List<String> buildParticipants() {
        int size = ThreadLocalRandom.current().nextInt(MIN_NUMBER_OF_PARTICIPANTS, MAX_NUMBER_OF_PARTICIPANTS + 1);
        List<String> participants = new ArrayList<>(size);
        for (int i=0; i<size; i++) {
            participants.add(i, RandomStringUtils.random(5, true, true));
        }
        return participants;
    }
}
