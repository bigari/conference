package com.example.mobile.presenters.speaker;

import android.util.Log;

import com.example.mobile.Callback;
import com.example.mobile.Repositories.ConferenceRepository;
import com.example.mobile.Repositories.QuestionRepository;
import com.example.mobile.Repositories.models.Question;
import com.example.mobile.Views.ViewInterfaces.speaker.QuestionListView;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

public class QuestionListPresenter {

    private QuestionListView view;
    private QuestionRepository questRepo;
    private ConferenceRepository confRepo;
    private List<Question> quests;

    private int index;
    private int qid;
    private int cid;

    public QuestionListPresenter(QuestionListView view, QuestionRepository questRepo, ConferenceRepository confRepo) {
        this.questRepo = questRepo;
        this.confRepo = confRepo;
        this.view = view;
        this.quests = new ArrayList<>();
    }

    public void loadQuestions(int confId) {
        view.showProgress();
        confRepo.getQuestions(confId, new Callback<List<Question>>() {
            @Override
            public void onSuccess(List<Question> quests) {
                if (quests.isEmpty()) {
                    view.hideProgress();
                    view.showEmptyListView();
                    QuestionListPresenter.this.quests = quests;
                } else {
                    view.hideProgress();
                    QuestionListPresenter.this.quests = quests;
                    view.showList(quests);
                }
            }

            @Override
            public void onError(Throwable error) {
                view.hideProgress();
                if(error instanceof ConnectException){
                    view.showErrorView("Network error, please check your internet connection.");
                }
                else{
                    view.showErrorView("An error has occurred.");
                }
            }
        });
    }

    public void reloadQuestions(int confId) {
        confRepo.getQuestions(confId, new Callback<List<Question>>() {
            @Override
            public void onSuccess(List<Question> quests) {
                if (quests.isEmpty()) {
                    view.showEmptyListView();
                    QuestionListPresenter.this.quests = quests;
                } else {
                    view.showList(quests);
                    QuestionListPresenter.this.quests = quests;
                    view.hideProgress();
                }
                view.stopRefreshingView();
            }

            @Override
            public void onError(Throwable error) {
                view.stopRefreshingView();
                if(error instanceof ConnectException){
                    view.showErrorView("Network error, please check your internet connection.");
                }
                else{
                    view.showErrorView("An error has occurred.");
                }
            }
        });
    }

    public void loadQuestions(){
        if (this.quests.isEmpty()) {
            view.showEmptyListView();
        } else {
            view.showList(this.quests);
        }
    }

    public void confirmDelete(String token){
        view.showProgress();
        questRepo.delete(this.cid, this.qid, token, new Callback<Void>() {
            @Override
            public void onSuccess(Void value) {
                quests.remove(index);
                loadQuestions();
                view.hideProgress();
            }

            @Override
            public void onError(Throwable error) {
                view.hideProgress();
                if(error instanceof ConnectException){
                    view.showErrorSnackbar("Network error, please check your internet connection.");
                }
                else{
                    view.showErrorSnackbar("An error has occurred.");
                }
            }
        });
    }

    public void cancelDelete(){
        this.index = 0;
        this.qid = 0;
    }

    public void deleteQuest(int index, int qid, int cid){
        view.showDeleteDialog();
        this.index = index;
        this.qid = qid;
        this.cid = cid;
    }


}
