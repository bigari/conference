package com.example.mobile.presenters;


import com.example.mobile.Callback;
import com.example.mobile.Repositories.ConferenceRepository;
import com.example.mobile.Repositories.models.Conference;
import com.example.mobile.Views.ViewInterfaces.LandingView;

public class ParticipantJoinPresenter {

    private ConferenceRepository repo;
    private LandingView view;

    public ParticipantJoinPresenter(ConferenceRepository repo, LandingView view){
        this.repo = repo;
        this.view = view;
    }

    public void joinConf(String email, String accessCode){
        // TODO- validate args
        view.showProgress();
        repo.joinConf(email, accessCode, new Callback<Conference>() {
            @Override
            public void onSuccess(Conference conf) {
                view.hideProgresss();
                if(conf == null){
                    view.showError("Wrong email or access code.");
                }else{
                    view.navToConf(conf);
                }
            }

            @Override
            public void onError(Throwable error) {
                view.hideProgresss();
                view.showError();
            }
        });
    }
}
