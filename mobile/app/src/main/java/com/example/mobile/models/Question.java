package com.example.mobile.models;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;


public class Question {
    private int id;
    private String intitule;
    private Date timestamp;


    public Question(){}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
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
}
