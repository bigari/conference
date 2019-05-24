package com.example.mobile.Repositories;

import com.example.mobile.Callback;
import com.example.mobile.Repositories.apis.PresenterApi;
import com.example.mobile.Repositories.models.User;
import com.example.mobile.RetrofitClient;

import retrofit2.Call;
import retrofit2.Response;

public class UserRepository {


    private final PresenterApi presenterApi;

    public UserRepository(){
        this.presenterApi = RetrofitClient.getRetrofit().create(PresenterApi.class);
    }

    public void login(User user, final Callback<User> callback) {
        presenterApi.login(user).enqueue(new retrofit2.Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

}
