package com.example.mobile.presenters.speaker;

import com.example.mobile.Callback;
import com.example.mobile.Repositories.SurveyRepository;
import com.example.mobile.Repositories.models.Enquete;
import com.example.mobile.Repositories.models.Vote;
import com.example.mobile.Views.ViewInterfaces.speaker.SpeakerSurveyView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by neron on 6/1/19.
 */

public class SpeakerSurveyPresenter {

    private SpeakerSurveyView view;
    private SurveyRepository repo;
    private List<Enquete> surveys;

    public SpeakerSurveyPresenter(SpeakerSurveyView view, SurveyRepository repo) {
        this.repo = repo;
        this.view = view;
        surveys = new ArrayList();
    }

    public void loadSurveys(int confId) {
        repo.getEnquetes(confId, new Callback<List<Enquete>>() {
            @Override
            public void onSuccess(List<Enquete> result) {
                surveys = result;
                if (surveys == null) {
                    System.out.println(">>>>>>>>>>>>NO SURVEYS>>>>>>>>>>>>");
                }
                else if (surveys.isEmpty()) {
                    System.out.println(">>>>>>>>>>>>EMPTY "+ confId+ "  "+ surveys.size()+ " >>>>>>>>>>>>");
                    view.showEmptyListView();
                } else {
                    System.out.println(">>>>>>>>>>>>SURVEYS: "+ surveys.get(0).getOptions().size()+" >>>>>>>>>>>>");
                    view.showList(surveys);
                }
            }

            @Override
            public void onError(Throwable error) {
                view.showErrorView();
            }
        });
    }


    public void getStats(int confId) {
        repo.getStats(confId, new Callback<List<Enquete>>() {
            @Override
            public void onSuccess(List<Enquete> value) {
                if (value != null) {
                    view.updateStats(value);
                }
            }

            @Override
            public void onError(Throwable error) {
                //Pass
            }
        });
    }

    public void deleteOption(int optionId) {
        repo.deleteOption(optionId, new Callback<Void>() {
            @Override
            public void onSuccess(Void value) {
                //Pass?
            }

            @Override
            public void onError(Throwable error) {
                //Pass
            }
        });
    }

    public void updateSurvey(Enquete survey) {
        repo.updateSurvey(survey, new Callback<Void>() {
            @Override
            public void onSuccess(Void value) {
                //Pass?
            }

            @Override
            public void onError(Throwable error) {
                //Pass
            }
        });
    }
}
