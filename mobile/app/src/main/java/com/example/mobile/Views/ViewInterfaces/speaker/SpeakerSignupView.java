package com.example.mobile.Views.ViewInterfaces.speaker;

import com.example.mobile.Repositories.models.Speaker;

public interface SpeakerSignupView {
    void showSignupError();
    void showSignupSuccess(Speaker speaker);
}
