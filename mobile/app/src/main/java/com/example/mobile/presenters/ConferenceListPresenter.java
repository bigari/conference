package com.example.mobile.presenters;

import android.util.Log;

import com.example.mobile.Callback;
import com.example.mobile.Repositories.ConferenceRepository;
import com.example.mobile.Repositories.models.Conference;
import com.example.mobile.Views.ConferenceListView;

import java.util.ArrayList;
import java.util.List;

public class ConferenceListPresenter {

    private static final String TAG = "conferenceListPresenter";
    private ConferenceListView view;
    private ConferenceRepository repository;
    private List<Conference> conferences;

    public ConferenceListPresenter(ConferenceListView view, ConferenceRepository repository){
        this.view = view;
        this.repository = repository;
    }


    public void loadConferenceList(){
        repository.getConferences(new Callback<List<Conference>>() {
            @Override
            public void onSuccess(List<Conference> response) {
                view.showConferences(response);
            }

            @Override
            public void onError(Throwable error) {
//                Log.e(TAG, "onError: Error while loading conference list.", error);
                view.showError();
            }
        });

    }
}
