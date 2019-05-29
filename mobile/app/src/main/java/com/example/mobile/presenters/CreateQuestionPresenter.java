package com.example.mobile.presenters;

import com.example.mobile.Repositories.QuestionRepository;
import com.example.mobile.Repositories.models.Question;
import com.example.mobile.Views.ViewInterfaces.CreateQuestionView;


public class CreateQuestionPresenter {
    private CreateQuestionView view;
    private QuestionRepository repository;

    public CreateQuestionPresenter(CreateQuestionView view, QuestionRepository repository){
        this.view = view;
        this.repository = repository;

    }

    public void createQuestion(){

    }

}
