package com.example.mobile.presenters;

import com.example.mobile.Repositories.ConferenceRepository;
import com.example.mobile.Views.ViewInterfaces.ConferenceView;

public class ConferencePresenter {

    private ConferenceView view;
    private ConferenceRepository repository;


    public ConferencePresenter(ConferenceView view, ConferenceRepository repository){
        this.view = view;
        this.repository = repository;
    }
}
