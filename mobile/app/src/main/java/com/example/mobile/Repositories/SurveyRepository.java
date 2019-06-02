package com.example.mobile.Repositories;

import android.support.v4.widget.SwipeRefreshLayout;

import com.example.mobile.Callback;
import com.example.mobile.Repositories.apis.EnqueteApi;
import com.example.mobile.Repositories.apis.OptionApi;
import com.example.mobile.Repositories.apis.QuestionApi;
import com.example.mobile.Repositories.models.Enquete;
import com.example.mobile.Repositories.models.Option;
import com.example.mobile.Repositories.models.Question;
import com.example.mobile.Repositories.models.Vote;
import com.example.mobile.RetrofitClient;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class SurveyRepository {

    private final EnqueteApi enqueteApi;
    private final OptionApi optionApi;


    public SurveyRepository(){
        enqueteApi = RetrofitClient.getRetrofit().create(EnqueteApi.class);
        optionApi = RetrofitClient.getRetrofit().create(OptionApi.class);

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

   public void create(Enquete enquete, int confId, String token, Callback<Enquete> cb){
        enqueteApi.createConfEnquete(confId, enquete, token).enqueue(new retrofit2.Callback<Enquete>() {
            @Override
            public void onResponse(Call<Enquete> call, Response<Enquete> response) {

                if (response == null) {
                    return;
                }
                optionApi.createSurveyOptions(response.body().getId(), enquete.getOptions()).enqueue(new retrofit2.Callback<List<Option>>() {
                    @Override
                    public void onResponse(Call<List<Option>> call, Response<List<Option>> responseOption) {

                        if (responseOption != null) {
                            cb.onSuccess(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Option>> call, Throwable t) {
                        cb.onError(t);
                    }
                });
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

    public  void deleteOption (int optionId, Callback<Void> cb ) {


        optionApi.deleteOption(optionId).enqueue(new retrofit2.Callback<Void>() {
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


    public  void updateSurvey (Enquete survey, Callback<Void> cb ) {
        enqueteApi.updateEnquete(survey.getId(), survey).enqueue(new retrofit2.Callback<Void>() {
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

    public  void updateSurveyOptions (Enquete enquete, Callback<List<Option>> cb ) {

        enqueteApi.deleteEnqueteOptions(enquete.getId()).enqueue(new retrofit2.Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                optionApi.createSurveyOptions(enquete.getId(), enquete.getOptions()).enqueue(new retrofit2.Callback<List<Option>>() {
                    @Override
                    public void onResponse(Call<List<Option>> call, Response<List<Option>> responseOption) {
                        if (responseOption != null) {
                            cb.onSuccess(responseOption.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Option>> call, Throwable t) {
                        cb.onError(t);
                    }
                });
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });




    }

}
