package com.example.mobile.Repositories.models;


import com.google.gson.annotations.Expose;

public class Questionnaire {
    @Expose
    public String intituleQuestionnaire;
    @Expose(serialize = false, deserialize = true)
    public int id;


    public Questionnaire(){}

    public String getIntituleQuestionnaire() {
        return intituleQuestionnaire;
    }

    public void setIntituleQustionnaire(String intituleQuestionnaire) {
        this.intituleQuestionnaire = intituleQuestionnaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
