package com.example.mobile.presenters;


import com.example.mobile.Callback;
import com.example.mobile.Repositories.UserRepository;
import com.example.mobile.Repositories.models.User;
import com.example.mobile.Views.UserLoginView;

/**
 * Created by neron on 5/23/19.
 */

public class UserLoginPresenter {

    private UserLoginView view;
    private UserRepository repository;

    public UserLoginPresenter(UserLoginView view, UserRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void login(User user) {

        repository.login(user, new Callback<User>() {
            @Override
            public void onSuccess(User value) {
                view.showLoginSuccess(value);
            }

            @Override
            public void onError(Throwable error) {
                view.showLoginError();
            }
        });
    }
}
