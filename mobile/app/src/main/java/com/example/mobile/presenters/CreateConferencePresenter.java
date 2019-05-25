package com.example.mobile.presenters;

import com.example.mobile.Callback;
import com.example.mobile.Repositories.ConfListCache;
import com.example.mobile.Repositories.ConferenceRepository;
import com.example.mobile.Repositories.models.Conference;
import com.example.mobile.Views.ViewInterfaces.CreateConferenceView;

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

    public void createConference(){
        String title = view.getConfTitle();
        Date startDate = view.getConfStartDate();
        Date endDate = view.getConfEndDate();


        String error = validateTitle(title);
        if(error != null){
            return;
        }

        Conference conference = new Conference(1, title, startDate, endDate);
        view.showProgressbar();
        repository.createConference(conference, new Callback<Conference>() {
            @Override
            public void onSuccess(Conference conf) {
                confListCache.addConf(conf);
                view.navToConfList();
            }
            @Override
            public void onError(Throwable error) {
                view.showErrorView();
            }
        });

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
