package com.example.mobile.Repositories;

import com.example.mobile.Callback;
import com.example.mobile.Repositories.apis.QuestionApi;
import com.example.mobile.Repositories.models.Question;
import com.example.mobile.RetrofitClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class QuestionRepository {

    private final QuestionApi questionApi;

    public QuestionRepository(){
        questionApi = RetrofitClient.getRetrofit().create(QuestionApi.class);
    }

    public void getQuestions(int confId, Callback<List<Question>> cb){
        questionApi.getQuestions(confId).enqueue(new retrofit2.Callback<List<Question>>() {
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

    public void create(Question quest, Callback<Void> cb){
        questionApi.create(quest).enqueue(new retrofit2.Callback<Void>() {
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



}
