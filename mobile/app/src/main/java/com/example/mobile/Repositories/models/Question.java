package com.example.mobile.Repositories.models;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;


public class Question {
    @Expose(serialize = false, deserialize = true)
    private Integer id;
    @Expose
    @SerializedName("contenu")
    private String content;
    @Expose
    private Date timestamp;
    @Expose
    private String username;
    @Expose
    @SerializedName("conferenceId")
    private int confId;

    public Question(){}

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setTimestamp(String timestamp) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        TemporalAccessor accessor = timeFormatter.parse(timestamp);
        this.timestamp = Date.from(Instant.from(accessor));
    }

    public void setTimestamp(){
        this.timestamp = new Date();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getConfId() {
        return confId;
    }

    public void setConfId(int confId) {
        this.confId = confId;
    }
}
