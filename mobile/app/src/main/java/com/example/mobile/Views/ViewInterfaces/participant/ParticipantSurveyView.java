package com.example.mobile.Views.ViewInterfaces.participant;

import com.example.mobile.Repositories.models.Enquete;

import java.util.List;

public interface ParticipantSurveyView {
    void showList(List<Enquete> surveys);
    void showEmptyListView();
    void showErrorView();
    void stopRefreshingView();

    void appendNew(List<Enquete> surveys);

    void showStats(int surveyIndex);

    void updateStats(List<Enquete> enquetes);
}
