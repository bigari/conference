package com.example.mobile.Views;

import com.example.mobile.Repositories.models.Conference;

import java.util.List;

public interface ConferenceListView {

    void showConferences(List<Conference> conferences);
    void showError();

}
