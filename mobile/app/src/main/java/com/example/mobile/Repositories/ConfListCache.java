package com.example.mobile.Repositories;

import com.example.mobile.Repositories.models.Conference;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;


// Singletons is instantiated only a single time when the conference activity starts only one instance
// holds list of conferences, every subsequent request/operation is done via a request and direct op on datat structure
public class ConfListCache {

    private List<Conference> activeConfs;
    private List<Conference> pastConfs;
    private static ConfListCache instance;

    public static ConfListCache getInstance(){
        if(instance == null){
            instance = new ConfListCache();
            return instance;
        }
        return instance;
    }

    public List<Conference> getPastConfs(){
        return pastConfs;
    }

    public List<Conference> getActiveConfs(){
        return activeConfs;
    }

    public void addConf(Conference conf){
        Date now = new Date(Calendar.getInstance().getTimeInMillis());
        int i = 0;
        Date endDate = conf.getEndDate();
        while(i < activeConfs.size() && activeConfs.get(i).getEndDate().after(endDate)){
            i++;
        }

        activeConfs.add(i, conf);
    }

    public void setActiveConfs(List<Conference> confs){
        this.activeConfs = confs;
    }

    public void setPastConfs(List<Conference> confs){
        this.pastConfs = confs;
    }
}
