package com.example.mobile.Repositories.models;

public class Participant {
    private String accessKey;
    private int id;


    public Participant(){}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }
}
