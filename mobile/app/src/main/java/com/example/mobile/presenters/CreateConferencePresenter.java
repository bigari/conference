package com.example.mobile.presenters;

import com.example.mobile.Repositories.ConferenceRepository;
import com.example.mobile.Repositories.models.Conference;
import com.example.mobile.Views.CreateConferenceView;

import java.sql.Date;

public class CreateConferencePresenter {

    private CreateConferenceView view;
    private ConferenceRepository repository;

    public CreateConferencePresenter(CreateConferenceView view, ConferenceRepository repository){
        this.view = view;
        this.repository = repository;
    }

    public void createConference(){
        String title = view.getConfTitle();

        String error = validateTitle(title);
        if(error != null){
            view.showError(error);
            return;
        }
//        error = validateStartDate();
//        if(error != null){
//            view.showError(error);
//            return;
//        }
//        error = validateEndDate();
//        if(error != null){
//            view.showError(error);
//            return;
//        }

        Conference conference = new Conference(title, null, null);
        view.showProgressBar();
        repository.createConference(conference);
        view.hideProgressBar();

    }

    private String validateTitle(String title){
        if(title == null){
            return "This field is required.";
        }
        else if(title.isEmpty()){
            return "This field must be at least 8 characters long.";
        }
        return null;
    }

    private String validateStartDate(Date startDate){
        return null;
    }

    private String validateEndDate(Date endDate){
        return null;
    }


}
