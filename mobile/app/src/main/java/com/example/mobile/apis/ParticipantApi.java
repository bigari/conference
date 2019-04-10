package com.example.mobile.apis;

import com.example.mobile.models.Participant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ParticipantApi {

    @POST("api/participants")
    Call<Participant> createParticipant(@Body Participant participant);
}
