package com.example.mobile.Repositories;

import android.support.v4.widget.SwipeRefreshLayout;

import com.example.mobile.Callback;
import com.example.mobile.Repositories.apis.EnqueteApi;
import com.example.mobile.Repositories.apis.QuestionApi;
import com.example.mobile.Repositories.models.Enquete;
import com.example.mobile.Repositories.models.Question;
import com.example.mobile.Repositories.models.Vote;
import com.example.mobile.RetrofitClient;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class SurveyRepository {

    private final EnqueteApi enqueteApi;


    public SurveyRepository(){
        enqueteApi = RetrofitClient.getRetrofit().create(EnqueteApi.class);
    }

    public void getEnquetes(int confId, Callback<List<Enquete>> cb){
        enqueteApi.getEnquetes(confId).enqueue(new retrofit2.Callback<List<Enquete>>() {
            @Override
            public void onResponse(Call<List<Enquete>> call, Response<List<Enquete>> response) {
                cb.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Enquete>> call, Throwable t) {
                cb.onError(t);
            }
        });
    }

    public void getVisibleEnquetes(int confId, Callback<List<Enquete>> cb){
        enqueteApi.getVisibleEnquetes(confId).enqueue(new retrofit2.Callback<List<Enquete>>() {
            @Override
            public void onResponse(Call<List<Enquete>> call, Response<List<Enquete>> response) {
                cb.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Enquete>> call, Throwable t) {
                cb.onError(t);
            }
        });
    }

   public void create(Enquete enquete, Callback<Enquete> cb){
        enqueteApi.createEnquete(enquete).enqueue(new retrofit2.Callback<Enquete>() {
            @Override
            public void onResponse(Call<Enquete> call, Response<Enquete> response) {
                cb.onSuccess(response.body());
            }
            @Override
            public void onFailure(Call<Enquete> call, Throwable t) {
                cb.onError(t);
            }
        });
    }


    public void vote (int enqueteId, Vote vote, Callback<ResponseBody> cb){
        enqueteApi.vote(enqueteId, vote).enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println(">>>>Vote made");
                cb.onSuccess(response.body());
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                cb.onError(t);
            }
        });
    }

    public void getStats (int confId, Callback<List<Enquete>> cb){
        enqueteApi.getStats(confId).enqueue(new retrofit2.Callback<List<Enquete>>() {
            @Override
            public void onResponse(Call<List<Enquete>> call, Response<List<Enquete>> response) {
                cb.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Enquete>> call, Throwable t) {
                cb.onError(t);
            }
        });
    }

}
