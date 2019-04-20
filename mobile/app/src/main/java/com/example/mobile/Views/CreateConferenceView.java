package com.example.mobile.Views;

import com.example.mobile.Repositories.models.Attachment;

public interface CreateConferenceView {

    void showError(String message);
    void displayForm();
    void showProgressBar();
    void hideProgressBar();
    void addAttachmentField(Attachment attachment);
    String getConfTitle();
    String getConfDescription();
    String getConfAddress();
    String getConfDate();


}
