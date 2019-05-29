package com.example.mobile.Views.ViewInterfaces;

import com.example.mobile.Repositories.models.Conference;

public interface LandingView {
    void navToConf(Conference conf);
    void showProgress();
    void hideProgresss();
    void showError();
    void showError(String error);
}
