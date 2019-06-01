package com.example.mobile.Repositories.models;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Option {
    @Expose(serialize = false, deserialize = true)
    private int id;
    @Expose
    private String intituleOption;

    @Expose(serialize = false)
    private int voteCount;


    private boolean isChosen;


    public Option() {
//        this.votes = new ArrayList();
        this.isChosen = false;
        voteCount = 0;
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof  Option) {
            return  this.id == ((Option) obj).getId();
        }
        return super.equals(obj);
    }

    public static void updateStats(List<Option> l1, List<Option> l2) {
        int index;
        for (Option option : l2) {
            index = l1.indexOf(option);
            if (index != -1) {
                l1.get(index).setVoteCount(option.getVoteCount());
            }
        }
    }
}
