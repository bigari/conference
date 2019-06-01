package com.example.mobile.Repositories;

import com.example.mobile.Callback;
import com.example.mobile.Repositories.models.Question;
import com.example.mobile.RetrofitClient;
import com.example.mobile.Repositories.apis.ConferenceApi;
import com.example.mobile.Repositories.models.Conference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class ConferenceRepository{

    private final ConferenceApi conferenceApi;

    public ConferenceRepository(){
        conferenceApi = RetrofitClient.getRetrofit().create(ConferenceApi.class);
    }

    public void getConferences(int userId, String key, final Callback<List<Conference>> callback) {
        conferenceApi.getConferences(userId, key).enqueue(new retrofit2.Callback<List<Conference>>() {
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

    public void getQuestions(int confId, Callback<List<Question>> cb){
        conferenceApi.getQuestions(confId).enqueue(new retrofit2.Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                cb.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                cb.onError(t);
            }
        });
    }

    public void createConference(Conference conference, String token, Callback<Conference> cb){
        conferenceApi.createConference(conference, token).enqueue(new retrofit2.Callback<Conference>() {
            @Override
            public void onResponse(Call<Conference> call, Response<Conference> response) {
                cb.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Conference> call, Throwable t) {
                cb.onError(t);
            }
        });
    }

    public void deleteConference(int cid, String token, Callback<Void> cb) {
        conferenceApi.deleteConference(cid, token).enqueue(new retrofit2.Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                cb.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                cb.onError(t);
            }
        });
    }

    public void deleteQuestions(int cid, String token, Callback<Void> cb){
        conferenceApi.deleteQuestions(cid, token).enqueue(new retrofit2.Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                cb.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                cb.onError(t);
            }
        });
    }

    public void updateConference(Conference conference) {

    }

    public void joinConf(String email, String accesscode, Callback<Conference> cb){
        Map<String, String> filters = new HashMap<>();
        filters.put("email", email);
        filters.put("accesscode", accesscode);

        conferenceApi.joinConference(filters).enqueue(new retrofit2.Callback<Conference>() {
            @Override
            public void onResponse(Call<Conference> call, Response<Conference> response) {
                cb.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Conference> call, Throwable t) {
                cb.onError(t);
            }
        });
    }

}
