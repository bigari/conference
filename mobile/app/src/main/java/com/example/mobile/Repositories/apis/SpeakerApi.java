package com.example.mobile.Repositories.apis;

/**
 * Created by neron on 5/23/19.
 */

import com.example.mobile.Repositories.models.Speaker;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface SpeakerApi {
    @POST("api/speakers/login")
    Call<Speaker> login(@Body Speaker speaker);

    @POST("api/speakers")
    Call<Speaker> signup(@Body Speaker speaker);
}
