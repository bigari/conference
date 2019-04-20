package com.example.mobile.presenters;

import com.example.mobile.Repositories.ConferenceRepository;
import com.example.mobile.Repositories.models.Conference;
import com.example.mobile.Views.CreateConferenceView;

public class CreateConferencePresenter {

    private CreateConferenceView view;
    private ConferenceRepository repository;

    public CreateConferencePresenter(CreateConferenceView view, ConferenceRepository repository){
        this.view = view;
        this.repository = repository;
    }

    public void createConference(){
        String errorMessage = validateTitle();
        if(errorMessage != null){
            view.showError(errorMessage);
        }



        Conference conference = new Conference();
        view.showProgressBar();
        repository.createConference(conference);
        view.hideProgressBar();

    }

    private String validateTitle(){
        String title = view.getConfTitle();
        if(title == null){
            return "This field is required.";
        }
        else if(title.isEmpty()){
            return "This field must be at least 8 characters long.";
        }
        return null;
    }

    private String validateDescription(){
        String title = view.getConfDescription();
        if(title == null){
            return "This field is required.";
        }
        else if(title.isEmpty()){
            return "This field must be at least 8 characters long.";
        }
        return null;
    }

    private String validateDate(){
        String title = view.getConfDate();
        if(title == null){
            return "This field is required.";
        }
        else if(title.isEmpty()){
            return "This field must be at least 8 characters long.";
        }
        return null;
    }

    private String validateAddress(){
        return null;
    }

    private String validateAttachment(){
        return null;
    }



}
