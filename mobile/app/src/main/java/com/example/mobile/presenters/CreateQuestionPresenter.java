package com.example.mobile.presenters;

import com.example.mobile.Callback;
import com.example.mobile.Repositories.QuestionRepository;
import com.example.mobile.Repositories.models.Question;
import com.example.mobile.Views.ViewInterfaces.CreateQuestionView;

import java.net.ConnectException;


public class CreateQuestionPresenter {
    private CreateQuestionView view;
    private QuestionRepository repo;

    public CreateQuestionPresenter(CreateQuestionView view, QuestionRepository repo){
        this.view = view;
        this.repo = repo;

    }

    public void createQuestion(String username, String question, int confId){
        view.showProgress();
        if(question.isEmpty()){
            view.showErrorView("Error");
            return;
        }
        Question quest = new Question();
        //TODO- validate args
        if(username.isEmpty()){quest.setUsername("Anonymous");}
        else{quest.setUsername(username);}
        quest.setTimestamp();
        quest.setContent(question);
        quest.setConfId(confId);

        repo.create(quest, new Callback<Void>() {
            @Override
            public void onSuccess(Void value) {
                view.hideProgress();
                view.navToQuestsView();
            }

            @Override
            public void onError(Throwable error) {
                view.hideProgress();
                if(error instanceof ConnectException){
                    view.showErrorView("Network error, check your internet connection.");
                }
                else{
                    view.showErrorView("An error occurred.");
                }
            }
        });
    }

}
