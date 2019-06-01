package com.example.mobile.presenters.participant;

import com.example.mobile.Callback;
import com.example.mobile.Repositories.ParticipantRepository;
import com.example.mobile.Repositories.models.Participant;
import com.example.mobile.Views.ViewInterfaces.LandingView;


public class ParticipantPresenter {

    private LandingView view;
    private ParticipantRepository repo;

    public ParticipantPresenter(LandingView view, ParticipantRepository repo) {
        this.repo = repo;
        this.view = view;
    }

    public void create(Participant participant) {
        repo.create(participant, new Callback<Participant>() {
            @Override
            public void onSuccess(Participant result) {
                if (result == null) {
                    System.out.println(">>>>>>>>>>>>Failed to create>>>>>>>>>>>>");
                    return;
                }
                Participant.current.setId(result.getId());
                view.setParticipantId(result.getId());
            }

            @Override
            public void onError(Throwable error) {
            }
        });
    }

    

    

}
