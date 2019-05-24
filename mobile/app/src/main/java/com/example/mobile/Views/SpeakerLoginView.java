package com.example.mobile.Views;

import com.example.mobile.Repositories.models.Speaker;

public interface UserLoginView {
    void showLoginError();

    void showLoginSuccess(Speaker speaker);
}
