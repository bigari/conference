package com.example.mobile.Views.ViewInterfaces.speaker;

import com.example.mobile.Repositories.models.Speaker;

public interface SpeakerLoginView {
    void showLoginError();

    void showLoginSuccess(Speaker speaker);
}
