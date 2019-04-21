package com.example.mobile.Repositories.models;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;
import java.util.List;

public class Conference {
    private int id;
    @SerializedName("nom")
    private String title;
    private Date startDate;
    private Date endDate;
    private String codeAcces;
    private List<Attachment> attachments;

    public Conference(){}

    public Conference(String title, Date startDate, Date endDate){
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
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

    public String getCodeAcces() {
        return codeAcces;
    }

    public void setCodeAcces(String codeAcces) {
        this.codeAcces = codeAcces;
    }
}
