package com.example.mobile.models;

import java.util.ArrayList;

/**
 * Created by neron on 4/10/19.
 */

public class Option {

    private int id;
    private String intituleOption;
    private ArrayList<Vote> votes;

    public Option() {
        this.votes = new ArrayList();
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

    public ArrayList<Vote> getVotes() {
        return votes;
    }

    public void setVotes(ArrayList<Vote> votes) {
        this.votes = votes;
    }

    public int getVoteCount() {
        return this.votes.size();
    }
}
