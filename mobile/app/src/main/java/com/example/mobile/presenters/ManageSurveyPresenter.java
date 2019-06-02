package com.example.mobile.presenters;


import com.example.mobile.Callback;
import com.example.mobile.Repositories.SurveyRepository;
import com.example.mobile.Repositories.models.Enquete;
import com.example.mobile.Repositories.models.Option;
import com.example.mobile.Views.ViewInterfaces.CreateSurveyView;
import java.net.ConnectException;
import java.util.List;

public class ManageSurveyPresenter {

    private CreateSurveyView view;
    private SurveyRepository repository;

    public ManageSurveyPresenter(CreateSurveyView view, SurveyRepository repository){
        this.view = view;
        this.repository = repository;

    }

    public void createSurvey(Enquete survey, int confId, String token){
        survey.setConferenceId(confId);
        view.showProgressbar();
        repository.create(survey, confId, token, new Callback<Enquete>() {
            @Override
            public void onSuccess(Enquete survey) {
                view.navToSurveysView();
                view.hideProgressbar();
            }
            @Override
            public void onError(Throwable error) {
                if(error instanceof ConnectException){
                    view.showErrorView("Check your internet connection and try again.");
                }
                else{
                    view.showErrorView("An error occurred");
                }
                view.hideProgressbar();
            }
        });

    }

    public void updateSurvey(Enquete survey) {
        repository.updateSurvey(survey, new Callback<Void>() {
            @Override
            public void onSuccess(Void value) {
                view.navToSurveysView();
            }

            @Override
            public void onError(Throwable error) {
                //Pass
            }
        });
    }

    public void updateSurveyOptions (Enquete enquete) {
        repository.updateSurveyOptions(enquete, new Callback<List<Option>>() {
            @Override
            public void onSuccess(List<Option> value) {
                view.navToSurveysView();
            }

            @Override
            public void onError(Throwable error) {

            }
        });
    }



}
