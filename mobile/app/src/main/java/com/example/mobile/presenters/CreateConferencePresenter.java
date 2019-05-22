package com.example.mobile.presenters;

import android.util.Log;

import com.example.mobile.Callback;
import com.example.mobile.Repositories.ConfListCache;
import com.example.mobile.Repositories.ConferenceRepository;
import com.example.mobile.Repositories.models.Conference;
import com.example.mobile.Views.CreateConferenceView;

import java.sql.Date;
import java.util.function.ToDoubleBiFunction;

import okhttp3.ResponseBody;

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
        Conference conference = new Conference(1, title, startDate, endDate);
        view.showProgressbar();
        repository.createConference(conference, new Callback<Conference>() {
            @Override
            public void onSuccess(Conference conf) {
                confListCache.addConf(conf);
                view.navToConfList();
//                TODO implement progressbar
                view.hideProgressbar();
            }
            @Override
            public void onError(Throwable error) {
                view.hideProgressbar();
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
