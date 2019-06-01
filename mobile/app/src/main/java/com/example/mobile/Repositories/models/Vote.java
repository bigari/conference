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
}
