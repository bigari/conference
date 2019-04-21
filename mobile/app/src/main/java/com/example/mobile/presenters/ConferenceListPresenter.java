package com.example.mobile.presenters;

import com.example.mobile.Repositories.ConferenceRepository;
import com.example.mobile.Repositories.models.Conference;
import com.example.mobile.Views.ConferenceListView;

import java.util.List;

public class ConferenceListPresenter {

    private ConferenceListView view;
    private ConferenceRepository repository;


    public ConferenceListPresenter(ConferenceListView view, ConferenceRepository repository){
        this.view = view;
        this.repository = repository;
    }

    public List<Conference> loadConfs(String userId){
        return null;
    }



}
