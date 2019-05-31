package com.example.mobile.presenters.speaker;


import com.example.mobile.Callback;
import com.example.mobile.Repositories.SpeakerRepository;
import com.example.mobile.Repositories.models.Speaker;
import com.example.mobile.Views.ViewInterfaces.speaker.SpeakerLoginView;

/**
 * Created by neron on 5/23/19.
 */

public class SpeakerLoginPresenter {

    private SpeakerLoginView view;
    private SpeakerRepository repository;

    public SpeakerLoginPresenter(SpeakerLoginView view, SpeakerRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void login(Speaker speaker) {

        repository.login(speaker, new Callback<Speaker>() {
            @Override
            public void onSuccess(Speaker value) {
                view.showLoginSuccess(value);
            }

            @Override
            public void onError(Throwable error) {
                view.showLoginError();
            }
        });
    }
}
