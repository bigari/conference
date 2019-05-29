package com.example.mobile.Repositories.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

public class Conference {
    @Expose(serialize = false, deserialize = true)
    private int id;
    @Expose
    @SerializedName("nom")
    private String title;
    @Expose
    @SerializedName("dateDebut")
    private Date startDate;
    @Expose
    @SerializedName("dateFin")
    private Date endDate;
    @Expose
    @SerializedName("speakerId")
    private int speakerId;
    @Expose(serialize = false, deserialize = true)
    @SerializedName("codeAcces")
    private String accessCode;

    @Expose(serialize = true, deserialize = true)
    @SerializedName("enquetes")
    private List<Enquete> enquetes;
    @Expose(serialize = true, deserialize = true)
    @SerializedName("questionnaires")
    private List<Questionnaire> questionnaires;
    @Expose(serialize = true, deserialize = true)
    @SerializedName("questions")
    private List<Question> questions;


    public Conference(){
        questionnaires = new ArrayList<>();
        questions = new ArrayList<>();
        enquetes = new ArrayList<>();

    }

    public Conference(int speakerId, String title, Date startDate, Date endDate){
        this.title = title;
        this.speakerId = speakerId;
        this.startDate = startDate;
        this.endDate = endDate;
        questionnaires = new ArrayList<>();
        questions = new ArrayList<>();
        enquetes = new ArrayList<>();
    }

    public int getUserId() {
        return speakerId;
    }

    public void setUserId(int speakerId) {
        this.speakerId = speakerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }
}
