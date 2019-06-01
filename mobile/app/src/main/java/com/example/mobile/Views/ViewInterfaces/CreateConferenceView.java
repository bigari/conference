package com.example.mobile.Views.ViewInterfaces;

import com.example.mobile.Repositories.models.Attachment;

import java.sql.Date;

public interface CreateConferenceView {

    void showProgressbar();
    void hideProgressbar();
    void showErrorView(String message);
    void navToConfList();

    String getConfTitle();
    Date getConfStartDate();
    Date getConfEndDate();
}
