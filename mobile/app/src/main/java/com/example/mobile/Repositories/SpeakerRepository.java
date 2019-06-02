package com.example.mobile.Repositories;

import android.util.Log;

import com.example.mobile.Callback;
import com.example.mobile.Repositories.apis.SpeakerApi;
import com.example.mobile.Repositories.models.Speaker;
import com.example.mobile.RetrofitClient;

import retrofit2.Call;
import retrofit2.Response;

public class SpeakerRepository {


    private final SpeakerApi speakerApi;

    public SpeakerRepository(){
        this.speakerApi = RetrofitClient.getRetrofit().create(SpeakerApi.class);
    }

    public void login(Speaker speaker, final Callback<Speaker> callback) {
        speakerApi.login(speaker).enqueue(new retrofit2.Callback<Speaker>() {
            @Override
            public void onResponse(Call<Speaker> call, Response<Speaker> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Speaker> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    public void signup(Speaker speaker, final Callback<Speaker> callback) {
        speakerApi.signup(speaker).enqueue(new retrofit2.Callback<Speaker>() {
            @Override
            public void onResponse(Call<Speaker> call, Response<Speaker> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Speaker> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    public void logout(String token, final Callback<Void> callback) {
        speakerApi.logout(token).enqueue(new retrofit2.Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("Logout:", "Logged out");
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onError(t);
            }
        });
    }


}
