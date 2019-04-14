package com.example.mobile.Repositories.models;

import java.util.Date;
import java.util.List;

public class Conference {
    private int id;
    private String nom;
    private Date date;
    private String lieu;
    private int nbInteresses;
    private int nbNonInteresses;
    private String description;
    private String codeAcces;
    private List<Attachment> attachments;

    public Conference(){}

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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getNbInteresses() {
        return nbInteresses;
    }

    public void setNbInteresses(int nbInteresses) {
        this.nbInteresses = nbInteresses;
    }

    public int getNbNonInteresses() {
        return nbNonInteresses;
    }

    public void setNbNonInteresses(int nbNonInteresses) {
        this.nbNonInteresses = nbNonInteresses;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCodeAcces() {
        return codeAcces;
    }

    public void setCodeAcces(String codeAcces) {
        this.codeAcces = codeAcces;
    }
}
