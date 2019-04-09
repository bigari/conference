package com.example.mobile.apis;

import com.example.mobile.models.Conference;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ConferenceApi {

    @GET("api/conferences")
    Call<List<Conference>> getConferences();

    @GET("api/conferences/{id}")
    Call<List<Conference>> getConferenceById(@Path("id") int id);
}
