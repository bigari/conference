package com.example.mobile.presenters.participant;

import com.example.mobile.Callback;
import com.example.mobile.Repositories.QuestionRepository;
import com.example.mobile.Repositories.models.Question;
import com.example.mobile.Views.ViewInterfaces.participant.QuestionListView;

import java.util.List;

public class QuestionListPresenter {

    private QuestionListView view;
    private QuestionRepository repo;
    private List<Question> quests;

    public QuestionListPresenter(QuestionListView view, QuestionRepository repo) {
        this.repo = repo;
        this.view = view;
    }

    public void loadQuestions(int confId) {
        repo.getQuestions(confId, new Callback<List<Question>>() {
            @Override
            public void onSuccess(List<Question> questions) {
                quests = questions;
                if (questions.isEmpty()) {
                    view.showEmptyListView();
                } else {
                    view.showList(questions);
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
            public void onSuccess(List<Question> questList) {
                quests = questList;
                if (quests.isEmpty()) {
                    view.showEmptyListView();
                } else {
                    view.showList(quests);
                }
                view.stopRefreshingView();
            }

            @Override
            public void onError(Throwable error) {
                view.showList(quests);
                view.stopRefreshingView();
            }
        });

    }


    public void deleteQuestion(int questId) {
    }


}
