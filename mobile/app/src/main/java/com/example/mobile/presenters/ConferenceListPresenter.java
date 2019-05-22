package com.example.mobile.presenters;

import android.util.Log;

import com.example.mobile.Callback;
import com.example.mobile.Repositories.ConferenceRepository;
import com.example.mobile.Repositories.models.Conference;
import com.example.mobile.Views.ConferenceListView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ConferenceListPresenter {

    private ConferenceListView view;
    private ConferenceRepository repository;


    public ConferenceListPresenter(ConferenceListView view, ConferenceRepository repository){
        this.view = view;
        this.repository = repository;
    }

    public void loadConfs(int uid){
        repository.getConferences(uid, new Callback<List<Conference>>() {
            @Override
            public void onSuccess(List<Conference> confs) {
                List<Conference> activeConfs = new ArrayList<>();
                List<Conference> pastConfs = new ArrayList<>();

                int confsSize = confs.size();
                if(confs.isEmpty()){
                    view.showEmptyListView();
                    return;
                }

                Date now = new Date(Calendar.getInstance().getTimeInMillis());
                int i = 0;
                while(i < confsSize && confs.get(i).getEndDate().after(now)){
                    activeConfs.add(confs.get(i));
                    i++;
                }
                pastConfs = confs.subList(i, confs.size());

                if(activeConfs.isEmpty()){
                    view.showConfs(pastConfs, "active");
                    return;
                }
                else if(pastConfs.isEmpty()){
                    view.showConfs(activeConfs, "past");
                    return;
                }
                view.showConfs(activeConfs, pastConfs);
            }

            @Override
            public void onError(Throwable error) {
                Log.e("conference list", "loading conference list failed", error);
            }
        });

    }





}
