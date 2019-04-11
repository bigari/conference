package com.example.mobile.presenters;

import com.example.mobile.Callback;
import com.example.mobile.Repositories.ConferenceRepository;
import com.example.mobile.Repositories.models.Conference;
import com.example.mobile.Views.ConferenceView;

public class ConferencePresenter {

    private ConferenceView view;
    private ConferenceRepository repository;


    public ConferencePresenter(ConferenceView view, ConferenceRepository repository){
        this.view = view;
        this.repository = repository;
    }

    public void loadConference(int id){
        repository.getConference(id, new Callback<Conference>() {
            @Override
            public void onSuccess(Conference response) {
                view.showConference(response);
            }

            @Override
            public void onError(Throwable error) {
                view.showError();
            }
        });
    }


}
