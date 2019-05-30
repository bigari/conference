package com.example.mobile.presenters.speaker;

import android.util.Log;

import com.example.mobile.Callback;
import com.example.mobile.Repositories.QuestionRepository;
import com.example.mobile.Repositories.models.Question;
import com.example.mobile.Views.ViewInterfaces.speaker.QuestionListView;

import java.util.ArrayList;
import java.util.List;

public class QuestionListPresenter {

    private QuestionListView view;
    private QuestionRepository repo;
    private List<Question> quests;

    private int index;
    private int qid;

    public QuestionListPresenter(QuestionListView view, QuestionRepository repo) {
        this.repo = repo;
        this.view = view;
        this.quests = new ArrayList<>();
    }

    public void loadQuestions(int confId) {
        repo.getQuestions(confId, new Callback<List<Question>>() {
            @Override
            public void onSuccess(List<Question> quests) {
                if (quests.isEmpty()) {
                    view.showEmptyListView();
                    QuestionListPresenter.this.quests = quests;
                } else {
                    QuestionListPresenter.this.quests = quests;
                    view.showList(quests);
                }
            }

            @Override
            public void onError(Throwable error) {
                view.showErrorView();
            }
        });
    }

    public void reloadQuestions(int confId) {
        repo.getQuestions(confId, new Callback<List<Question>>() {
            @Override
            public void onSuccess(List<Question> quests) {
                if (quests.isEmpty()) {
                    view.showEmptyListView();
                    QuestionListPresenter.this.quests = quests;
                } else {
                    view.showList(quests);
                    QuestionListPresenter.this.quests = quests;
                }
                view.stopRefreshingView();
            }

            @Override
            public void onError(Throwable error) {
                view.stopRefreshingView();
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
        repo.delete(this.qid, token, new Callback<Void>() {
            @Override
            public void onSuccess(Void value) {
                quests.remove(index);
                loadQuestions();
                view.hideProgress();
            }

            @Override
            public void onError(Throwable error) {
                view.hideProgress();
                view.showErrorView();
            }
        });
    }

    public void cancelDelete(){
        this.index = 0;
        this.qid = 0;
    }

    public void deleteQuest(int index, int qid){
        view.showDeleteDialog();
        this.index = index;
        this.qid = qid;
    }


}
