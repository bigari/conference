package com.example.mobile.Views;

import com.example.mobile.Repositories.models.Attachment;

import java.sql.Date;

public interface CreateConferenceView {

    void showProgressbar();
    void hideProgressbar();
    void showError(String error);
    void navToConfList();

    String getConfTitle();
    Date getConfStartDate();
    Date getConfEndDate();
}
