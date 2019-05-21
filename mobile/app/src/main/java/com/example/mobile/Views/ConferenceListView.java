package com.example.mobile.Views;

import com.example.mobile.Repositories.models.Conference;

import java.util.List;

public interface ConferenceListView {

    void showConfs(List<Conference> activeConfs, List<Conference> pastConfs);

}
