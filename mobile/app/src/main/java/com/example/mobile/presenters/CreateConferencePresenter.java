package com.example.mobile.presenters;


import android.util.Log;

import com.example.mobile.Callback;
import com.example.mobile.Repositories.ConfListCache;
import com.example.mobile.Repositories.ConferenceRepository;
import com.example.mobile.Repositories.models.Conference;
import com.example.mobile.Views.ViewInterfaces.CreateConferenceView;

import java.net.ConnectException;
import java.sql.Date;


public class CreateConferencePresenter {

    private CreateConferenceView view;
    private ConferenceRepository repository;
    private ConfListCache confListCache;

    public CreateConferencePresenter(CreateConferenceView view, ConferenceRepository repository){
        this.view = view;
        this.repository = repository;
        this.confListCache = ConfListCache.getInstance();

    }

    public void createConference(int uid, String token){
        String title = view.getConfTitle();
        Date startDate = view.getConfStartDate();
        Date endDate = view.getConfEndDate();
        int speakerId = uid;


        Conference conference = new Conference(speakerId, title, startDate, endDate);
        view.showProgressbar();
        repository.createConference(conference, token,new Callback<Conference>() {
            @Override
            public void onSuccess(Conference conf) {
                confListCache.addConf(conf);
                view.navToConfList();
            }
            @Override
            public void onError(Throwable error) {
                if(error instanceof ConnectException){
                    view.showErrorView("Check your internet connection and try again.");
                }
                else{
                    view.showErrorView("An error occurred");
                }
            }
        });

    }



}
