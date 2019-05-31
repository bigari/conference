package com.example.mobile.presenters.speaker;


import com.example.mobile.Callback;
import com.example.mobile.Repositories.SpeakerRepository;
import com.example.mobile.Repositories.models.Speaker;
import com.example.mobile.Views.ViewInterfaces.speaker.SpeakerLoginView;
import com.example.mobile.Views.ViewInterfaces.speaker.SpeakerSignupView;

/**
 * Created by neron on 5/23/19.
 */

public class SpeakerSignupPresenter {

    private SpeakerSignupView view;
    private SpeakerRepository repository;

    public SpeakerSignupPresenter(SpeakerSignupView view, SpeakerRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void signup(Speaker speaker) {

        repository.signup(speaker, new Callback<Speaker>() {
            @Override
            public void onSuccess(Speaker createdSpeaker) {
                if (createdSpeaker == null) {
                    System.out.println(">>>>>>>>>>>ERROR SIGNUP");
                    view.showSignupSuccess(createdSpeaker);
                    return;
                }
                repository.login(speaker, new Callback<Speaker>() {
                    @Override
                    public void onSuccess(Speaker value) {
                        view.showSignupSuccess(value);
                    }
                    @Override
                    public void onError(Throwable error) {
                        view.showSignupError();
                    }
                });
            }
            @Override
            public void onError(Throwable error) {
                view.showSignupError();
            }
        });
    }
}
