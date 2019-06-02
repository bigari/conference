package com.example.mobile.Views.ViewInterfaces.speaker;

import com.example.mobile.Repositories.models.Enquete;

import java.util.List;

/**
 * Created by neron on 6/1/19.
 */

public interface SpeakerSurveyView {
    void showList(List<Enquete> surveys);
    void showEmptyListView();
    void showErrorView();
    void stopRefreshingView();
    void updateStats(List<Enquete> enquetes);
}
