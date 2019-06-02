package com.example.mobile.Views.ViewInterfaces;

public interface CreateQuestionView {
    void showErrorView(String error);
    void navToQuestsView();
    void showProgress();
    void hideProgress();
    void showFieldError(String field, String error);
}
