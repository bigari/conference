package com.example.mobile.Repositories.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neron on 4/10/19.
 */

public class Enquete {
    @Expose(serialize = false, deserialize = true)
    private int id;
    @Expose
    @SerializedName("intituleEnquete")
    private String intituleEnquete;

    @Expose(serialize = false, deserialize = true)
    private List<Option> options;


    private boolean statsVisible;

    @Expose
    private boolean visible;

    public Enquete() {
        options = new ArrayList();
        statsVisible = false;
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof  Enquete) {
            return this.id == ((Enquete) obj).getId();
        }
        return super.equals(obj);
    }

    public boolean isStatsVisible() {
        return statsVisible;
    }

    public void setStatsVisible(boolean statsVisible) {
        this.statsVisible = statsVisible;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public static void appendNew(List<Enquete> l1, List<Enquete> l2) {
        ArrayList<Enquete> newSurveys = new ArrayList<>();
        for (Enquete enquete : l2) {
            if (!l1.contains(enquete)) {
                newSurveys.add(enquete);
            }
        }
        l1.addAll(newSurveys);
    }

    public static void updateStats(List<Enquete> l1, List<Enquete> l2) {
        int index;
        for (Enquete enquete : l2) {
            index = l1.indexOf(enquete);
            if (index != -1) {
                Option.updateStats(
                        l1.get(index).getOptions(),
                        enquete.getOptions()
                );
            }
        }
    }
}
