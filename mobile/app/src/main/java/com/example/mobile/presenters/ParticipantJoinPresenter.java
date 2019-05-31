package com.example.mobile.presenters;


import com.example.mobile.Callback;
import com.example.mobile.Repositories.ConferenceRepository;
import com.example.mobile.Repositories.models.Conference;
import com.example.mobile.Views.ViewInterfaces.LandingView;
import com.example.mobile.utils.RandomString;

import static com.example.mobile.utils.RandomString.generate;

public class ParticipantJoinPresenter {

    private ConferenceRepository repo;
    private LandingView view;

    public ParticipantJoinPresenter(ConferenceRepository repo, LandingView view){
        this.repo = repo;
        this.view = view;
    }

    public void joinConf(String email, String accessCode){
        view.showProgress();
        // TODO- validate args
        repo.joinConf(email, accessCode, new Callback<Conference>() {
            @Override
            public void onSuccess(Conference conf) {
                if(conf == null){
                    view.showError("Wrong email or access code.");
                }else{
                    view.navToConf(conf);
                }
                view.hideProgresss();
            }

            @Override
            public void onError(Throwable error) {
                view.hideProgresss();
                view.showError();
            }
        });
    }
}
