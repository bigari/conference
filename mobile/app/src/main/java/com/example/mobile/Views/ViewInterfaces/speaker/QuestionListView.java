package com.example.mobile.Views.ViewInterfaces.speaker;

import com.example.mobile.Repositories.models.Question;

import java.util.List;

public interface QuestionListView {

    void showList(List<Question> question);
    void showEmptyListView();
    void showErrorView(String message);
    void stopRefreshingView();
    void showDeleteDialog();
    void showProgress();
    void hideProgress();
    void showErrorSnackbar(String message);
}
