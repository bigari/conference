package com.example.mobile.Views.ViewInterfaces;

import com.example.mobile.Repositories.models.Conference;

import java.util.List;

public interface ConferenceListView {

    void showConfs(List<Conference> activeConfs, List<Conference> pastConfs);
    void showEmptyListView();
    void showConfs(List<Conference> confs, String type);
    void showErrorView(String message);
    void showErrorSnackbar(String message);
    void showLoading();
    void hideLoading();
    void showDeleteDialog();
}
