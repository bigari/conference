package com.example.mobile.Repositories.models;

import com.google.gson.annotations.Expose;

/**
 * Created by neron on 6/1/19.
 */

public class Vote {

    @Expose
    private int participantId;
    @Expose
    private int optionId;

    @Expose (deserialize = false)
    private String accessKey;

    public Vote(int participantId, String accessKey, int optionId) {
        this.participantId = participantId;
        this.optionId = optionId;
        this.accessKey = accessKey;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }
}
