package com.example.mobile.Repositories.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class Option {
    @Expose(serialize = false, deserialize = true)
    private int id;
    @Expose
    private String intituleOption;
    private int voteCount;


    public Option() {
//        this.votes = new ArrayList();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntituleOption() {
        return intituleOption;
    }

    public void setIntituleOption(String intituleOption) {
        this.intituleOption = intituleOption;
    }

//    public ArrayList<Vote> getVotes() {
//        return votes;
//    }
//
//    public void setVotes(ArrayList<Vote> votes) {
//        this.votes = votes;
//    }
//
//    public int getVoteCount() {
//        return this.votes.size();
//    }

    public int getVoteCount(){return voteCount;}

    public void setVoteCount(int voteCount){this.voteCount = voteCount;}
}
