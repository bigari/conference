package com.example.mobile.Views.ViewInterfaces.speaker;

import com.example.mobile.Repositories.models.Question;

import java.util.List;

public interface QuestionListView {

    void showList(List<Question> question);
    void showEmptyListView();
    void showErrorView();
    void stopRefreshingView();
}
