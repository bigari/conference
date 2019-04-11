package com.example.mobile.Views;

import com.example.mobile.Repositories.models.Conference;

import java.util.List;

public interface ConferenceView {

    void showConference(Conference conference);
    void showError();

}
