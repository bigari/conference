package com.example.mobile.Repositories.models;

import java.util.ArrayList;

/**
 * Created by neron on 4/10/19.
 */

public class Enquete {
    private int id;
    private String intituleEnquete;

    private ArrayList<Option> options;

    public Enquete() {
        options = new ArrayList();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntituleEnquete() {
        return intituleEnquete;
    }

    public void setIntituleEnquete(String intituleEnquete) {
        this.intituleEnquete = intituleEnquete;
    }

    public ArrayList<Option> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<Option> options) {
        this.options = options;
    }

    public void addOptions (ArrayList<Option> options) {
        this.options.addAll(options);
    }

    public int getTotalVoteCount () {
        int sum = 0;
        for(Option option : this.options) {
            sum += option.getVoteCount();
        }
        return sum;
    }
}
