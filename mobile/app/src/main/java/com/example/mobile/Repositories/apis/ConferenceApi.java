package com.example.mobile.Repositories.apis;

import com.example.mobile.Repositories.models.Conference;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ConferenceApi {

    @GET("api/conferences")
    Call<List<Conference>> getConferences();

    @GET("api/conferences/{id}")
    Call<Conference> getConferenceById(@Path("id") int id);

    @POST("api/conferences")
    Call<Conference> createConference(@Body Conference conference);
}
