package com.example.mobile.presenters.participant;

import com.example.mobile.Callback;
import com.example.mobile.Repositories.SurveyRepository;
import com.example.mobile.Repositories.models.Enquete;
import com.example.mobile.Repositories.models.Vote;
import com.example.mobile.Views.ViewInterfaces.participant.ParticipantSurveyView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

public class ParticipantSurveyPresenter {

    private ParticipantSurveyView view;
    private SurveyRepository repo;
    private List<Enquete> surveys;

    public ParticipantSurveyPresenter(ParticipantSurveyView view, SurveyRepository repo) {
        this.repo = repo;
        this.view = view;
        surveys = new ArrayList();
    }

    public void loadSurveys(int confId) {
        repo.getVisibleEnquetes(confId, new Callback<List<Enquete>>() {
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

    public void reloadSurveys(int confId) {
        repo.getVisibleEnquetes(confId, new Callback<List<Enquete>>() {
            @Override
            public void onSuccess(List<Enquete> surveyList) {
                surveys = surveyList;
                if (surveys == null){
                    System.out.println(">>>>>>>>>>>>NO SURVEYS>>>>>>>>>>>>");
                }
                else if (surveys.isEmpty()) {
                    System.out.println(">>>>>>>>>>>>EMPTY>>>>>>>>>>>>");
                    view.showEmptyListView();
                } else {
                    System.out.println(">>>>>>>>>>>>SURVEYS: "+ surveys.get(0).getOptions().size()+" >>>>>>>>>>>>");
                    view.showList(surveys);
                }
                view.stopRefreshingView();
            }

            @Override
            public void onError(Throwable error) {
                view.showList(surveys);
                view.stopRefreshingView();
            }
        });

    }

    public void fetchNew(int confId) {
        System.out.println(">>>>>>>>>>>Fetching new >>>>>>>>>>>");
        repo.getVisibleEnquetes(confId, new Callback<List<Enquete>>() {
            @Override
            public void onSuccess(List<Enquete> result) {
                if (result == null ) {
                    return;
                }
                int n = surveys.size();
                Enquete.appendNew(surveys, result);

                if (n == surveys.size()) {
                    System.out.println(">>>>>>>>>>>Nothing found>>>>>>>>>>>");
                } else {
                    System.out.println(">>>>>>>>>>>Found new>>>>>>>>>>>");
                    view.appendNew(surveys);
                }
            }

            @Override
            public void onError(Throwable error) {
                view.showErrorView();
            }
        });
    }

    public void vote(int enqueteIndex, Vote vote) {
        repo.vote(surveys.get(enqueteIndex).getId(), vote, new Callback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody result) {
                view.showStats(enqueteIndex);
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

}
