package com.example.mobile.Repositories.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neron on 4/10/19.
 */

public class Enquete {
    @Expose(serialize = false, deserialize = true)
    private int id;
    @Expose
    private String intituleEnquete;
    @Expose
    private List<Option> options;

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

    public List<Option> getOptions() {
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
