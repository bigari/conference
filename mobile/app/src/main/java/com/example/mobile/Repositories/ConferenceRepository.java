package com.example.mobile.Repositories;

import com.example.mobile.Callback;
import com.example.mobile.RetrofitClient;
import com.example.mobile.Repositories.apis.ConferenceApi;
import com.example.mobile.Repositories.models.Conference;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ConferenceRepository{

    private final ConferenceApi conferenceApi;

    public ConferenceRepository(){
        conferenceApi = RetrofitClient.getRetrofit().create(ConferenceApi.class);
    }


    public void getConference(int id, final Callback<Conference> callback) {
        conferenceApi.getConferenceById(id).enqueue(new retrofit2.Callback<Conference>() {
            @Override
            public void onResponse(Call<Conference> call, Response<Conference> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Conference> call, Throwable t) {
                callback.onError(t);
            }
        });


    }

    public void getConferences(final Callback<List<Conference>> callback) {
        conferenceApi.getConferences().enqueue(new retrofit2.Callback<List<Conference>>() {
            @Override
            public void onResponse(Call<List<Conference>> call, Response<List<Conference>> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Conference>> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    public void createConference(Conference conference){}

    public void deleteConference(int id) {

    }

    public void updateConference(Conference conference) {

    }

}