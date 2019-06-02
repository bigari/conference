package com.example.mobile.Repositories.apis;

import com.example.mobile.Repositories.models.Option;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OptionApi {

    @POST("api/enquetes/{id}/options")
    Call<List<Option>> createSurveyOptions(@Path("id") int id, @Body List<Option> options);

    @DELETE("api/options/{id}")
    Call<Void> deleteOption(@Path("id") int optionId);

    // TODO define this endpoint (increments the voteCount)
    @PUT("api/options/{id}/increment")
    Call<Void> incrementVoteCount(@Path("id") int optionId);

    // TODO define this endpoint (decrements the voteCount)
    @PUT("api/options/{id}/decrement")
    Call<Void> decrementVoteCount(@Path("id") int optionId);

}
