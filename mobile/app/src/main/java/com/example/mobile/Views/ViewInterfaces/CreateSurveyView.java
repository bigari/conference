package com.example.mobile.Views.ViewInterfaces;

/**
 * Created by neron on 6/1/19.
 */

public interface CreateSurveyView {

    void showErrorView(String error);
    void navToSurveysView();
    void showProgressbar();
    void hideProgressbar();
}
